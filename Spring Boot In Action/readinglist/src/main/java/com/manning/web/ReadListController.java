package com.manning.web;

import com.manning.entity.Book;
import com.manning.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/4/26
 */
@Controller
@RequestMapping("/")
public class ReadListController {

    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }

    /**
     * 根据读者获取阅读列表，并返回阅读列表页面
     * @param reader
     * @param model
     * @return
     */
    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    /**
     * 修改图书的读者并重定向到/{reader}
     * @param reader
     * @param book
     * @return
     */
    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
