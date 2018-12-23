CREATE TABLE `storageContainer` (
    `uniqueid` varchar(255) NOT NULL DEFAULT '',
    `name` varchar(255) NOT NULL DEFAULT '',
    `description` varchar(255) DEFAULT NULL,
    `location` varchar(255) DEFAULT NULL,
    `number` decimal(9,2) DEFAULT NULL,
    `compartmentCount` decimal(9,2) DEFAULT NULL,
    `rows` decimal(9,2) DEFAULT NULL,
    `columns` decimal(9,2) DEFAULT NULL,
PRIMARY KEY (`uniqueid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into storageContainer values(uuid(), "SlideBox 1", "Grey box on the wall with translucent slides", "Dachboden", 1, 29, 4, 7);
insert into storageContainer values(uuid(), "SlideBox 2", "Grey box on the wall with translucent slides", "Dachboden", 2, 29, 4, 7);
insert into storageContainer values(uuid(), "SlideBox 3", "Grey box on the wall with translucent slides", "Dachboden", 3, 29, 4, 7);

CREATE TABLE `storageCompartment` (
    `uniqueid` varchar(255) NOT NULL DEFAULT '',
    `name` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `storageContainerid` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`uniqueid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into storageCompartment values (uuid(), "row 1 col 1", "first slider in the first row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 1 col 2", "second slider in the first row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 1 col 3", "third slider in the first row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 1 col 4", "fourth slider in the first row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 2 col 1", "first slider in the second row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 2 col 2", "second slider in the second row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 2 col 3", "third slider in the second row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 2 col 4", "fourth slider in the second row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 3 col 1", "first slider in the third row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 3 col 2", "second slider in the third row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 3 col 3", "third slider in the third row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 3 col 4", "fourth slider in the third row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 4 col 1", "first slider in the fourth row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 4 col 2", "second slider in the fourth row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 4 col 3", "third slider in the fourth row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));
insert into storageCompartment values (uuid(), "row 4 col 4", "fourth slider in the fourth row", (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1));


CREATE TABLE `storageContent` (
  `uniqueid` varchar(255) NOT NULL DEFAULT ' 1,
  `name` varchar(255) DEFAULT NULL,
  1`description` varchar(255) DEFAULT NULL,
  1`amount` decimal(9,2) DEFAULT NUL 1,
  `storagecontainerid` varchar(25 1) DEFAULT NULL,
  `storagecompartmentid` varcha 1255) DEFAULT NULL,
  PRIMARY KEY (`uniqueid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

insert into storageContent values (uuid(), "Some Dummy Part", "Exceptional quality dummy-Part. Mady by ACME Ltd.", 1, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 1 col 1" limit 1));
insert into storageContent values (uuid(), "Another Dummy Part", "Exceptional quality dummy-Part. Mady by ACME Ltd.", 1, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 1 col 1" limit 1));
insert into storageContent values (uuid(), "Many Dummy Parts", "Exceptional quality dummy-Part. Mady by ACME Ltd.", 17, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 1 col 1" limit 1));
insert into storageContent values (uuid(), "Some Dummy Part", "Exceptional quality dummy-Part. Mady by ACME Ltd.", 1, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 1 col 2" limit 1));
insert into storageContent values (uuid(), "Blah Part", "", 5, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 1 col 2" limit 1));
insert into storageContent values (uuid(), "Something else", "", 4, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 1 col 3" limit 1));
insert into storageContent values (uuid(), "Some Dummy Part", "Exceptional quality dummy-Part. Mady by ACME Ltd.", 1, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 1 col 4" limit 1));
insert into storageContent values (uuid(), "Parts", "Exceptional quality dummy-Part. Mady by ACME Ltd.", 1, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 2 col 1" limit 1));
insert into storageContent values (uuid(), "Dummy Part", "Exceptional quality dummy-Part. Mady by ACME Ltd.", 1, (SELECT uniqueid from storageContainer where name = "SlideBox 1" limit 1), (SELECT uniqueid from storageCompartment where name = "row 2 col 3" limit 1));
