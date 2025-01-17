CREATE TABLE lab9ex1.masini (
  nr_matricol INT NOT NULL AUTO_INCREMENT,
  marca VARCHAR(45) NOT NULL,
  anul_fabric INT NOT NULL,
  culoarea VARCHAR(45) NOT NULL,
  nr_km INT NOT NULL,
  PRIMARY KEY (nr_matricol));

insert into masini (nr_matricol,marca,anul_fabric,culoarea,nr_km) values (97-SAV,'Audi',2013,'neagra',200.000);
insert into masini (nr_matricol,marca,anul_fabric,culoarea,nr_km) values(15-GWR,'Seat',2001,'albastra',200.000);
insert into masini (nr_matricol,marca,anul_fabric,culoarea,nr_km) values(70-RSO,'Mazda'2006,'neagra',250.000);
insert into masini (nr_matricol,marca,anul_fabric,culoarea,nr_km) values(02-FBA,'Skoda',2024,'gri',1000);
insert into masini (nr_matricol,marca,anul_fabric,culoarea,nr_km) values(13-RRR,'Audi',2014,'gri',210.000);