create table MEMBER (
    MemberId INT NOT NULL AUTO_INCREMENT,
    EmailId varchar(100) NOT NULL,
    FirstName varchar(50) NOT NULL,
    LastName varchar(50) NOT NULL,
    Password varchar(50) NOT NULL,
    PRIMARY KEY (MemberId),
    UNIQUE KEY (EmailId)
    );

# Create KITCHENIMAGE
create table KITCHENIMAGE (
    KitchenImageId INT NOT NULL AUTO_INCREMENT,
    MemberId INT NOT NULL,
    ImageIndex INT NOT NULL,
    Image MEDIUMBLOB NOT NULL,
    PRIMARY KEY (KitchenImageId),
    FOREIGN KEY (MemberId) REFERENCES MEMBER(MemberId) on delete cascade,
    UNIQUE KEY (MemberId, ImageIndex)
    );
