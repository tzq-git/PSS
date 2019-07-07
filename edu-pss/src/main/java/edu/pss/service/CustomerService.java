package edu.pss.service;

import edu.pss.bean.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> list();

    Long count();

    List<Customer> pager(Long pageNum, Long pageSize);

    Long insert(Customer bean);

    Long delete(Long id);

    Long update(Customer bean);

    Customer load(Long id);

    Customer loadByName(String name);

    Long countByName(String name);

    List<Customer> pagerByName(String name,Long pageNum,Long pageSize);
}
