DROP DATABASE if exists FOODIE;
CREATE DATABASE FOODIE;
USE FOODIE;

CREATE TABLE restaurant (
	restaurant_id int AUTO_INCREMENT,
	USER_ID int,
	restaurant_name varchar(20) NOT NULL,
	restaurant_contact varchar(30) NOT NULL,
	restaurant_type varchar(10) NOT NULL,
	address_line1 varchar(20) NOT NULL,
	area varchar(15) NOT NULL,
	city varchar(15) NOT NULL,
	res_state varchar(17) NOT NULL,
	pincode int NOT NULL,
	approval_status varchar(10) not null,
	avg_rating double,
	photo_urls varchar(200) not null,
	constraint FOODIE_RESTAURANT_Pk primary key ( RESTAURANT_ID )
);


-- INSERT CODE TO RESTAURANT TABLE
INSERT INTO restaurant VALUES (1,102,'KFC','9823414141','Nonveg','23, Shastri Nagar','Baner','Pune','Maharashtra',411041,'Accepted',4.2,'assets/kfca1.jpg-assets/kfca2.jpg-assets/kfca3.jpg');
INSERT INTO restaurant VALUES (2,102,'KFC','8934217843','Nonveg','3, Gajanan Nagar','Kothrud','Pune','Maharashtra',411038,'Accepted',4.2,'assets/kfcb1.jpg-assets/kfcb2.jpg-assets/kfcb3.jpg');
INSERT INTO restaurant VALUES (3,104,'Pizza Hut','8784393421','Veg','21, Adalat road','Rajouri Garden','Delhi','Delhi',110027,'Accepted',4.5,'assets/pizzahut1.jpg-assets/pizzahut2.jpg-assets/pizzahut3.jpg');
INSERT INTO restaurant VALUES (4,104,'Master Kitchen','8777772771','Nonveg','52, Sandesh road','Vasant Vihar','Delhi','Delhi',110057,'Accepted',4.1,'assets/masterkitchen1.jpg-assets/masterkitchen2.jpg-assets/masterkitchen3.jpg');
INSERT INTO restaurant VALUES (5,104,'Diamond Cafe','8977772771','Veg','11, Bandana circle','Vasant Vihar','Delhi','Delhi',110057,'Accepted',4.3,'assets/cafe1.jpg-assets/cafe2.jpg-assets/cafe3.jpg');
INSERT INTO restaurant VALUES (6,104,'Empire Restaurant','9877226354','Veg','2, Amol Complex','Vasant Vihar','Delhi','Delhi',110057,'Accepted',4.3,'assets/empire1.jpg-assets/empire2.jpg');
-- INSERT INTO restaurant VALUES (7,102,'Barbeque Nation','8823414141','Nonveg',33,'Mayura Circle','Baner','Pune','Maharashtra',411041,'Pending',4.2,'assets/kfca3.jpg-assets/kfca2.jpg-assets/kfca1.jpg');
-- INSERT INTO restaurant VALUES (8,102,'Kamat','7823414141','Nonveg',3,'Madge Circle','Kothrud','Pune','Maharashtra',411038,'Pending',1.0,'assets/kfca2.jpg-assets/kfca1.jpg-assets/kfca3.jpg');



CREATE TABLE users (

	USER_ID INT AUTO_INCREMENT,
	USER_NAME VARCHAR(20) NOT NULL,
	EMAIL_ID VARCHAR(30) NOT NULL,
	CONTACT_NUMBER VARCHAR(10) NOT NULL,
	PASSWORD VARCHAR(70) NOT NULL,  
	constraint FOODIE_USERS_Pk primary key ( USER_ID )
);

CREATE TABLE roles(
ROLE_ID INT AUTO_INCREMENT,
ROLE_TYPE VARCHAR(10) not null,
USER_ID INT,
constraint FOODIE_ROLES_Pk primary key (ROLE_ID)
);

create table wallet (
	wallet_id int AUTO_INCREMENT,
	user_id int NOT NULL,
	available_amount int(10) not null,
	constraint CIBO_wallet_pk primary key (wallet_id)
);

CREATE TABLE coupon (
	coupon_id int AUTO_INCREMENT,
	coupon_code varchar(10) NOT NULL,
	minimum_bill int NOT NULL,
	maximum_redemption int NOT NULL,
	start_date datetime default CURRENT_TIMESTAMP,
	end_date datetime default CURRENT_TIMESTAMP,
	constraint FOODIE_COUPON_Pk primary key ( COUPON_ID )
);

create table dish (
	dish_id int AUTO_INCREMENT,
	restaurant_id int,
	dish_name varchar(30) not null,
	dish_cuisine varchar(20) not null,
	dish_type varchar(10) not null,
	dish_description varchar(150) not null,
	speciality varchar(30) not null,
	price double not null,
	avg_rating double not null,
	image_url varchar(50) not null,
	constraint FOODIE_dish_pk primary key (dish_id)
	
);

create table dish_rating (
	dish_rating_id int AUTO_INCREMENT,
	dish_id int,
	user_id int NOT NULL,
	rating int default 0,
	constraint FOODIE_dish_rating_fk primary key (dish_rating_id)
);

CREATE TABLE restaurant_transaction(
	restaurant_transaction_id int AUTO_INCREMENT,
	restaurant_id int,
	restaurant_order_counter int NOT NULL,
	restaurant_approx_cost int NOT NULL,
	restaurant_status boolean DEFAULT false,
	constraint FOODIE_RESTAURANT_T_Pk primary key (restaurant_transaction_id)
);


