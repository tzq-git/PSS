package edu.pss.service;

import edu.pss.bean.SaleReturn;

import java.util.List;

public interface SaleReturnService {

    List<SaleReturn> list();

    Long insert(SaleReturn bean);

    Long delete(Long id);

    Long update(SaleReturn bean);

    SaleReturn loadById(Long id);

    SaleReturn loadByName(String name);

    Long count();

    List<SaleReturn> pager(Long pageNum, Long pageSize);

    Long countByName(String name);

    List<SaleReturn> pagerByName(String name, Long pageNum, Long pageSize);
}
