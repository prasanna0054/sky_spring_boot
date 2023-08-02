DROP TABLE IF EXISTS `game` CASCADE;
CREATE TABLE `game` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `genre` VARCHAR(50),
    `year_of_release` INTEGER CHECK(year_of_release>=1950 AND year_of_release<=2050)
);