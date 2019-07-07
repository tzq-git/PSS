package edu.pss.dao;

import edu.pss.bean.SalePerson;

import java.util.List;

public interface SalePersonDao  {
    List<SalePerson> list();

    SalePerson load(Long id);
    SalePerson loadByName(String name);

    Long delete(Long id);
    Long insert(SalePerson bean);
    Long update(SalePerson bean);

    Long count();
    List<SalePerson> pager(Long pageNum, Long pageSize);

    Long countByName(String name);
    List<SalePerson> pagerByName(String name ,Long pageNum, Long pageSize);

    Long loadCustomer(Long id);
}
