package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author XQR
 * @date 2022/10/11 2022/10/11
 * @dsecription 新增收货地址的实现类
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private int maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
//        调用收货地址统计的方法
        Integer count = addressMapper.countByUid(uid);
        if (count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
//        对address对象中的数据进行补全：省市区的相关数据
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        Integer isDelete=count==0 ? 1 : 0;
        address.setIsDefault(isDelete);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());

        Integer rows=addressMapper.insert(address);
        if (rows!=1){
            throw new InsertException("插入用户的收货地址存在未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address :list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result=addressMapper.findByAid(aid);
        if (result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
//        检测当前获取到的收货地址数据的归属
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
//        先将所有的收货地址设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows<1){
            throw new UpdateException("更新数据产生未知的异常");
        }
//        将用户选中的某条地址设置为默认收货地址
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");
        }

    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用findByAid()查询收货地址数据
        Address result = addressMapper.findByAid(aid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException：非法访问
            throw new AccessDeniedException("非常访问");
        }
        // 根据参数aid，调用deleteByAid()执行删除
        Integer rows1 = addressMapper.deleteByAid(aid);
        if (rows1 != 1) {
            throw new DeleteException("删除收货地址数据时出现未知错误，请联系系统管理员");
        }
        // 判断查询结果中的isDefault是否为0
        if (result.getIsDefault() == 0) {
            return;
        }
        // 调用持久层的countByUid()统计目前还有多少收货地址
        Integer count = addressMapper.countByUid(uid);
        // 判断目前的收货地址的数量是否为0
        if (count == 0) {
            return;
        }
        // 调用findLastModified()找出用户最近修改的收货地址数据
        Address lastModified = addressMapper.findLastModified(uid);
        // 从以上查询结果中找出aid属性值
        Integer lastModifiedAid = lastModified.getAid();
        // 调用持久层的updateDefaultByAid()方法执行设置默认收货地址，并获取返回的受影响的行数
        Integer rows2 = addressMapper.updateDefaultByAid(lastModifiedAid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows2 != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新收货地址数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据方法");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedTime(null);
        address.setCreatedUser(null);
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        return  address;
    }
}
