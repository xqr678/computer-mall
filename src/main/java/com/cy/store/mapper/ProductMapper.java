package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * @author XQR
 * @date 2022/10/14 2022/10/14
 * @dsecription 类的描述和介绍
 */
public interface ProductMapper {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 查询新商品的前四名
     * @return 新商品的前四名的集合
     */
    List<Product> findNewList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
