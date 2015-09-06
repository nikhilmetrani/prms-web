create database phoenix;

use phoenix;

-- -----------------------------------------------------
-- Table `phoenix`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`role` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`role` (
  `role` VARCHAR(15) NOT NULL ,
  `accessPrivilege` VARCHAR(45) NULL ,
  PRIMARY KEY (`role`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `role_UNIQUE` ON `phoenix`.`role` (`role` ASC) ;


-- -----------------------------------------------------
-- Table `phoenix`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`user` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`user` (
  `id` VARCHAR(40) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NULL ,
  `role` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`))
--  CONSTRAINT `role`
--    FOREIGN KEY (`role` )
--    REFERENCES `phoenix`.`role` (`role` )
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `phoenix`.`user` (`id` ASC) ;

CREATE INDEX `role_index` ON `phoenix`.`user` (`role` ASC) ;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`role`
-- -----------------------------------------------------

-- role, access privilege
insert into `phoenix`.`role` values("presenter","radio program presenter");
insert into `phoenix`.`role` values("manager", "station manager");
insert into `phoenix`.`role` values("admin", "system administrator");
insert into `phoenix`.`role` values("producer", "program producer");


-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`user`
-- -----------------------------------------------------

-- id, password, name, role
insert into `phoenix`.`user` values("dilbert", "dilbert", "dilbert, the hero", "presenter:producer");
insert into `phoenix`.`user` values("wally", "wally", "wally, the bludger", "producer");
insert into `phoenix`.`user` values("pointyhead", "pointyhead", "pointyhead, the manager", "manager");
insert into `phoenix`.`user` values("catbert", "catbert", "catbert, the hr", "admin:manager");
insert into `phoenix`.`user` values("dogbert", "dogbert", "dogbert, the CEO", "producer:admin");

-- -----------------------------------------------------
-- Table `phoenix`.`radio-program`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`radio-program` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`radio-program` (
  `name` VARCHAR(45) NOT NULL ,
  `desc` VARCHAR(100) NULL ,
  `typicalDuration` TIME NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `phoenix`.`radio-program` (`name` ASC) ;


-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`radio-program`
-- -----------------------------------------------------

insert into `phoenix`.`radio-program` values("short news", "summarised 5 minutes broadcasted every 2 hours", '00:05:00');
insert into `phoenix`.`radio-program` values("news", "full news broadcasted four times a day", '00:30:00');
insert into `phoenix`.`radio-program` values("top 10", "countdown music play of top 10 songs of the week", '01:00:00');
insert into `phoenix`.`radio-program` values("your choice", "audinece ask for music album song of thier choice", '01:00:00');
insert into `phoenix`.`radio-program` values("opinions", "discuss, debate or share opinions regarding a theme or subject", '00:30:00');
insert into `phoenix`.`radio-program` values("noose", "black comedy show", '00:30:00');
insert into `phoenix`.`radio-program` values("ppk", "phu chu kang comedy show", '00:30:00');
insert into `phoenix`.`radio-program` values("dance floor", "dance show", '00:30:00');
insert into `phoenix`.`radio-program` values("charity", "president charity show for unfortunate", '00:30:00');

-- -----------------------------------------------------
-- Table `phoenix`.`annual-schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`annual-schedule` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`annual-schedule` (
  `year` INT NOT NULL ,
  `assingedBy` VARCHAR(45) NULL ,
  PRIMARY KEY (`year`) ,
  CONSTRAINT `id_as`
    FOREIGN KEY (`assingedBy` )
    REFERENCES `phoenix`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `id_annual_schedule` ON `phoenix`.`annual-schedule` (`assingedBy` ASC) ;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`annual-schedule`
-- -----------------------------------------------------

Insert into phoenix.`annual-schedule` values (2015, 'pointyhead');

-- -----------------------------------------------------
-- Table `phoenix`.`program-slot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`program-slot` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`program-slot` (
  `duration` TIME NOT NULL ,
  `dateOfProgram` DATETIME NOT NULL ,
  `startTime` DATETIME NULL ,
  `program-name` VARCHAR(45) NULL ,
  PRIMARY KEY (`duration`, `dateOfProgram`) ,
  CONSTRAINT `name`
    FOREIGN KEY (`program-name` )
    REFERENCES `phoenix`.`radio-program` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `name_program_slot` ON `phoenix`.`program-slot` (`program-name` ASC) ;

CREATE UNIQUE INDEX `dateOfProgram_UNIQUE` ON `phoenix`.`program-slot` (`dateOfProgram` ASC) ;


-- -----------------------------------------------------
-- Table `phoenix`.`weekly-schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`weekly-schedule` ;
CREATE  TABLE IF NOT EXISTS `phoenix`.`weekly-schedule` (
  `startDate` DATETIME NOT NULL ,
  `assignedBy` VARCHAR(45) NULL ,
  PRIMARY KEY (`startDate`) ,
  CONSTRAINT `id_ws`
    FOREIGN KEY (`assignedBy` )
    REFERENCES `phoenix`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `startDate_UNIQUE` ON `phoenix`.`weekly-schedule` (`startDate` ASC) ;

CREATE INDEX `id_assigned_by` ON `phoenix`.`weekly-schedule` (`assignedBy` ASC) ;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`weekly-schedule`
-- -----------------------------------------------------

Insert into phoenix.`weekly-schedule` values (str_to_date('01-01-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('08-01-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('15-01-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('22-01-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('29-01-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('05-02-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('12-02-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('19-02-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('26-02-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('05-03-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('12-03-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('19-03-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('26-03-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('02-04-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('09-04-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('16-04-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('23-04-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('30-04-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('07-05-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('14-05-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('21-05-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('28-05-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('04-06-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('11-06-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('18-06-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('25-06-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('02-07-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('09-07-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('16-07-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('23-07-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('30-07-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('06-08-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('13-08-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('20-08-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('27-08-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('03-09-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('10-09-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('17-09-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('24-09-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('01-10-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('08-10-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('15-10-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('22-10-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('29-10-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('05-11-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('12-11-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('19-11-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('26-11-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('03-12-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('10-12-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('17-12-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('24-12-15', '%d-%m-%y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('31-12-15', '%d-%m-%y'), 'pointyhead');

