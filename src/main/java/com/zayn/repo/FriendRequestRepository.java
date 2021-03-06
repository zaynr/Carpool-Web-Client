package com.zayn.repo;

import com.zayn.bean.FriendRequests;
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
public interface FriendRequestRepository extends CrudRepository<FriendRequests, Long> {
    @Query(value = "SELECT * FROM FriendRequests WHERE userial1 = :serial_num OR userial2 = :serial_num"
            , nativeQuery = true)
    ArrayList<FriendRequests> findByUserial(@Param("serial_num") int serial_num);

    @Query(value = "SELECT * FROM FriendRequests WHERE (userial1 = :userial1 AND userial2 = :userial2) OR (userial1 = :userial2 AND userial2 = :userial1) LIMIT 1", nativeQuery = true)
    FriendRequests findPerfectMatch(@Param("userial1") int userial1, @Param("userial2") int userial2);

    @Modifying
    @Query(value = "DELETE FROM FriendRequests WHERE (userial1 = :userial1 AND userial2 = :userial2) OR (userial1 = :userial2 AND userial2 = :userial1)", nativeQuery = true)
    void deletePerfectMatch(@Param("userial1") int userial1, @Param("userial2") int userial2);
}
