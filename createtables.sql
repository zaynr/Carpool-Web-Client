use users;

DROP TABLE Customers;
DROP TABLE Drivers;
DROP TABLE Friends;
DROP TABLE Orders;

CREATE TABLE Customers(
    user_name varchar(20),          
    serial_num int,                                                     #工号
    sex varchar(5),                                                     #性别
    mobile_number varchar(15),                                          #手机号
    pwd varchar(255),                                                   #密码
    primary key (serial_num)
);
CREATE TABLE Drivers(
    driver_name varchar(255),
    mobile_number varchar(15),                                          #手机号
    pwd varchar(255),                                                   #密码
    car_plate varchar(15),                                              #车牌号
    lat DOUBLE,                                                         #司机当前纬度
    lng DOUBLE,                                                         #司机当前经度
    primary key (mobile_number)
);
CREATE TABLE Friends(
    serial_num int primary key not  null  auto_increment,
    userial1 int,
    userial2 int
);
CREATE TABLE Orders(
    serial_num int primary key not  null  auto_increment,               #订单号
    call_serial int,                                                    #下订单顾客工号
    rec_mobile_num int,                                                 #接单司机手机号
    call_type tinyint,                                                  #订单类型，（0：立即，1：预约）
    apt_time DATETIME,                                                  #预约时间
    ori_lat	float,                                                      #起点纬度
    ori_lng	float,                                                      #起点经度
    des_lat	float,                                                      #终点纬度
    des_lng	float,                                                      #终点经度
    status tinyint                                                      #是否完成，（0：未完成，1：已完成）
);