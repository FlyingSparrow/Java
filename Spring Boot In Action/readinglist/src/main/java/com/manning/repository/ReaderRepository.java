package com.manning.repository;

import com.manning.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangjianchun
 * @create 2018/4/27
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
