package com.cs.personer;

import com.cs.personer.config.MailService;
import com.cs.personer.config.RedisService;
import com.cs.personer.esdao.ItemRepository;
import com.cs.personer.model.Student;
import com.cs.personer.model.es.Item;
import com.cs.personer.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/chen_2890/article/details/83895646 es教程网址
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonerApplicationTests {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void contextLoads() {
        log.info("Info");
        log.error("error");
        log.debug("debug");
    }

    @Test
    public void testLocalDate() {
        Student student = studentService.getById(1);
        System.out.println(student);
    }

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Item.class);
//        elasticsearchTemplate.putMapping(Item.class);
//        elasticsearchTemplate.deleteIndex(Item.class);
    }

    //新增与修改
    @Test
    public void insert() {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
    }

    @Test
    public void insertList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    /**
     * @Description:定义查询方法,含对价格的降序、升序查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testQueryAll(){
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = this.itemRepository.findAll(Sort.by("price").ascending());

        for (Item item:list){
            System.out.println(item);
        }
    }

}
