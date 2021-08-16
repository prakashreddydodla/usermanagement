CREATE database KLMPOSDB tablespace pg_Default;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop table if exists public.statedefinition;

CREATE TABLE public.statedefinition (
	stategroup character varying(28),
	stateid integer,
    statename    character varying(28),
    uniqueid UUID default uuid_generate_v4() primary key,
    creationdate date default current_timestamp,
    lastmodified date default current_timestamp
);

insert into statedefinition(stategroup,stateid,statename) values('OrderStatus',1,'new');
insert into statedefinition(stategroup,stateid,statename) values('OrderStatus',2,'pending');
insert into statedefinition(stategroup,stateid,statename) values('OrderStatus',3,'created');
insert into statedefinition(stategroup,stateid,statename) values('OrderStatus',4,'cancelled');
insert into statedefinition(stategroup,stateid,statename) values('OrderStatus',5,'rejected');
insert into statedefinition(stategroup,stateid,statename) values('OrderStatus',6,'returned');
insert into statedefinition(stategroup,stateid,statename) values('UserStatus',1,'active');
insert into statedefinition(stategroup,stateid,statename) values('UserStatus',2,'inactive');
insert into statedefinition(stategroup,stateid,statename) values('UserStatus',3,'disabled');
insert into statedefinition(stategroup,stateid,statename) values('DeliverySlipStatus',1,'new');
insert into statedefinition(stategroup,stateid,statename) values('DeliverySlipStatus',2,'order_created');
insert into statedefinition(stategroup,stateid,statename) values('DeliverySlipStatus',3,'noorder_created');

drop table if exists public.domaindata;

CREATE TABLE public.domaindata (
	domainname character varying(28),
	description character varying(38),
    uniqueid UUID default uuid_generate_v4() primary key, 
    creationdate date default current_timestamp,
    lastmodified date default current_timestamp
);

insert into domaindata(domainname,description) values('application','kalamandir organization');
insert into domaindata(domainname,description) values('anonymous','other than kalamandir organization');



drop table if exists public.userdata;
CREATE TABLE public.userdata (
	userName character varying(28),
	password character(100),
	status integer,
	phonenumber integer,
    gender char(1) ,
    dateofbirth date,
    gstnumber integer,
    email character varying(100),
	domaindatauuid UUID references domaindata(uniqueID) ,
    uniqueID UUID default uuid_generate_v4() primary key, 
    creationdate date default current_timestamp,
    lastmodified date default current_timestamp
);

insert into public.userdata(userName,password,status,phonenumber,gender,dateofbirth,gstnumber,email,domaindatauuid)
  values ('bhaskara','ilovedatabase',1,'55555555','M',
  '28-10-1980','12345678','bhaskara.bangaru@otsi.co.in',(select uniqueid from domaindata where domainname='application'));

insert into public.userdata(userName,password,status,phonenumber,gender,dateofbirth,gstnumber,email,domaindatauuid)
  values ('niranjan','ilovemgmt',1,'66666666','M',
  '28-10-1980','12345678','bhaskara.bangaru@otsi.co.in',(select uniqueid from domaindata where domainname='anonymous'));

  

drop table if exists public.userdata_av;
CREATE TABLE public.userdata_av (
	ownerid UUID references userdata(uniqueID),
	type integer,
	name character varying(128),
	intvalue integer,
	stringvalue character varying(128),
	datevalue character varying(128),
	lastmodified timestamp default current_timestamp
  );


insert into userdata_av(ownerid,type,name,datevalue)
values
((select uniqueid from userdata where username='niranjan'),3,'lastvisited',current_timestamp);

insert into userdata_av(ownerid,type,name,stringvalue)
values
((select uniqueid from userdata where username='bhaskara'),3,'password','neerajchopra');


drop table if exists deliveryslip;

CREATE TABLE public.deliveryslip (
	dsNumber character varying(28),
	status integer,
	salesManID UUID references userdata(uniqueID),
    uniqueID UUID default uuid_generate_v4() primary key,
    creationdate timestamp default current_timestamp,
    lastmodified timestamp default current_timestamp
);

insert into deliveryslip(dsNumber,status,salesManID) values ('ds00001',1,(select uniqueID from userdata where username='bhaskara'));
insert into deliveryslip(dsNumber,status,salesManID) values ('ds00002',1,(select uniqueID from userdata where username='bhaskara'));
insert into deliveryslip(dsNumber,status,salesManID) values ('ds00003',1,(select uniqueID from userdata where username='bhaskara'));

drop table if exists lineitems;
CREATE TABLE public.Lineitems (
	dsuuid UUID references deliveryslip(uniqueID),
	itemsku character varying(28),
	itemprice decimal(12,2),
	quantity integer,
	grossvalue decimal(12,2),
	discount decimal(12,2),
	netvalue decimal(12,2),
    uniqueid UUID default uuid_generate_v4() primary key,
    creationdate timestamp default current_timestamp,
    lastmodified timestamp default current_timestamp
);

insert into lineitems(dsuuid,itemsku,itemprice,quantity,grossvalue,discount,netvalue)
values
((select uniqueID from deliveryslip where dsNumber='ds00001'),'I000001',999.00,2,1998.00,0,1998.00 );

insert into lineitems(dsuuid,itemsku,itemprice,quantity,grossvalue,discount,netvalue)
values
((select uniqueID from deliveryslip where dsNumber='ds00002'),'I000002',599.00,3,1797.00,0,1797.00 );