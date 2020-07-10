DROP TABLE If EXISTS Product;

create table Product(
  pid INT AUTO_INCREMENT  PRIMARY KEY,
  pname VARCHAR(250) NOT NULL,
  pamount INT NOT NULL
);


insert into Product (pname, pamount) VALUES('Candy', 5);
insert into Product (pname, pamount) VALUES('Chocolates', 10);
insert into Product (pname, pamount) VALUES('Cooldrink', 20);