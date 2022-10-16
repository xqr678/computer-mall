package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;

import java.util.List;

/**
 * @author XQR
 * @date 2022/10/11 2022/10/11
 * @dsecription
 */
public interface IDistrictService {
    /**
     * 根据父代号来查询区域的信息省（省市区）
     * @param parent 父代码
     * @return 多个区域的信息
     */
    List<District> getByParent(String parent);

    /**
     *
     * @param code
     * @return
     */
    String getNameByCode(String code);
}
