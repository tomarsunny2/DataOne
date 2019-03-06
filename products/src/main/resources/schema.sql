CREATE TABLE Product (
    id   INTEGER      NOT NULL AUTO_INCREMENT ,
    name VARCHAR(50) ,
    price DECIMAL(10,2),
    brand VARCHAR(50),
    onsale Boolean,
    PRIMARY KEY (id)
);
