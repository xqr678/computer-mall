package com.cy.store.service;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author XQR
 * @date 2022/10/12 2022/10/12
 * @dsecription 类的描述和介绍
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;
    @Test
    public void getByParent(){
        List<District> list=districtService.getByParent("86");
        for (District d:list) {
            System.err.println(d);
        }
    }
}
