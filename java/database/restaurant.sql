DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS thumbs_up;
DROP TABLE IF EXISTS thumbs_down;
DROP TABLE IF EXISTS restaurant_days;
DROP TABLE IF EXISTS invitations;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS days;




/***************************************/
/***************************************/
CREATE TABLE invitations(
invitation_id serial NOT NULL,
owner_id int NOT NULL,
city varchar(200),
zip_code varchar(5),
selection int,
invitation_date timestamp,
invitation_link varchar(200) NOT NULL,
CONSTRAINT PK_invitation_id PRIMARY KEY (invitation_id)
);
/***************************************/
/***************************************/
CREATE TABLE restaurants(
restaurant_id serial NOT NULL,
name varchar(200) NOT NULL,
img_url varchar(200),
rating float,
type varchar(50) NOT NULL,
address1 varchar(200) NOT NULL,
address2 varchar(200),
address3 varchar(200),
city varchar(100) NOT NULL,
state varchar(50) NOT NULL,
zip_code varchar(5) NOT NULL,
phone varchar(10),
open_hour varchar(5),
close_hour varchar(5),
CONSTRAINT PK_restaurant_id PRIMARY KEY (restaurant_id)
);
/***************************************/
/***************************************/
CREATE TABLE days(
day_id serial NOT NULL,
day_name varchar(20),
CONSTRAINT PK_day_id PRIMARY KEY (day_id)
);
/***************************************/
/***************************************/
CREATE TABLE restaurant_days(
restaurant_id int NOT NULL,
dayfrom_id int NOT NULL,
dayto_id int NOT NULL,
timeopen varchar(20) NOT NULL,
CONSTRAINT PK_restaurant_days PRIMARY KEY (restaurant_id,dayfrom_id,dayto_id),
CONSTRAINT FK_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
CONSTRAINT FK_dayfrom_id FOREIGN KEY (dayfrom_id) REFERENCES days(day_id),
CONSTRAINT FK_dayto_id FOREIGN KEY (dayto_id) REFERENCES days(day_id)
);
/***************************************/
/***************************************/
CREATE TABLE thumbs_up(
thumbs_up_id serial NOT NULL,
invitation_id int NOT NULL,
restaurant_id int NOT NULL,
CONSTRAINT PK_thumbs_up_id PRIMARY KEY (thumbs_up_id),
CONSTRAINT FK_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
CONSTRAINT FK_invitation_id FOREIGN KEY (invitation_id) REFERENCES invitations(invitation_id)
);
/***************************************/
/***************************************/
/***************************************/
/***************************************/

INSERT INTO days(day_name)
VALUES
('Monday'),
('Tuesday'),
('Wednesday'),
('Thursday'),
('Friday'),
('Saturday'),
('Sunday');

/***************************************/
/***************************************/
CREATE TABLE vote(
restaurant_id int NOT NULL,
invite_id int  NOT NULL,
thumbs_up int,
thumbs_down int,
CONSTRAINT PK_vote PRIMARY KEY (restaurant_id, invite_id),
CONSTRAINT FK_restaurant_id  FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
CONSTRAINT FK_invite_id  FOREIGN KEY (invite_id) REFERENCES invitations(invitation_id)
);




