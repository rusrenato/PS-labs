CREATE TABLE zbor(
id INT(6) UNSIGNED auto_increment PRIMARY KEY,
departure VARCHAR(30) NOT NULL,
arrival VARCHAR(30) NOT NULL,
company VARCHAR(30) NOT NULL
);

CREATE TABLE userr(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nume VARCHAR(30) NOT NULL,
prenume VARCHAR(30) NOT NULL,
username VARCHAR(30) NOT NULL,
parola VARCHAR(60) NOT NULL,
rol VARCHAR(30) NOT NULL,
money INT(6) NOT NULL
);

CREATE TABLE bilet(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
id_zbor INT(6) UNSIGNED NOT NULL,
id_user INT(6) UNSIGNED NOT NULL,
price INT(6) NOT NULL,
seat_nr INT(6) ,
FOREIGN KEY(id_zbor) REFERENCES zbor(id) ,
FOREIGN KEY(id_user) REFERENCES userr(id) 
);
