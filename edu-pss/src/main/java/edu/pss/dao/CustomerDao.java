package edu.pss.dao;

import edu.pss.bean.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> list();

    Customer load(Long id);
    Customer loadByName(String name);

    Long delete(Long id);
    Long insert(Customer bean);
    Long update(Customer bean);

    Long count();
    List<Customer> pager(Long pageNum, Long pageSize);

    Long countByName(String name);
    List<Customer> pagerByName(String name ,Long pageNum, Long pageSize);
}
