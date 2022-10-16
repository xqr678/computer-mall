package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * @author XQR
 * @date 2022/10/14 2022/10/14
 * @dsecription 类的描述和介绍
 */
public interface IProductService {
    List<Product> findHotList();

    List<Product> findNewList();
    Product findById(Integer id);
}
