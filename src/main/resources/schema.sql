DROP TABLE IF EXISTS destinations;

CREATE TABLE destinations
(
    id       BIGSERIAL,
    location VARCHAR(255),
    distance INT,
    CONSTRAINT PK_destinations PRIMARY KEY (id)
);