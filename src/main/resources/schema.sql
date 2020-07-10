DROP TABLE If EXISTS Product;

Create Table Product(
  pid INT AUTO_INCREMENT  PRIMARY KEY,
  pname VARCHAR(250) NOT NULL,
  pamount INT NOT NULL
);