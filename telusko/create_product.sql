CREATE TABLE product(
	id INT PRIMARY KEY,
	name VARCHAR(50),
	price DECIMAL(6, 2)
);

INSERT INTO product(id, name, price)
VALUES
	(1, 'Hammer', 8.99),
	(2, 'Towel', 11.00),
	(3, 'Oat Milk', 4.29);