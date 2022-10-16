package com.cy.store.service;

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
public class UserServiceTests {
    @Autowired
    private IUSerService iuSerService;
    @Test
    public void reg(){
        try{
            User user=new User();
            user.setUsername("yuanxin02");
            user.setPassword("123");
            iuSerService.reg(user);
            System.out.println("ok");
        }catch (ServiceException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass().getSimpleName());
        }

    }

    @Test
    public void login(){
        User user = iuSerService.login("xqr", "123");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
       iuSerService.changePassword(17,"xqr","123","123456");
    }

    @Test
    public void getByUid(){
        System.err.println(iuSerService.getByUid(17));
    }

    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("15996565375");
        user.setEmail("1422148162@qq.com");
        user.setGender(0);
        iuSerService.changeInfo(17,"xqrrqx12345",user);
    }

    @Test
    public void changeAvatar(){
        iuSerService.changeAvatar(17,"C:/work_space/heimaTest/store/upload/R-C.jpg","张三");
    }
}
