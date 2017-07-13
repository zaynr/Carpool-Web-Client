package com.zayn.repo;

import com.zayn.bean.Drivers;
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
public interface DriverRepository extends CrudRepository<Drivers, Long> {
    @Query(value = "SELECT * FROM Drivers WHERE mobile_number = :mobile_number", nativeQuery = true)
    Drivers findByMobile_number(@Param("mobile_number")String mobile_number);

    @Modifying
    @Query(value = "UPDATE Drivers SET lat=:lat, lng=:lng WHERE mobile_number = :mobile_number", nativeQuery = true)
    void updateDriverLoc(@Param("lat")String lat, @Param("lng")String lng, @Param("mobile_number")String mobile_number);
}
