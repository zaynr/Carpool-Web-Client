package com.zayn.repo;

import com.zayn.bean.Orders;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zengzy19585 on 2017/7/13.
 */
@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Orders, Long>{
    @Query(value = "SELECT * FROM Orders WHERE serial_num = :serial_num", nativeQuery = true)
    Orders findBySerial_num(@Param("serial_num")int serial_num);

    @Query(value = "SELECT * FROM Orders WHERE rec_mobile_num = :rec_mobile_num", nativeQuery = true)
    List<Orders> findByRec_mobile_num(@Param("rec_mobile_num")String rec_mobile_num);

    @Query(value = "SELECT * FROM Orders WHERE call_serial = :call_serial", nativeQuery = true)
    List<Orders> findByCall_serial(@Param("call_serial")String call_serial);

    @Query(value = "SELECT * FROM Orders WHERE status = 0", nativeQuery = true)
    List<Orders> findAllUndone();

    @Modifying
    @Query(value = "UPDATE Orders SET status = 1, rec_mobile_num = :rec_mobile_num WHERE serial_num = :serial_num", nativeQuery = true)
    void recOrder(@Param("serial_num") int serial_num, @Param("rec_mobile_num")String rec_mobile_num);

    @Modifying
    @Query(value = "UPDATE Orders SET status = 0, rec_mobile_num = '' WHERE serial_num = :serial_num", nativeQuery = true)
    void cancleOrder(@Param("serial_num") int serial_num);

    @Modifying
    @Query(value = "UPDATE Orders SET customer_comment = :comment, rating = :rating WHERE serial_num = :serial_num", nativeQuery = true)
    void finishOrder(@Param("comment") String comment, @Param("rating") float rating, @Param("serial_num") int serial_num);
}
