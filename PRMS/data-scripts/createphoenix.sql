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
  `activeuser` boolean default 1,
  `email` VARCHAR(255) NULL ,
  `phoneNo` VARCHAR(45) NULL ,
  `siteLink` MEDIUMTEXT NULL ,
  `profilePicture` MEDIUMBLOB NULL ,
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
insert into `phoenix`.`user` values("dilbert", "dilbert", "dilbert, the hero", "presenter:producer", 1, NULL, NULL, NULL, NULL);
insert into `phoenix`.`user` values("wally", "wally", "wally, the bludger", "producer", 1, NULL, NULL, NULL, NULL);
insert into `phoenix`.`user` values("pointyhead", "pointyhead", "pointyhead, the manager", "manager", 1, NULL, NULL, NULL, NULL);
insert into `phoenix`.`user` values("catbert", "catbert", "catbert, the hr", "admin:manager", 1, NULL, NULL, NULL, NULL);
insert into `phoenix`.`user` values("dogbert", "dogbert", "dogbert, the CEO", "producer:admin", 1, NULL, NULL, NULL, NULL);

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
  `assignedBy` VARCHAR(45) NULL ,
  PRIMARY KEY (`year`) ,
  CONSTRAINT `id_as`
    FOREIGN KEY (`assignedBy` )
    REFERENCES `phoenix`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `id_annual_schedule` ON `phoenix`.`annual-schedule` (`assignedBy` ASC) ;

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
  `dateOfProgram` DATE NOT NULL ,
  `startTime` TIME NULL ,
  `program-name` VARCHAR(45) NULL ,
  `producer` VARCHAR(40),
  `presenter` VARCHAR(40),
  PRIMARY KEY (`startTime`, `dateOfProgram`) ,
  CONSTRAINT `name`
    FOREIGN KEY (`program-name` )
    REFERENCES `phoenix`.`radio-program` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 
     `producer_fk`
     FOREIGN KEY(`producer`) 
     REFERENCES `phoenix`.`user` (`id` )
  ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 
     `presenter_fk` 
     FOREIGN KEY(`presenter`) 
     REFERENCES `phoenix`.`user` (`id` ) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `name_program_slot` ON `phoenix`.`program-slot` (`program-name` ASC) ;

CREATE INDEX `dateOfProgram` ON `phoenix`.`program-slot` (`dateOfProgram` ASC) ;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`program-slot`
-- -----------------------------------------------------

Insert into phoenix.`program-slot` values (str_to_date('00:30:00', '%H:%i:%s'), str_to_date('10-01-2015', '%d-%m-%Y'), str_to_date('13:00:00', '%H:%i:%s'), 'news','dilbert','dilbert');
Insert into phoenix.`program-slot` values (str_to_date('01:30:00', '%H:%i:%s'), str_to_date('10-01-2015', '%d-%m-%Y'), str_to_date('14:00:00', '%H:%i:%s'), 'charity','dilbert','wally');
Insert into phoenix.`program-slot` values (str_to_date('02:00:00', '%H:%i:%s'), str_to_date('11-01-2015', '%d-%m-%Y'), str_to_date('09:00:00', '%H:%i:%s'), 'dance floor','dilbert','wally');
Insert into phoenix.`program-slot` values (str_to_date('02:30:00', '%H:%i:%s'), str_to_date('11-01-2015', '%d-%m-%Y'), str_to_date('11:00:00', '%H:%i:%s'), 'noose','dilbert','wally');
Insert into phoenix.`program-slot` values (str_to_date('01:00:00', '%H:%i:%s'), str_to_date('11-01-2015', '%d-%m-%Y'), str_to_date('12:00:00', '%H:%i:%s'), 'news','dilbert','dilbert');

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

Insert into phoenix.`weekly-schedule` values (str_to_date('05-01-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('12-01-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('19-01-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('26-01-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('02-02-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('09-02-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('16-02-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('23-02-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('02-03-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('09-03-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('16-03-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('23-03-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('30-03-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('06-04-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('13-04-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('20-04-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('27-04-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('04-05-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('11-05-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('18-05-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('25-05-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('01-06-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('08-06-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('15-06-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('22-06-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('29-06-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('06-07-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('13-07-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('20-07-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('27-07-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('03-08-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('10-08-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('17-08-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('24-08-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('31-08-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('07-09-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('14-09-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('21-09-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('28-09-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('05-10-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('12-10-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('19-10-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('26-10-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('02-11-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('09-11-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('16-11-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('23-11-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('30-11-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('07-12-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('14-12-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('21-12-2015', '%d-%m-%Y'), 'pointyhead');
Insert into phoenix.`weekly-schedule` values (str_to_date('28-12-2015', '%d-%m-%Y'), 'pointyhead');

