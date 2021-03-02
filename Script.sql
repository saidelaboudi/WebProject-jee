CREATE TABLE Users(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    FirstName Varchar(30),
    LastName Varchar(30),
    Address   Varchar(90),
    Password    Varchar(90), # pour connecter au compte
    Email   Varchar(30),  # pour connecter au compte
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Team(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    Name    Varchar(20),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    OwnerID INTEGER references Users(ID)
);

CREATE TABLE Channel(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    Name    Varchar(20),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    TeamID INTEGER references Team(ID)
);

CREATE TABLE Chat(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    ChannelID INTEGER references Channel(ID)
);

CREATE TABLE Message(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    Message    Varchar(100),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    SenderID INTEGER references User(ID),
    ChannelID INTEGER references Chat(ID)
);

CREATE TABLE File(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    Name    Varchar(20),
    Path    Varchar(20),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    ChannelID INTEGER references Channel(ID)
);

CREATE TABLE Groups(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME    Varchar(20),
    OwnerID   INTEGER references Users(ID),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    description varchar(100)
);

CREATE TABLE Tag(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    tag Varchar(50),
    owner INTEGER references Users(ID)
);

CREATE TABLE Team_Users(
    ID  INTEGER PRIMARY KEY AUTO_INCREMENT,
    TeamID INTEGER references Team(ID),
    UsersID INTEGER references Users(ID)
);

CREATE TABLE Tag_Users(
    TagID INTEGER references Tag(ID),
    UsersID INTEGER references Users(ID),
    PRIMARY KEY ( TagID,UsersID )
);

CREATE TABLE Groups_Team(
    GroupsID INTEGER references Groups(ID),
    TeamID INTEGER references Team(ID),
    PRIMARY KEY (GroupsID,TeamID)
);
