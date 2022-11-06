BEGIN TRANSACTION;
DROP TABLE IF EXISTS invitations;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS days;
DROP TABLE IF EXISTS restaurant_days;
DROP TABLE IF EXISTS thumbs_up;

DROP SEQUENCE IF EXISTS seq_invitation_id;
DROP SEQUENCE IF EXISTS seq_restaurant_id;
DROP SEQUENCE IF EXISTS seq_day_id;
DROP SEQUENCE IF EXISTS seq_thumbs_up_id;

CREATE SEQUENCE seq_invitation_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_restaurant_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_day_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_thumbs_up_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
/***************************************/
/***************************************/
CREATE TABLE invitations (
invitation_id int DEFAULT nextval('seq_invitation_id'::regclass) NOT NULL,
owner_id int NOT NULL,
city varchar(200),
zip_code varchar(5),
selection int,
invitation_link varchar(200) NOT NULL,
CONSTRAINT PK_invitation_id PRIMARY KEY (invitation_id)
);
/***************************************/
/***************************************/
CREATE TABLE restaurants(
restaurant_id int DEFAULT nextval('seq_restaurant_id'::regclass) NOT NULL,
name varchar(200) NOT NULL,
type varchar(50) NOT NULL,
address1 varchar(200) NOT NULL,
address2 varchar(200) NOT NULL,
address3 varchar(200) NOT NULL,
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
day_id int DEFAULT nextval('seq_day_id'::regclass) NOT NULL,
day_name varchar(20),
CONSTRAINT PK_days_id PRIMARY KEY (day_id)
);
/***************************************/
/***************************************/
CREATE TABLE restaurant_days(
restaurant_id int NOT NULL,
day_id int NOT NULL,
CONSTRAINT PK_restaurant_days PRIMARY KEY (restaurant_id,days_id),
CONSTRAINT FK_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
CONSTRAINT FK_day_id FOREIGN KEY (day_id) REFERENCES days(day_id)
);
/***************************************/
/***************************************/
CREATE TABLE thumbs_up(
thumbs_up_id int DEFAULT nextval('seq_thumbs_up_id'::regclass) NOT NULL,
invitation_id int NOT NULL,
restaurant_id int NOT NULL,
CONSTRAINT PK_thumbs_up_id PRIMARY KEY (thumbs_up_id),
CONSTRAINT FK_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
CONSTRAINT FK_invitation_id FOREIGN KEY (invitation_id) REFERENCES invitations(invitation_id)
);

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

INSERT INTO restaurants(name,type,address1,city,state,zip_code,phone,open_hour,close_hour)
VALUES
('Ginger Dim Sum','Dim Sum','1912 Kings Hwy','Port Charlotte','FL','33980','9413915768','8:30','21:00'),
('Tavola','Pizza','488 9th Ave', 'New York','NY','10018')