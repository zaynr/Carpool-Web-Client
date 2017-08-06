package com.zayn.repo;

import com.zayn.bean.DriverServes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface DriverServeReppository extends CrudRepository<DriverServes, Long> {

    @Query(value = "SELECT * FROM DriverServes WHERE rec_mobile_num = :rec_mobile_num", nativeQuery = true)
    ArrayList<DriverServes> getFriendByDriverMobileNum(@Param("rec_mobile_num") String rec_mobile_num);

    @Modifying
    @Query(value = "UPDATE DriverServes SET serve_count = serve_count + 1 WHERE rec_mobile_num = :rec_mobile_num" +
            " AND call_serial = :call_serial", nativeQuery = true)
    void updateDriverServeTime(@Param("rec_mobile_num") String rec_mobile_num, @Param("call_serial") String call_serial);

    @Modifying
    @Query(value = "INSERT INTO DriverServes(rec_mobile_num, call_serial, serve_count, call_mobile_num, call_name) VALUES (:rec_mobile_num," +
            " :call_serial, 1, :call_mobile_num, :call_name)", nativeQuery = true)
    void updateDriverServeMan(@Param("rec_mobile_num") String rec_mobile_num, @Param("call_serial") String call_serial
            ,@Param("call_name") String call_name, @Param("call_mobile_num") String call_mobile_num);
}
