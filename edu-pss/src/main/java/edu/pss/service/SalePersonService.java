package edu.pss.service;

import edu.pss.bean.SalePerson;

import java.util.List;

public interface SalePersonService {
    List<SalePerson> list();

    Long count();

    List<SalePerson> pager(Long pageNum, Long pageSize);

    Long insert(SalePerson bean);

    Long delete(Long id);

    Long update(SalePerson bean);

    SalePerson load(Long id);

    SalePerson loadByName(String name);

    Long countByName(String name);

    List<SalePerson> pagerByName(String name,Long pageNum,Long pageSize);

    Long loadCustomer(Long id);
}
