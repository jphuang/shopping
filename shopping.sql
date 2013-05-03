--数据库命名全部小写
create database shopping;

use shopping;

create table tbl_user
(
	id int primary key auto_increment,
	username varchar(40),
	password varchar(32),
	phone varchar(40),
	addr varchar(255),
	rdate datetime
);

create table tbl_category
(
	id int primary key auto_increment,
	name varchar(255),
	descr varchar(255),
	pid int,
	isleaf int ,#0表示leaf 1表示非leaf
	grade int
);

create table tbl_product
(
	id int primary key auto_increment,
	name varchar(255),
	descr varchar(255),
	normalprice double,
	memberprice double,
	pdate datetime,
	categoryid int references category(id)
);

create table tbl_salesorder
(
	id int primary key auto_increment,
	userid int,
	addr varchar(255),
	odate datetime,
	status int
);

create table tbl_salesitem
(
	id int primary key auto_increment,
	productid int,
	unitprice double,
	pcount int,
	orderid int
);
