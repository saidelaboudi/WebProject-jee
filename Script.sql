CREATE TABLE Users(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    FirstName Varchar(30),
    LastName Varchar(30),
    Address   Varchar(90),
    Birthday    Varchar(90),
    Email   Varchar(30)
);

CREATE TABLE Team(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    Name    Varchar(20),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    OwnerID INTEGER references Users(ID)
    
);

CREATE TABLE Groups(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME    Varchar(20),
    OwnerID   INTEGER references Users(ID),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    description varchar(100)
);

CREATE TABLE Team_Users(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    TeamID INTEGER references team(ID),
    UsersID INTEGER references student(ID)
);

CREATE TABLE Tag(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    tag Varchar(50)
);

CREATE TABLE Tag_Users(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    TagID INTEGER references Tag(ID),
    UsersID INTEGER references Users(ID)
);

CREATE TABLE Groups_Team(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    GroupsID INTEGER references Groups(ID),
    TeamrID INTEGER references Team(ID)
);