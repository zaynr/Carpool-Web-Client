use users;

DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Drivers;
DROP TABLE IF EXISTS Friends;
DROP TABLE IF EXISTS FriendRequests;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS DriverServe;

CREATE TABLE Customers(
    user_name nvarchar(20),          
    serial_num int,                                                     #工号
    sex nvarchar(5),                                                     #性别
    mobile_number nvarchar(15),                                          #手机号
    pwd nvarchar(255),                                                   #密码
    primary key (serial_num)
);
CREATE TABLE Drivers(
    driver_name nvarchar(255),
    mobile_number nvarchar(15),                                          #手机号
    pwd nvarchar(255),                                                   #密码
    car_plate nvarchar(15),                                              #车牌号
    rating float,                                                       #司机评分
    lat DOUBLE,                                                         #司机当前纬度
    lng DOUBLE,                                                         #司机当前经度
    fin_deals int,                                                      #已完成订单数
    primary key (mobile_number)
);
CREATE TABLE Friends(
    serial_num int primary key not  null  auto_increment,
    userial1 int,
    userial2 int
);
CREATE TABLE FriendRequests(
    serial_num int primary key not  null  auto_increment,
    userial1 int,
    userial2 int
);
CREATE TABLE DriverServe(
    serial_num int primary key not  null  auto_increment,
    rec_mobile_num nvarchar(15),                                        #接单司机手机号
    call_serial nvarchar(15),                                           #下订单顾客工号
    serve_count int
);
CREATE TABLE Orders(
    serial_num int primary key not  null  auto_increment,               #订单号
    call_serial int,                                                    #下订单顾客工号
    rec_mobile_num nvarchar(15),                                        #接单司机手机号
    call_type tinyint,                                                  #订单类型，（0：立即，1：预约）
    apt_time DATETIME,                                                  #预约时间
    ori_address	nvarchar(255),                                          #起点地址文字描述
    ori_lat	float,                                                      #起点纬度
    ori_lng	float,                                                      #起点经度
    des_address	nvarchar(255),                                          #终点地址文字描述
    des_lat	float,                                                      #终点纬度
    des_lng	float,                                                      #终点经度
    customer_comment nvarchar(255),                                     #乘客评价
    rating int,                                                         #乘客打分
    status tinyint                                                      #是否完成，（0：未接，1：已接，2：已完成）
);