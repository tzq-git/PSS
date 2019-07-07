package edu.pss.dao;

import edu.pss.bean.Provider;

import java.util.List;

public interface ProviderDao {
    List<Provider> list();

    Provider load(Long id);
    Provider loadByName(String name);

    Long delete(Long id);
    Long insert(Provider bean);
    Long update(Provider bean);

    Long count();
    List<Provider> pager(Long pageNum, Long pageSize);

    Long countByName(String name);
    List<Provider> pagerByName(String name ,Long pageNum, Long pageSize);
}
