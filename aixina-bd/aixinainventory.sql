-- MySQL Script generated by MySQL Workbench
-- Mon Nov 16 11:42:34 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema default_schema
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema aixinainventory
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `aixinainventory` ;

-- -----------------------------------------------------
-- Schema aixinainventory
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aixinainventory` DEFAULT CHARACTER SET utf8 ;
USE `aixinainventory` ;

-- -----------------------------------------------------
-- Table `aixinainventory`.`Headquarters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Headquarters` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Headquarters` (
  `headquartersId` VARCHAR(50) NOT NULL,
  `headquartersName` VARCHAR(100) NOT NULL,
  `headquartersAddress` VARCHAR(100) NOT NULL,
  `headquartersLocation` VARCHAR(200) NULL DEFAULT NULL,
  `headquartersWebSite` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`headquartersId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Department` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Department` (
  `departmentId` VARCHAR(50) NOT NULL,
  `departmentName` VARCHAR(100) NULL DEFAULT NULL,
  `headquartersId` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`departmentId`),
  CONSTRAINT `fk_Department_Headquarters`
    FOREIGN KEY (`headquartersId`)
    REFERENCES `aixinainventory`.`Headquarters` (`headquartersId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Department_Headquarters` ON `aixinainventory`.`Department` (`headquartersId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Location` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Location` (
  `locationId` INT NOT NULL AUTO_INCREMENT,
  `departmentLocationId` VARCHAR(50) NOT NULL,
  `locationAbbreviation` VARCHAR(50) NOT NULL,
  `locationDescription` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`locationId`),
  CONSTRAINT `fk_LocationFloor_Department`
    FOREIGN KEY (`departmentLocationId`)
    REFERENCES `aixinainventory`.`Department` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aixinainventory`.`ItemType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`ItemType` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`ItemType` (
  `itemTypeId` VARCHAR(50) NOT NULL,
  `itemTypeName` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`itemTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aixinainventory`.`ItemSet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`ItemSet` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`ItemSet` (
  `itemSetId` INT NOT NULL AUTO_INCREMENT,
  `itemSetName` VARCHAR(300) NULL DEFAULT NULL,
  `itemSetStickerNumber` VARCHAR(100) NULL DEFAULT NULL,
  `itemSetRegisterDate` DATE NULL DEFAULT NULL,
  `itemSetShutDate` DATE NULL DEFAULT NULL,
  `itemTypeId` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`itemSetId`),
  CONSTRAINT `fk_ItemSet_ItemType`
    FOREIGN KEY (`itemTypeId`)
    REFERENCES `aixinainventory`.`ItemType` (`itemTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ItemSet_ItemType` ON `aixinainventory`.`ItemSet` (`itemTypeId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Brand`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Brand` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Brand` (
  `brandId` VARCHAR(50) NOT NULL,
  `brandName` VARCHAR(100) NULL DEFAULT NULL,
  `brandAddress` VARCHAR(200) NULL,
  `brandPhone` VARCHAR(200) NULL,
  `brandEmail` VARCHAR(100) NULL,
  `brandWebSite` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`brandId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Item` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Item` (
  `itemId` INT NOT NULL AUTO_INCREMENT,
  `itemName` VARCHAR(300) NOT NULL,
  `itemStickerNumber` VARCHAR(100) NULL DEFAULT NULL,
  `itemRegisterDate` DATE NULL DEFAULT NULL,
  `itemShutDate` DATE NULL DEFAULT NULL,
  `itemSetId` INT NULL DEFAULT NULL,
  `itemTypeId` VARCHAR(50) NOT NULL,
  `brandId` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`itemId`),
  CONSTRAINT `fk_Item_ItemSet`
    FOREIGN KEY (`itemSetId`)
    REFERENCES `aixinainventory`.`ItemSet` (`itemSetId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_Brand`
    FOREIGN KEY (`brandId`)
    REFERENCES `aixinainventory`.`Brand` (`brandId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_ItemType`
    FOREIGN KEY (`itemTypeId`)
    REFERENCES `aixinainventory`.`ItemType` (`itemTypeId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_Item_ItemSet` ON `aixinainventory`.`Item` (`itemSetId` ASC) VISIBLE;

CREATE INDEX `fk_Item_Brand` ON `aixinainventory`.`Item` (`brandId` ASC) VISIBLE;

CREATE INDEX `fk_Item_ItemType` ON `aixinainventory`.`Item` (`itemTypeId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Employee` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Employee` (
  `employeeId` INT NOT NULL AUTO_INCREMENT,
  `employeeName` VARCHAR(100) NOT NULL,
  `employeeLastNames` VARCHAR(100) NULL DEFAULT NULL,
  `employeeNIF` VARCHAR(9) NOT NULL,
  `departmentId` VARCHAR(50) NOT NULL,
  `locationId` INT NOT NULL,
  `employeeChiefId` INT NULL DEFAULT NULL,
  `itemSetId` INT NULL DEFAULT NULL,
  `itemId` INT NULL DEFAULT NULL,
  `employeeRegisterDate` DATE NULL DEFAULT NULL,
  `employeeShutDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  CONSTRAINT `fk_Employee_ItemSet`
    FOREIGN KEY (`itemSetId`)
    REFERENCES `aixinainventory`.`ItemSet` (`itemSetId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Chief`
    FOREIGN KEY (`employeeChiefId`)
    REFERENCES `aixinainventory`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Department`
    FOREIGN KEY (`departmentId`)
    REFERENCES `aixinainventory`.`Department` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_LocationFloor`
    FOREIGN KEY (`locationId`)
    REFERENCES `aixinainventory`.`Location` (`locationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Item`
    FOREIGN KEY (`itemId`)
    REFERENCES `aixinainventory`.`Item` (`itemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Employee_ItemSet` ON `aixinainventory`.`Employee` (`itemSetId` ASC) VISIBLE;

CREATE INDEX `fk_Employee_Chief` ON `aixinainventory`.`Employee` (`employeeChiefId` ASC) VISIBLE;

CREATE INDEX `fk_Employee_Department` ON `aixinainventory`.`Employee` (`departmentId` ASC) VISIBLE;

CREATE INDEX `fk_Employee_LocationFloor` ON `aixinainventory`.`Employee` (`locationId` ASC) VISIBLE;

CREATE INDEX `fk_Employee_Item` ON `aixinainventory`.`Employee` (`itemId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`User` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `employeeId` INT NOT NULL,
  `userName` VARCHAR(50) NOT NULL,
  `userEmail` VARCHAR(200) NOT NULL,
  `userPassword` VARCHAR(300) NOT NULL,
  `lastLogin` DATE NULL DEFAULT NULL,
  `userRegisterDate` DATE NULL DEFAULT NULL,
  `userShutDate` DATE NULL DEFAULT NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT 0,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_User_Employee`
    FOREIGN KEY (`employeeId`)
    REFERENCES `aixinainventory`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_User_Employee` ON `aixinainventory`.`User` (`employeeId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`ItemImages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`ItemImages` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`ItemImages` (
  `itemImagesId` INT NOT NULL AUTO_INCREMENT,
  `itemImagesName` VARCHAR(100) NOT NULL,
  `itemImagesFile` MEDIUMBLOB NULL DEFAULT NULL,
  `itemId` INT NULL,
  `itemSetId` INT NULL,
  `uploadDate` DATE NULL,
  PRIMARY KEY (`itemImagesId`),
  CONSTRAINT `fk_ItemImages_Item`
    FOREIGN KEY (`itemId`)
    REFERENCES `aixinainventory`.`Item` (`itemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ItemImages_ItemSet`
    FOREIGN KEY (`itemSetId`)
    REFERENCES `aixinainventory`.`ItemSet` (`itemSetId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ItemImages_Item_idx` ON `aixinainventory`.`ItemImages` (`itemId` ASC) VISIBLE;

CREATE INDEX `fk_ItemImages_ItemSet_idx` ON `aixinainventory`.`ItemImages` (`itemSetId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Invoice` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Invoice` (
  `invoiceId` INT NOT NULL AUTO_INCREMENT,
  `invoiceName` VARCHAR(100) NOT NULL,
  `invoiceAbbreviation` VARCHAR(50) NOT NULL,
  `invoiceDate` DATE NULL DEFAULT NULL,
  `invoiceAttach` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`invoiceId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Line` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Line` (
  `lineId` INT NOT NULL AUTO_INCREMENT,
  `invoiceId` INT NOT NULL,
  `itemId` INT NULL DEFAULT NULL,
  `itemSetId` INT NULL DEFAULT NULL,
  `units` INT NULL DEFAULT NULL,
  `invoiceUnitCost` DECIMAL(25,9) NULL DEFAULT NULL,
  `invoiceTotalCost` DECIMAL(25,9) NULL DEFAULT NULL,
  PRIMARY KEY (`lineId`),
  CONSTRAINT `fk_InvoiceLine_Invoice`
    FOREIGN KEY (`invoiceId`)
    REFERENCES `aixinainventory`.`Invoice` (`invoiceId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_InvoiceLine_Item`
    FOREIGN KEY (`itemId`)
    REFERENCES `aixinainventory`.`Item` (`itemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_InvoiceLine_ItemSet`
    FOREIGN KEY (`itemSetId`)
    REFERENCES `aixinainventory`.`ItemSet` (`itemSetId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_InvoiceLine_Item` ON `aixinainventory`.`Line` (`itemId` ASC) VISIBLE;

CREATE INDEX `fk_InvoiceLine_ItemSet` ON `aixinainventory`.`Line` (`itemSetId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`MoveType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`MoveType` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`MoveType` (
  `moveTypeId` VARCHAR(50) NOT NULL,
  `moveTypeName` VARCHAR(100) NULL DEFAULT NULL,
  `moveTypeDescription` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`moveTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Movement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Movement` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Movement` (
  `movementId` INT NOT NULL AUTO_INCREMENT,
  `movementDate` DATE NOT NULL,
  `moveTypeId` VARCHAR(50) NOT NULL,
  `itemSetId` INT NULL DEFAULT NULL,
  `itemId` INT NULL DEFAULT NULL,
  `employeeSourceId` INT NULL DEFAULT NULL,
  `employeeDestinationId` INT NULL DEFAULT NULL,
  `departmentSourceId` VARCHAR(50) NULL DEFAULT NULL,
  `departmentDestinationId` VARCHAR(50) NULL DEFAULT NULL,
  `locationSourceId` INT NULL DEFAULT NULL,
  `locationDestinationId` INT NULL DEFAULT NULL,
  `invoiceId` INT NULL,
  `employeeId` INT NULL,
  `movementUnits` INT NULL DEFAULT NULL,
  `movementReason` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`movementId`),
  CONSTRAINT `fk_Movement_EmployeeSource`
    FOREIGN KEY (`employeeSourceId`)
    REFERENCES `aixinainventory`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_EmployeeDestination`
    FOREIGN KEY (`employeeDestinationId`)
    REFERENCES `aixinainventory`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_LocationFloorSource`
    FOREIGN KEY (`locationSourceId`)
    REFERENCES `aixinainventory`.`Location` (`locationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_LocationFloorDestination`
    FOREIGN KEY (`locationDestinationId`)
    REFERENCES `aixinainventory`.`Location` (`locationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_ItemSet`
    FOREIGN KEY (`itemSetId`)
    REFERENCES `aixinainventory`.`ItemSet` (`itemSetId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_MoveType`
    FOREIGN KEY (`moveTypeId`)
    REFERENCES `aixinainventory`.`MoveType` (`moveTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_DepartmentSource`
    FOREIGN KEY (`departmentSourceId`)
    REFERENCES `aixinainventory`.`Department` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_DepartmentDestination`
    FOREIGN KEY (`departmentDestinationId`)
    REFERENCES `aixinainventory`.`Department` (`departmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_Item`
    FOREIGN KEY (`itemId`)
    REFERENCES `aixinainventory`.`Item` (`itemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_Employee`
    FOREIGN KEY (`employeeId`)
    REFERENCES `aixinainventory`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movement_Invoice`
    FOREIGN KEY (`invoiceId`)
    REFERENCES `aixinainventory`.`Invoice` (`invoiceId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Movement_EmployeeSource` ON `aixinainventory`.`Movement` (`employeeSourceId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_EmployeeDestination` ON `aixinainventory`.`Movement` (`employeeDestinationId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_LocationFloorSource` ON `aixinainventory`.`Movement` (`locationSourceId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_LocationFloorDestination` ON `aixinainventory`.`Movement` (`locationDestinationId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_ItemSet` ON `aixinainventory`.`Movement` (`itemSetId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_MoveType` ON `aixinainventory`.`Movement` (`moveTypeId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_DepartmentSource` ON `aixinainventory`.`Movement` (`departmentSourceId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_DepartmentDestination` ON `aixinainventory`.`Movement` (`departmentDestinationId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_Item` ON `aixinainventory`.`Movement` (`itemId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_Employee_idx` ON `aixinainventory`.`Movement` (`employeeId` ASC) VISIBLE;

CREATE INDEX `fk_Movement_Invoice_idx` ON `aixinainventory`.`Movement` (`invoiceId` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `aixinainventory`.`Inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aixinainventory`.`Inventory` ;

CREATE TABLE IF NOT EXISTS `aixinainventory`.`Inventory` (
  `inventoryId` INT NOT NULL AUTO_INCREMENT,
  `inventoryDate` DATE NULL DEFAULT NULL,
  `inventoryYear` YEAR NULL DEFAULT NULL,
  `inventoryControlNumber` VARCHAR(100) NULL DEFAULT NULL,
  `employeeId` INT NOT NULL,
  `headquartersId` VARCHAR(50) NOT NULL,
  `invoiceId` INT NOT NULL,
  `itemSetId` INT NULL DEFAULT NULL,
  `itemId` INT NULL DEFAULT NULL,
  `inventoryUnits` INT NULL,
  `inventoryUnitCost` DECIMAL(25,9) NULL,
  `inventoryTotalValue` DECIMAL(25,9) NULL DEFAULT NULL,
  `inventoryLocation` INT NOT NULL,
  PRIMARY KEY (`inventoryId`),
  CONSTRAINT `fk_Inventory_Employee`
    FOREIGN KEY (`employeeId`)
    REFERENCES `aixinainventory`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inventory_Invoice`
    FOREIGN KEY (`invoiceId`)
    REFERENCES `aixinainventory`.`Invoice` (`invoiceId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inventory_ItemSet`
    FOREIGN KEY (`itemSetId`)
    REFERENCES `aixinainventory`.`ItemSet` (`itemSetId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inventory_Headquarters`
    FOREIGN KEY (`headquartersId`)
    REFERENCES `aixinainventory`.`Headquarters` (`headquartersId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inventory_Item`
    FOREIGN KEY (`itemId`)
    REFERENCES `aixinainventory`.`Item` (`itemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Inventory_Location`
    FOREIGN KEY (`inventoryLocation`)
    REFERENCES `aixinainventory`.`Location` (`locationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Inventory_Employee` ON `aixinainventory`.`Inventory` (`employeeId` ASC) VISIBLE;

CREATE INDEX `fk_Inventory_Invoice` ON `aixinainventory`.`Inventory` (`invoiceId` ASC) VISIBLE;

CREATE INDEX `fk_Inventory_ItemSet` ON `aixinainventory`.`Inventory` (`itemSetId` ASC) VISIBLE;

CREATE INDEX `fk_Inventory_Headquarters` ON `aixinainventory`.`Inventory` (`headquartersId` ASC) VISIBLE;

CREATE INDEX `fk_Inventory_Item` ON `aixinainventory`.`Inventory` (`itemId` ASC) VISIBLE;

CREATE INDEX `fk_Inventory_Location_idx` ON `aixinainventory`.`Inventory` (`inventoryLocation` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;