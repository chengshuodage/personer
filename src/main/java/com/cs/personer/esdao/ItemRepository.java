package com.cs.personer.esdao;

import com.cs.personer.model.es.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

}
