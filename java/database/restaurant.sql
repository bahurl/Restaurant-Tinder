DROP TABLE IF EXISTS invitations;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS days;
DROP TABLE IF EXISTS restaurant_days;
DROP TABLE IF EXISTS thumbs_up;


/***************************************/
/***************************************/
CREATE TABLE invitations(
invitation_id serial NOT NULL,
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
restaurant_id serial NOT NULL,
name varchar(200) NOT NULL,
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
day_id int NOT NULL,
CONSTRAINT PK_restaurant_days PRIMARY KEY (restaurant_id,day_id),
CONSTRAINT FK_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
CONSTRAINT FK_day_id FOREIGN KEY (day_id) REFERENCES days(day_id)
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
('Tavola','Pizza','488 9th Ave', 'New York','NY','10018','7304468945','9:00','19:00'),
('Baskin Robins','Burger','123 Main Ave','Port Charlotte','FL','33980','9413911150','11:00','22:00'),
('Mint Leaf','Thai','1456 South Ave','Port Charlotte','FL','33980','9413915587','11:00','16:00'),
('Chinos','Ramen','982 5th Ave','New York','NY','10018','7304468952','16:00','02:00'),
('Rickys','Colombian','359 12th Ave','New York','NY','10018','7304468957','07:00','17:00'),
('Late Night Study','Chinese','1568 Highland Ave','Port Charlotte','FL','33980','9149154682','17:00','03:00'),
('Brekkie','Egg','125 3rd Ave','New York','NY','10018','7304469857','06:00','14:00');



