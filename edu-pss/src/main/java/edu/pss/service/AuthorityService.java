package edu.pss.service;

import edu.pss.bean.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> list();
    
    Long insert(Authority bean);

    Long update(Authority bean);

    Authority loadById(Long id);

    Authority loadByName(String name);

    Long count();

    List<Authority> pager(Long pageNum, Long pageSize);

    Long countByName(String name);

    List<Authority> pagerByName(String name, Long pageNum, Long pageSize);
    
}
