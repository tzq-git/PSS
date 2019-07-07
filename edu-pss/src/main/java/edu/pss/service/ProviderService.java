package edu.pss.service;

import edu.pss.bean.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> list();

    Long count();

    List<Provider> pager(Long pageNum, Long pageSize);

    Long insert(Provider bean);

    Long delete(Long id);

    Long update(Provider bean);

    Provider load(Long id);

    Provider loadByName(String name);

    Long countByName(String name);

    List<Provider> pagerByName(String name,Long pageNum,Long pageSize);
}
