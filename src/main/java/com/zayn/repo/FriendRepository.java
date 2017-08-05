package com.zayn.repo;

import com.zayn.bean.Friends;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by zaynr on 2017/7/14.
 */
@Repository
@Transactional
public interface FriendRepository extends CrudRepository<Friends, Long> {
    @Query(value = "SELECT * FROM Friends WHERE userial1 = :serial_num OR userial2 = :serial_num"
            , nativeQuery = true)
    ArrayList<Friends> findByUserial(@Param("serial_num") int serial_num);

    @Query(value = "SELECT * FROM Friends WHERE (userial1 = :userial1 AND userial2 = :userial2) OR (userial1 = :userial2 AND userial2 = :userial1) LIMIT 1", nativeQuery = true)
    Friends findPerfectMatch(@Param("userial1") int userial1, @Param("userial2") int userial2);

    @Modifying
    @Query(value = "DELETE FROM Friends WHERE (userial1 = :userial1 AND userial2 = :userial2) OR (userial1 = :userial2 AND userial2 = :userial1)", nativeQuery = true)
    void deletePerfectMatch(@Param("userial1") int userial1, @Param("userial2") int userial2);

    @Query(value = "SELECT * FROM DriverServe WHERE rec_mobile_num = :rec_mobile_num", nativeQuery = true)
    Friends getFriendByDriverMobileNum(@Param("rec_mobile_num") String rec_mobile_num);

    @Modifying
    @Query(value = "UPDATE DriverServe SET serve_count = serve_count + 1 WHERE rec_mobile_num = :rec_mobile_num" +
            " AND call_serial = :call_serial", nativeQuery = true)
    void updateDriverServeTime(@Param("rec_mobile_num") String rec_mobile_num, @Param("call_serial") String call_serial);

    @Modifying
    @Query(value = "INSERT INTO DriverServe(rec_mobile_num, call_serial, serve_count) VALUES (:rec_mobile_num," +
            " :call_serial, 1)", nativeQuery = true)
    void updateDriverServeMan(@Param("rec_mobile_num") String rec_mobile_num, @Param("call_serial") String call_serial);
}
