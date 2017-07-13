package com.zayn.repo;

import com.zayn.bean.Orders;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zengzy19585 on 2017/7/13.
 */
@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Orders, Long>{
    @Query(value = "SELECT * FROM Orders WHERE serial_num = :serial_num", nativeQuery = true)
    Orders findBySerial_num(@Param("serial_num")String serial_num);

    @Query(value = "SELECT * FROM Orders WHERE rec_mobile_num = :rec_mobile_num", nativeQuery = true)
    List<Orders> findByRec_mobile_num(@Param("rec_mobile_num")String rec_mobile_num);

    @Query(value = "SELECT * FROM Orders WHERE call_serial = :call_serial", nativeQuery = true)
    List<Orders> findByCall_serial(@Param("call_serial")String call_serial);
}