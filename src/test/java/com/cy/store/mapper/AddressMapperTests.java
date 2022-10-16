package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author XQR
 * @date 2022/10/6 2022/10/6
 * @dsecription 类的描述和介绍
 */
//表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired(required = true)
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(1);
        address.setPhone("15996565376");
        address.setName("xqr");
        Integer rows=addressMapper.insert(address);
        System.out.println(rows);
    }

    @Test
    public void countByUid(){
        Integer count=addressMapper.countByUid(17);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(17);
        System.out.println(list);
    }

    @Test
    public void findByAid(){
        System.out.println(addressMapper.findByAid(5));
    }

    @Test
    public void updateNonDefault(){
        addressMapper.updateNonDefault(17);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(5,"xqr",new Date());
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(2);

    }

    @Test
    public void findLastModified(){
        Address lastModified = addressMapper.findLastModified(17);
        System.out.println(lastModified);
    }





}
