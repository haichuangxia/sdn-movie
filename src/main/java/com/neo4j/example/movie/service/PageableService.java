package com.neo4j.example.movie.service;

import com.neo4j.example.movie.domains.abstractEntity.AbstractEntity;
import com.neo4j.example.movie.repositories.PageableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @author qingbin 2020/12/31
 * usage 封装pageable对象
 */
@Service
public class PageableService {
    @Autowired
    private PageableRepository pageableRepository;

    //    @Transactional(readOnly = true)
    public Page<AbstractEntity> findAll(int pageNo, int pageSize) {
        /*封装Pageable对象*/
        Pageable pageable = PageRequest.of(1, 10);
        return pageableRepository.findAll(pageable);
    }
}
