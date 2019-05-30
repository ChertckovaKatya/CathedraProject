-- MySQL Script generated by MySQL Workbench
-- Wed May 29 14:56:04 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sys_analysis_management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sys_analysis_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sys_analysis_management` DEFAULT CHARACTER SET utf8 ;
USE `sys_analysis_management` ;

-- -----------------------------------------------------
-- Table `sys_analysis_management`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`user` (
  `id` INT(100) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `Password` INT(100) NOT NULL,
  `Role` VARCHAR(45) NULL DEFAULT 'UNKNOWN',
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sys_analysis_management`.`Subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`Subject` (
  `idSubject` INT NOT NULL AUTO_INCREMENT,
  `SubjectName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSubject`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sys_analysis_management`.`Test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`Test` (
  `idTest` INT NOT NULL AUTO_INCREMENT,
  `idSubjectTest` INT NOT NULL,
  `QuantityQuestions` INT NOT NULL,
  `LeadTime` INT NOT NULL,
  `TotalScore` INT NOT NULL,
  `TitleTests` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTest`),
  INDEX `fk_Test_Subject1_idx` (`idSubjectTest` ASC),
  CONSTRAINT `fk_Test_Subject1`
    FOREIGN KEY (`idSubjectTest`)
    REFERENCES `sys_analysis_management`.`Subject` (`idSubject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sys_analysis_management`.`TypeQuestion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`TypeQuestion` (
  `idTypeQuestion` INT NOT NULL AUTO_INCREMENT,
  `Wording` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTypeQuestion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sys_analysis_management`.`PassedTests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`PassedTests` (
  `idPassed` INT NOT NULL AUTO_INCREMENT,
  `idSelectedOptions` INT NULL,
  `idTest` INT NOT NULL,
  `idStudents` INT NOT NULL,
  `LeadTime` INT NOT NULL,
  `NumCorreсtAnswer` INT NOT NULL,
  `NumIncorreсtAnswer` INT NOT NULL,
  `Point` INT NOT NULL,
  `DateCompletion` DATETIME NULL,
  PRIMARY KEY (`idPassed`),
  INDEX `fk_PassedTests_Test1_idx` (`idTest` ASC),
  INDEX `fk_PassedTests_user1_idx` (`idStudents` ASC),
  CONSTRAINT `fk_PassedTests_Test1`
    FOREIGN KEY (`idTest`)
    REFERENCES `sys_analysis_management`.`Test` (`idTest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PassedTests_user1`
    FOREIGN KEY (`idStudents`)
    REFERENCES `sys_analysis_management`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sys_analysis_management`.`SelectedAnswerChoices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`SelectedAnswerChoices` (
  `idSelectedAnswerChoices` INT NOT NULL AUTO_INCREMENT,
  `idQuestionPassedTest` INT NULL,
  `idAnswerPassedTest` INT NULL,
  `Self-enteredAnswer` VARCHAR(45) NULL,
  PRIMARY KEY (`idSelectedAnswerChoices`),
  CONSTRAINT `fk_SelectedAnswerChoices_PassedTests1`
    FOREIGN KEY (`idSelectedAnswerChoices`)
    REFERENCES `sys_analysis_management`.`PassedTests` (`idSelectedOptions`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sys_analysis_management`.`Questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`Questions` (
  `idQuestions` INT NOT NULL AUTO_INCREMENT,
  `idTestQuestions` INT NOT NULL,
  `QuestionWording` VARCHAR(100) NOT NULL,
  `TypeQues` INT NOT NULL,
  `QuantityAnswer` INT NOT NULL,
  PRIMARY KEY (`idQuestions`),
  INDEX `fk_Questions_TypeQuestion1_idx` (`TypeQues` ASC),
  INDEX `fk_Questions_Test1_idx` (`idTestQuestions` ASC),
  CONSTRAINT `fk_Questions_TypeQuestion1`
    FOREIGN KEY (`TypeQues`)
    REFERENCES `sys_analysis_management`.`TypeQuestion` (`idTypeQuestion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Questions_Test1`
    FOREIGN KEY (`idTestQuestions`)
    REFERENCES `sys_analysis_management`.`Test` (`idTest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Questions_SelectedAnswerChoices1`
    FOREIGN KEY (`idQuestions`)
    REFERENCES `sys_analysis_management`.`SelectedAnswerChoices` (`idQuestionPassedTest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sys_analysis_management`.`Answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sys_analysis_management`.`Answer` (
  `idAnswer` INT NOT NULL AUTO_INCREMENT,
  `idQuestionAnswer` INT NOT NULL,
  `AnswerWording` VARCHAR(45) NOT NULL,
  `Mark` INT NULL DEFAULT 0,
  PRIMARY KEY (`idAnswer`),
  INDEX `fk_Answer_Questions1_idx` (`idQuestionAnswer` ASC),
  CONSTRAINT `fk_Answer_Questions1`
    FOREIGN KEY (`idQuestionAnswer`)
    REFERENCES `sys_analysis_management`.`Questions` (`idQuestions`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_SelectedAnswerChoices1`
    FOREIGN KEY (`idAnswer`)
    REFERENCES `sys_analysis_management`.`SelectedAnswerChoices` (`idAnswerPassedTest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;