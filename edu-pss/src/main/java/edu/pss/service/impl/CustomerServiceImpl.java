package edu.pss.service.impl;

import edu.pss.bean.Customer;
import edu.pss.dao.CustomerDao;
import edu.pss.dao.impl.CustomerDaoImpl;
import edu.pss.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private CustomerDao customerDao = new CustomerDaoImpl();


    @Override
    public List<Customer> list() {
        return customerDao.list();
    }

    @Override
    public Long insert(Customer bean){
        return customerDao.insert(bean);
    }
    @Override
    public Long delete(Long id){
        return customerDao.delete(id);
    }
    @Override
    public Long update(Customer bean){
        return customerDao.update(bean);
    }
    @Override
    public Customer load(Long id){
        return customerDao.load(id);
    }
    @Override
    public Customer loadByName(String name){
        return customerDao.loadByName(name);
    }


    @Override
    public List<Customer> pager(Long pageNum, Long pageSize){
        return  customerDao.pager(pageNum,pageSize);
    }
    @Override
    public Long count(){
        return customerDao.count();
    }

    @Override
    public Long countByName(String name) {
        return customerDao.countByName(name);
    }

    @Override
    public List<Customer> pagerByName(String name, Long pageNum, Long pageSize) {
        return customerDao.pagerByName(name, pageNum, pageSize);
    }
}
