package com.zayn.repo;

import com.zayn.bean.Customers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by zengzy19585 on 2017/7/12.
 */
@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customers, Long> {
    @Query(value = "SELECT * FROM Customers WHERE serial_num = :serial_num", nativeQuery = true)
    Customers findBySerial_num(@Param("serial_num") String serial_num);

    @Modifying
    @Query(value = "UPDATE Customers SET pwd = :pwd WHERE serial_num = :serial_num", nativeQuery = true)
    void updatePwd(@Param("serial_num") String serial_num, @Param("pwd") String pwd);
}
