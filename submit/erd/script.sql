use nhn_academy_21;

CREATE TABLE Categories (
                            CategoryID		INT	auto_increment,
                            CategoryName	varchar(50),

                            CONSTRAINT pk_Categories PRIMARY KEY(CategoryID)
);

truncate Categories;
select *from Categories;
INSERT into Categories (CategoryID, CategoryName) VALUES (3, 'Bottom');
select *from Products;


CREATE TABLE Products (
                          ProductID	INT	auto_increment,
                          CategoryID	INT,
                          ModelNumber	nvarchar(10),
                          ModelName	nvarchar(120),
                          ProductImage	nvarchar(30),
                          UnitCost	decimal(15),
                          Description	text,

                          CONSTRAINT pk_Products PRIMARY KEY(ProductID),
                          CONSTRAINT fk_Products_Categories FOREIGN KEY(CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE Customers (
                           CustomerID	int auto_increment,
                           Name	varchar(10),
                           EmailAddress varchar(100)	UNIQUE,
                           Password	varchar(12),

                           CONSTRAINT pk_Customer PRIMARY KEY(CustomerID)
);

CREATE TABLE Reviews (
                         ReviewID	int auto_increment,
                         ProductID	int,
                         CustomerID	int,
                         Rating		int,
                         Comments	text,

                         CONSTRAINT pk_ReviewID PRIMARY KEY(ReviewID),
                         CONSTRAINT fk_Review_Products FOREIGN KEY(ProductID) REFERENCES Products(ProductID),
                         CONSTRAINT fk_Review_Customer FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Orders (
                        OrderID		int auto_increment,
                        CustomerID	int,
                        OrderDate	Datetime,
                        ShipDate	Datetime,

                        CONSTRAINT pk_Orders PRIMARY KEY(OrderID),
                        CONSTRAINT fk_Orders_CustomerID FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE OrderDetails (
                              OrderID		int,
                              ProductID	int,
                              Quantity	int,
                              UnitCost	decimal(15),

                              CONSTRAINT pk_OrderDetails PRIMARY KEY(OrderID, ProductID),
                              CONSTRAINT fk_OrderDetails_Orders FOREIGN KEY(OrderID) REFERENCES Orders(OrderID),
                              CONSTRAINT fk_OrderDetails_Products FOREIGN KEY(ProductID) REFERENCES Products(ProductID)
);


CREATE TABLE ShoppingCart (
                              RecordID	int	auto_increment,
                              CartID		nvarchar(150),
                              Quantity	int,
                              ProductID	int,
                              DateCreateed	Datetime DEFAULT NOW(),

                              CONSTRAINT pk_RecordID PRIMARY KEY(RecordID),
                              CONSTRAINT fk_cart_ProductID FOREIGN KEY(ProductID) REFERENCES Products(ProductID)
);


CREATE TABLE `users` (
                         `user_id` varchar(50) NOT NULL COMMENT '아이디',
                         `user_name` varchar(50) NOT NULL COMMENT '이름',
                         `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                         `user_auth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `user_point` int NOT NULL COMMENT 'default : 1000000',
                         `created_at` datetime NOT NULL COMMENT '가입일자',
                         `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';


INSERT INTO `users` (
    `user_id`, `user_name`, `user_password`, `user_birth`, `user_auth`, `user_point`, `created_at`, `latest_login_at`
) VALUES (
             'admin_user', 'Admin User', 'hashed_admin_password', '19851215', 'ROLE_ADMIN', 1000000, NOW(), NULL
         );

DELETE from users where 1=1;

select *from users;

INSERT INTO Products (CategoryID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
VALUES (111, 'ABC123', 'Product 1', 'image1.jpg', 9.99, 'This is product 1');

SELECT CategoryID FROM Categories WHERE CategoryID = 111;

insert into Categories (CategoryID, CategoryName) values (111, 'categorytest');

insert into Categories (CategoryID, CategoryName) values (111, 'categorytest');


DELETE FROM Products where ProductID >=1;

select *from Products;

DELETE FROM Categories where CategoryID >= 1;

insert into Categories (CategoryID, CategoryName) values (1, 'categoryFirst');

INSERT INTO Products (CategoryID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
VALUES (1, '코트', 'Product 1', 'image1.jpg', 9.99, 'This is product 1');

INSERT INTO Products (CategoryID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
VALUES (1, '코트', 'Product 2', 'image1.jpg', 9.99, 'This is product 2');

INSERT INTO Products (CategoryID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
VALUES (1, '코트', 'Product 1', 'image3.jpg', 9.99, 'This is product 3');

INSERT INTO Products (CategoryID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
VALUES (1, '바지', 'Product 1', 'image4.jpg', 9.99, 'This is product 4');

INSERT INTO Products (CategoryID, ModelNumber, ModelName, ProductImage, UnitCost, Description)
VALUES (1, '바지', 'Product 5', 'image5.jpg', 9.99, 'This is product 5');

