package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.Date;

/**
 * @author XQR
 * @date 2022/10/6 2022/10/6
 * @dsecription 类的描述和介绍
 */
//表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired(required = true)
    private UserMapper userMapper;
    /**
     * 单元测试方法：就可以单独独立运行，不用启动整个项目，可以做单元测试，提升了代码的测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("tom");
        user.setPassword("123456");
        Integer rows=userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user=userMapper.findByUsername("tom");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(16,"123","xqr",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(16));
    }

    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(17);
        user.setPhone("15996565376");
        user.setEmail("1422148163@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(17,"C:/work_space/heimaTest/store/upload/R-C.jpg","xqr",new Date());
    }


}
