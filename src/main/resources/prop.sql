CREATE TABLE IF NOT EXISTS tb_alpinists(

   alpinist_id serial PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   address VARCHAR(50) NOT NULL,
   age INTEGER NOT NULL
);
INSERT INTO tb_alpinists (name, address, age) VALUES ('Mike', 'Moscow', 21);
INSERT INTO tb_alpinists (name, address, age) VALUES ('Alex', 'Berlin', 24);
INSERT INTO tb_alpinists (name, address, age) VALUES ('Tom', 'London', 35);

SELECT alpinist_id, name, addresses
FROM tb_alpinists
WHERE age BETWEEN 30 AND 50;


