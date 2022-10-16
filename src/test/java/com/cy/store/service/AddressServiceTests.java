package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author XQR
 * @date 2022/10/6 2022/10/6
 * @dsecription 类的描述和介绍
 */
//表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setPhone("15996565376");
        address.setName("xqr");
        addressService.addNewAddress(1,"xqr",address);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(5,17,"xqr");
    }

    @Test
    public void delete(){
        addressService.delete(6,17,"xqr");
    }

}
