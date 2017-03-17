--
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

--
-- Установка базы данных по умолчанию
--
USE cars;

--
-- Описание для таблицы car
--
DROP TABLE IF EXISTS car;
CREATE TABLE car (
  id       INT(11)      NOT NULL AUTO_INCREMENT,
  number   VARCHAR(255) NOT NULL,
  model_id INT(11)      NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_car_model_id FOREIGN KEY (model_id)
  REFERENCES model (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = INNODB
  AUTO_INCREMENT = 5
  AVG_ROW_LENGTH = 5461
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

--
-- Описание для таблицы car_service
--
DROP TABLE IF EXISTS car_service;
CREATE TABLE car_service (
  car_id     INT(11) DEFAULT NULL,
  service_id INT(11) DEFAULT NULL,
  CONSTRAINT FK_car_service_car_id FOREIGN KEY (car_id)
  REFERENCES car (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_car_service_service_id FOREIGN KEY (service_id)
  REFERENCES service (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = INNODB
  AVG_ROW_LENGTH = 4096
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

--
-- Описание для таблицы model
--
DROP TABLE IF EXISTS model;
CREATE TABLE model (
  id        INT(11)      NOT NULL AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  vendor_id INT(11)      NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_model_vendor_id FOREIGN KEY (vendor_id)
  REFERENCES vendor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = INNODB
  AUTO_INCREMENT = 4
  AVG_ROW_LENGTH = 5461
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

--
-- Описание для таблицы service
--
DROP TABLE IF EXISTS service;
CREATE TABLE service (
  id   INT(11) NOT NULL AUTO_INCREMENT,
  name TEXT    NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 3
  AVG_ROW_LENGTH = 8192
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

--
-- Описание для таблицы vendor
--
DROP TABLE IF EXISTS vendor;
CREATE TABLE vendor (
  id   INT(11)     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 4
  AVG_ROW_LENGTH = 5461
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

--
-- Вывод данных для таблицы car
--
INSERT INTO car VALUES
  (1, '345432', 1),
  (3, '33543253', 2),
  (4, '122312', 3);

--
-- Вывод данных для таблицы car_service
--
INSERT INTO car_service VALUES
  (1, 1),
  (1, 1),
  (3, 1),
  (3, 2);

--
-- Вывод данных для таблицы model
--
INSERT INTO model VALUES
  (1, 'X5', 1),
  (2, 'X4', 1),
  (3, 'X6', 1);

--
-- Вывод данных для таблицы service
--
INSERT INTO service VALUES
  (1, 'Сервис 1'),
  (2, 'Сервис 2');

--
-- Вывод данных для таблицы vendor
--
INSERT INTO vendor VALUES
  (1, 'BMW'),
  (2, 'Lada'),
  (3, 'General Motors');
