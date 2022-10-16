package com.cy.store.controller;

import com.cy.store.entity.District;
import com.cy.store.service.IDistrictService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.CDATASection;

import java.util.List;

/**
 * @author XQR
 * @date 2022/10/12 2022/10/12
 * @dsecription 类的描述和介绍
 */
@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

//    districts开头的请求都被拦截到getByParent()方法
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data=districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }

}
