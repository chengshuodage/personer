package com.cs.personer.model.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
//@Document(indexName = "testgoods", type = "goods")
//indexName索引名称 可以理解为数据库名 必须为小写
// 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
public class GoodsInfo implements Serializable {
    @Id
    private Long id;
    @Field(store = true)
    private String name;
    @Field(store = true, analyzer = "ik", searchAnalyzer = "ik")
    private String description;
    @Field
    private String backup;

}





