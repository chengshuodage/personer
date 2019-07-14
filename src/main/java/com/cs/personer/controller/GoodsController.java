package com.cs.personer.controller;

import com.cs.personer.esdao.GoodsRepository;
import com.cs.personer.model.es.GoodsInfo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * java中使用ElasticsearchCrudRepository来操作,复杂的使用ElasticsearchTemplate
 */
@RestController
@RequestMapping(value = "/es", produces = "application/json;charset=UTF-8")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //http://localhost:8888/save
    @GetMapping("/save")
    public void save() {
        GoodsInfo goodsInfo = new GoodsInfo(System.currentTimeMillis(),
                "商品" + System.currentTimeMillis(), "这是一个测试商品");
        goodsRepository.save(goodsInfo);
    }

    //http://localhost:8888/delete?id=1525415333329
    @GetMapping("/delete")
    public void delete(long id) {
        GoodsInfo goodsInfo = goodsRepository.findById(id).orElse(null);
        goodsRepository.delete(goodsInfo);
    }

    //http://localhost:8888/update?id=1525417362754&name=修改&description=修改
    @GetMapping("/update")
    public void update(long id, String name, String description) {
        GoodsInfo goodsInfo = new GoodsInfo(id,
                name, description);
        goodsRepository.save(goodsInfo);
    }

    //http://localhost:8888/getOne?id=1525417362754
    @GetMapping("/getById")
    public GoodsInfo getOne(long id) {
        GoodsInfo goodsInfo = goodsRepository.findById(id).orElse(null);
        return goodsInfo;
    }


    //根据关键字"商品"去查询列表，name或者description包含的都查询
    @GetMapping("/getGoodsList")
    public List<GoodsInfo> getList(@RequestParam(required = false, defaultValue = "10") Integer page,
                                   @RequestParam(required = false, defaultValue = "0") Integer size,
                                   String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder()
                .withPageable(pageable);
        if (StringUtils.isNoneBlank(keyword)) {
            // keyword must not null
            searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyword));
        }

        /*
        SearchQuery
        这个很关键，这是搜索条件的入口，
        elasticsearchTemplate 会 使用它 进行搜索
         */
        SearchQuery searchQuery = searchQueryBuilder.build();
        // page search
        Page<GoodsInfo> goodsInfoPage = elasticsearchTemplate.queryForPage(searchQuery, GoodsInfo.class);
        return goodsInfoPage.getContent();
    }


}
