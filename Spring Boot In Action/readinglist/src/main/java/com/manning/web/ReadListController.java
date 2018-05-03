package com.manning.web;

import com.manning.configuration.AmazonProperties;
import com.manning.entity.Book;
import com.manning.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
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
    private AmazonProperties amazonProperties;
    private CounterService counterService;
    private GaugeService gaugeService;

    @Autowired
    public ReadListController(ReadingListRepository readingListRepository,
                              AmazonProperties amazonProperties,
                              CounterService counterService,
                              GaugeService gaugeService){
        this.readingListRepository = readingListRepository;
        this.amazonProperties = amazonProperties;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
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
            model.addAttribute("reader", reader);
            model.addAttribute("amazonId", amazonProperties.getAssociateId());
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

        counterService.increment("books.saved");
        gaugeService.submit("books.last.saved", System.currentTimeMillis());

        return "redirect:/{reader}";
    }
}
