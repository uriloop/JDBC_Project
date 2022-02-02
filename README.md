# JDBC_Project

Projecte de classe JDBC Postgresql driver






------------------------------------------------------------------------------------------------------
INFO variada:

vagrant file in:         Documents/vagrant_JDBC
database name:           biblioteca
database user:           usuario
db user password:        password


creates:
 
create table pimientos (id smallint primary key unique, nombre varchar(20),descripcion text, familia varchar(10),origen varchar(20), img varchar(50));
create table caracteristicas (id smallint primary key unique, altura_max smallint, altura_min smallint, ancho_max smallint, ancho_min smallint, 
  scoville_max integer, scoville_min integer, dies_cult_max smallint, dies_cult_min smallint, color varchar(20),rendimiento varchar(20));
create table cultivo (id smallint primary key unique, prof_semilla decimal, dist_semillas decimal, dist_plantas smallint, temp_cre_max smallint,
  temp_cre_min smallint , temp_germ_max smallint,temp_germ_min smallint , luz varchar(10));



