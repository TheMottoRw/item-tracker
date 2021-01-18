CREATE DATABASE declaredDocuments;

CREATE TABLE documents(
	id int primary key auto_increment,
	NationalId varchar(70) not null,
	name varchar(70) not null,
	documentType varchar(80) not null,
	gender varchar(10) not null,
	phone int not null,
	declared_at date default CURRENT_TIMESTAMP
);
