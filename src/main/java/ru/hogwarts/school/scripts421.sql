ALTER TABLE student
    ADD CONSTRAINT age CHECK (age > 16);

ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name),
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT color_name_unique UNIQUE (color, name);






