DROP TABLE IF EXISTS destinations;

CREATE TABLE destinations
(
    id BIGSERIAL,
    location VARCHAR(255),
    distance int,
    CONSTRAINT PK_destinations PRIMARY KEY (id)
);