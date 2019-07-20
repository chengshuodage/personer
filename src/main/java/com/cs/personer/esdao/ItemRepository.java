package com.cs.personer.esdao;

import com.cs.personer.model.es.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    /**
     * @Description:根据价格区间查询
     * @Param price1
     * @Param price2
     * @Author: https://blog.csdn.net/chen_2890
     */
    List<Item> findByPriceBetween(double price1, double price2);

}
