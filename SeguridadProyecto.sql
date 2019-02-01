use mysql;


create user 'anaklusmos'@'localhost' identified by 'anaklusmos.123';

Grant all privileges on PROYECTO.* to 'anaklusmos'@'localhost';

flush privileges;
