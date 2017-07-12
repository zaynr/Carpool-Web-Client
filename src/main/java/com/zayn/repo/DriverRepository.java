package com.zayn.repo;

import com.zayn.bean.Drivers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zengzy19585 on 2017/7/12.
 */
@Repository
public interface DriverRepository extends CrudRepository<Drivers, Long> {
    @Query("SELECT lat, lng FROM Drivers WHERE mobile_number = ?1")
    Drivers findByMobile_number(String mobile_number);
}
