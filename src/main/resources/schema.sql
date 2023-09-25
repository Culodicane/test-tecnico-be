CREATE TABLE IF NOT EXISTS USERS
(
    ID_USER             serial PRIMARY KEY,
    NAME                VARCHAR(255) NOT NULL,
    LASTNAME            VARCHAR(255) NOT NULL,
    EMAIL               VARCHAR(255) NOT NULL,
    PASSWORD            VARCHAR(255) NOT NULL,
    TYPE                VARCHAR(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS LIBRARY
(
    ID_LIBRARY             serial PRIMARY KEY,
    DATE_ADDED             TIMESTAMP(6),
    DATE_DELETED           TIMESTAMP(6),
    TIMES_READ             INTEGER NOT NULL,
    ID_USER                INTEGER NOT NULL,
    TITLE                  VARCHAR(255),
    ISBN                   VARCHAR(255),
    AUTHOR                 VARCHAR(255),
    PLOT                   VARCHAR(255)
);

