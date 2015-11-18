CREATE TABLE DRIVER
(
  ID              NUMERIC(19) PRIMARY KEY,
  TEACHER         VARCHAR(255) NOT NULL,
  CLASS_NAME      VARCHAR(255) NOT NULL,
  FIRST_NAME      VARCHAR(255) NOT NULL,
  LAST_NAME       VARCHAR(255) NOT NULL,
  ADDRESS         VARCHAR(255) NOT NULL,
  PLZ             VARCHAR(255) NOT NULL,
  TOWN            VARCHAR(255) NOT NULL,
  KM_TO_SCHOOL    NUMERIC(19)  NOT NULL,
  HOURS_TO_SCHOOL NUMERIC(19)  NOT NULL,
  KFZ             VARCHAR(255) NOT NULL,
  PRIORITY        NUMERIC(19)  NOT NULL
);

CREATE TABLE PASSENGER
(
  ID         NUMERIC(19) PRIMARY KEY,
  FIRST_NAME VARCHAR(255) NOT NULL,
  LAST_NAME  VARCHAR(255) NOT NULL
);