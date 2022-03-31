CREATE TABLE driver
(
    Id             SERIAL,
    Name           TEXT,
    Driver_License BOOLEAN,
    Age            INTEGER,
    Car_Mark       TEXT REFERENCES car(Mark)
);



CREATE TABLE car
(
    Id     SERIAL,
    Model  TEXT,
    Mark   TEXT PRIMARY KEY,
    Salary INTEGER
);


