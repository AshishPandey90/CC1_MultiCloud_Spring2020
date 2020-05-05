drop database fuzzy_data;
create database fuzzy_data DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;  ;
use fuzzy_data;

create table Profile_t( ID varchar(20) NOT NULL, User_name varchar(20),Password_ varchar(20), Email varchar(35),etc varchar(40), PRIMARY KEY (ID)) ;
create table Order_t( ID varchar(20)  NOT NULL, Order_id varchar(20) NOT NULL ,date_o datetime ,PRIMARY KEY (Order_id) ,
FOREIGN KEY(ID) REFERENCES Profile_t(ID)  );
create table Template(Order_id varchar(20) , Template_id varchar(3)  NOT NULL, CSP varchar(20),De_value varchar(3),PRIMARY KEY (Template_id),FOREIGN KEY (Order_id) REFERENCES Order_t(Order_id));

create table Requirment( Order_id varchar(20),Expertize_rate varchar(1),Performance_requirment varchar(15),Agility_requirment varchar(15),
Cost_requirement varchar(15),Security_requirment varchar(15),FOREIGN KEY (Order_id) REFERENCES Order_t(Order_id));
create table Feedback( Order_id varchar(20), Overall_satisfaction varchar(15) default 0,Helpful_Satisfaction varchar(15) default 0,
One_thing_to_change varchar(300),additional_comments varchar(300) , FOREIGN KEY(Order_id) REFERENCES Order_t(Order_id));
create table Rules(Order_id varchar(20),Rule_amount varchar(2), FOREIGN KEY (Order_id) REFERENCES Order_t(Order_id));
create table Rule(Order_id varchar(20),Rule_id varchar(1) NOT NULL ,Element1 varchar(10) , Valalue1 varchar(10) default 0,
 Element2 varchar(10) , Valalue2 varchar(10) default 0, Element3 varchar(10) , Valalue3 varchar(10) default 0,Element4 varchar(10) , 
 Valalue4 varchar(10) default 0 ,Element5 varchar(10) , Valalue5 varchar(10) default 0, output_CSP varchar(10) ,PRIMARY KEY (Rule_id),  
FOREIGN KEY (Order_id) REFERENCES Order_t(Order_id));
show table status;
show tables;
#insert into Order_t("40" ,"40",CURRENT_TIMESTAMP);
#INSERT INTO Rule(Order_id="40" , Rule_id="555555", Element1="ooooooo",Value1="11111111", Element2="tttttttttt",Value2="2222222",
#Element3="th, th...",Value3="3333333333", Element1="fofoffof",Value1="4444444",Element5="ffffffff",Value5="55555555", output_CSP="tuytuitytiu");

#select Element1 as   Performance.AWS  from Rule FOR JSON PATH 
use  fuzzy_data;
show tables;