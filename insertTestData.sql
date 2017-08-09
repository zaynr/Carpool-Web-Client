USE users;

INSERT INTO Customers(user_name, serial_num, sex, mobile_number,pwd) VALUES(
    "zengzengyu","19585","男","134567893","123456"
);

INSERT INTO Drivers(driver_name,mobile_number,pwd,car_plate,lat,lng) VALUES (
    "driver-1","15656097278","123445","闽E-12345","31.191178","121.173026"
);

INSERT INTO Friends(userial1, userial2) VALUES (
    "19585","19322"
);

INSERT INTO Orders(call_serial, rec_mobile_num, call_type, apt_time, ori_lat, ori_lng, des_lat, des_lng, status) VALUES(
    "19585","15656097278","0","0","30.191178","120.173026","31.191178","121.173026",0
);
