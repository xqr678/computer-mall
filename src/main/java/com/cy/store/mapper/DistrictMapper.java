package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @author XQR
 * @date 2022/10/12 2022/10/12
 * @dsecription 类的描述和介绍
 */
public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 某个父区域下的所有区域列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据省/市/区的行政代号获取省/市/区的名称
     * @param code 省/市/区的行政代号
     * @return 匹配的省/市/区的名称，如果没有匹配的数据则返回null
     */
    String findNameByCode(String code);
}
