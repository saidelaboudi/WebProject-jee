---------------------------------------
-- GROUP TABLE 
---------------------------------------

CREATE TABLE `group` (
  `idGroup` int NOT NULL,
  `createdDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nameGroup` varchar(45) NOT NULL,
  `idUser` int DEFAULT NULL,
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


---------------------------------------
-- GROUP USER
---------------------------------------
CREATE TABLE `user` (
  `idUser` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `createdDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
		-- DATA
	user VALUES (3 , 'EL ABOUDI' , 'Said' , 'adminSaid' , 'admin' , NOW() );
	user VALUES (1 , 'Faleh' , 'Yasser' , 'adminYasser' , 'admin' , NOW() );
	user VALUES (2 , 'Sefiat' , 'Amine' , 'adminAmine' , 'admin' , NOW() );

---------------------------------------
-- TEAM TABLE 
--------------------------------------
CREATE TABLE `team` (
  `idTEAM` int NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `idGroup` int NOT NULL,
  PRIMARY KEY (`idTEAM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci