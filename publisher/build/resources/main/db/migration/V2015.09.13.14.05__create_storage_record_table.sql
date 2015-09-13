CREATE TABLE `lousysterm`.`storage_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `time_created` DATETIME NULL,
  `item_code` VARCHAR(255) NULL,
  `weight` VARCHAR(64) NULL,
  PRIMARY KEY (`id`));
