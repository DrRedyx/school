package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    @Value("${student.avatar.dir.path}")
    private String avatarDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long studId, MultipartFile file) throws IOException {
        logger.info("Load avatar to db");
        Student student = studentService.findStudent(studId);

        Path filePath = Path.of(avatarDir, studId + "." + getExist(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream in = file.getInputStream();
             OutputStream out = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bin = new BufferedInputStream(in, 1024);
             BufferedOutputStream bout = new BufferedOutputStream(out, 1024)) {
            bin.transferTo(bout);
        }

        Avatar avatar = findAvatar(studId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generatePreview(filePath));

        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(long id) {
        logger.info("Find avatar of 1 student");
        return avatarRepository.findByStudentId(id).orElse(new Avatar());
    }

    public List<Avatar> findAvatar(Integer pageNumber, Integer pageSize) {
        logger.info("Find avatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }

    public String getExist(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private byte[] generatePreview (Path filePath) throws IOException {
        logger.info("Load preview from db");
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()){
             BufferedImage bufferedImage = ImageIO.read(bis);

            int height = bufferedImage.getHeight() / (bufferedImage.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, bufferedImage.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(bufferedImage, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExist(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }
}
