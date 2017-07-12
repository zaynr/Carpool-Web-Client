package com.zayn.repo;

import com.zayn.bean.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zengzy19585 on 2017/7/12.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customers, Long> {
}
