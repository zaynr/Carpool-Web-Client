USE users;

INSERT INTO customer(name, serial_num, sex, mobile_number,pwd) VALUES(
    "zengzengyu","19585","男","15658027823","123456"
);

INSERT INTO driver(name,mobile_number,pwd,car_plate) VALUES (
    "driver-1","15656097278","123445","闽E-12345"
);

INSERT INTO friend(userial1, userial2) VALUES (
    "19585","19322"
);

INSERT INTO orders(call_serial, rec_mobile_num, call_type, apt_time, ori_lat, ori_lng, des_lat, des_lng, status) VALUES(
    "19585","15656097278","0","0","30.191178","120.173026","31.191178","121.173026",0
);