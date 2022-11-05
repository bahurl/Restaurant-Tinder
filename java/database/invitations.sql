BEGIN TRANSACTION;
DROP TABLE IF EXISTS invitations;

DROP SEQUENCE IF EXISTS seq_invitation_id;

CREATE SEQUENCE seq_invitation_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE invitations (
invitation_id int DEFAULT nextval('seq_invitation_id'::regclass) NOT NULL,
owner_id int NOT NULL,
location varchar(200),
zip_code varchar(5),
invitation_link varchar(200) NOT NULL
CONSTRAINT PK_invitation_id PRIMARY KEY (invitation_id)
);

