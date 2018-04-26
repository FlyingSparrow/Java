package com.manning.repository;

import com.manning.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/4/26
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    /**
     * 根据读者查找阅读列表
     * @param reader
     * @return
     */
    List<Book> findByReader(String reader);
}
