SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS client_auth;
DROP TABLE IF EXISTS ride;
DROP TABLE IF EXISTS taxi;
SET FOREIGN_KEY_CHECKS = 1;

create table if not exists client (
	id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    money_spent DECIMAL(11),
    PRIMARY KEY(id_client)
    );

create table if not exists client_auth (
	id INT(11) NOT NULL AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_client_auth)
    );
    
create table if not exists taxi (
	id INT(11) NOT NULL AUTO_INCREMENT,
    car_type VARCHAR(100) NOT NULL,
    state_number VARCHAR(100) NOT NULL,
    driver_name VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_taxi)
    );

create table if not exists ride (
	id INT(11) NOT NULL AUTO_INCREMENT,
    cost DECIMAL(11) NOT NULL,
    distance DOUBLE(11,11) NOT NULL,
    PRIMARY KEY(id_ride)
    );
    
ALTER TABLE client
	ADD CONSTRAINT client_auth_fk FOREIGN KEY (client_auth_id) REFERENCES client_aut (id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION;
    
ALTER TABLE ride
	ADD CONSTRAINT client_fk FOREIGN KEY (client_id) REFERENCES client (id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION;

ALTER TABLE ride
	ADD CONSTRAINT taxi_fk FOREIGN KEY (taxi_id) REFERENCES taxi (id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION;

ALTER TABLE client AUTO_INCREMENT 1;
ALTER TABLE client_auth AUTO_INCREMENT 1;
ALTER TABLE taxi AUTO_INCREMENT 1;
ALTER TABLE ride AUTO_INCREMENT 1;

INSERT INTO client_auth (login, password) VALUES
	("ivan", 1111),
    ("petr", 2222),
    ("denis", 3333),
    ("vasya", 4444), 
    ("alex", 5555);
    
INSERT INTO client (name, client_auth_id) VALUES
	("Ivan", 1),
     ("Petr", 2),
    ("Denis", 3),
    ("Vasya", 4), 
    ("Alex", 5);
    
INSERT INTO taxi (car_type, state_number, driver_name) VALUES
	("Standart", "AA1010BK", "Oleg"),
    ("Business", "AA2020BK", "Roma"),
    ("Comfort", "AA3030BK", "Maxim"),
    ("Wagon", "AA4040BK", "Vadim"),
    ("Van", "AA5050BK", "Artem");
