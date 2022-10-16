package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUSerService;
import com.cy.store.service.ex.NewPasswordNotMatchException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author XQR
 * @date 2022/10/6 2022/10/6
 * @dsecription 处理用户相关请求的控制器类
 */
//@RestController相当于：@Controller+@ResponseBody的功能
@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUSerService userService;

    /**
     * 约会大于配置，开发思想来完成，省略大量的配置甚至注解的编写
     * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端的数据
     * springBoot会将前端的url地址参数名和pojo类的属性名进行比较，如果这两个
     * 名称项目，则将值
     */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 2.接收数据方式：请求处理方法的参数列表设置为非pojo类型,
     * SprintBoot会直接请求的参数名和方法的参数直接进行比较，如果名称相同则
     * 自动完成值的依赖注入
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data=userService.login(username,password);
//         向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
//        获取session中绑定的数据
//        System.out.println(getUidFromSession(session));
//        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }
    /**
    @RequestMapping("reg")
//    表示此方法的响应结果以json格式进行数据的响应给到前端
//    @ResponseBody
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result=new JsonResult<>();
        try {
            // 调用业务对象执行注册
            userService.reg(user);
            // 响应成功
            result.setState(200);
        } catch (UsernameDuplicatedException e){
            result.setState(4000);
            result.setMessage("用户被占用");
        }catch (InsertException e){
            result.setState(5000);
            result.setMessage("注册时创建产生未知的异常");
        }
        return result;
    }
    **/

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,String newPasswordAnother,HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        if (!newPassword.equals(newPasswordAnother)){
            throw new NewPasswordNotMatchException("新密码两次输入不准确");
        }
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
//        user对象有四部分的数据：username、phone、email、gender
//        uid数据需要再次封装user对象中
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }

    /**
     *  MultipartFile接口是SpringMVC提供了一个接口，这个接口是为我们包装了
     *  获取文件类型的数据（任何类型的file都可以接收），SpringBoot它整合了SpringMVC
     *  只需要在处理请求的方法参数列表上声明一个参数类型为MultipartFile的参数，然后
     *  SpringBoot自动将传递给服务的文件数据赋值给这个的参数
     * @RequestParam 表示请求的参数，将请求中的参数注入请求处理方法的某个参数上
     * 如果名称不一致可以使用@RequsetParam注解进行标记和映射
     * @param session
     * @param file
     * @return
     */
    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session){
//        判断文件是否为null
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
//        判断文件的类型是否是我们规定的和后缀类型
        String contentType = file.getContentType();
//        如果集合包含某个元素则返回值true
        if (!AVATAR_TYPES.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }

        String parent = session.getServletContext().getRealPath("upload");
        System.out.println("头像存放的路径名"+parent);
//        File对象指向这个路径，File是否存在
        File dir=new File(parent);
        if (!dir.exists()){
            dir.mkdirs();
        }
//        获取到这个文件名称，UUID工具来将生成一个新的字符串将作文文件名
//        例如avatar01.png
        String OriginalFileName=file.getOriginalFilename();
        System.out.println("头像原名："+OriginalFileName);
        int index = OriginalFileName.lastIndexOf(".");
        String suffix=OriginalFileName.substring(index);
        String fileName = UUID.randomUUID().toString().toUpperCase()+suffix;
        System.out.println("现头像名："+fileName);
        //是一个空文件
        File dest=new File(dir,fileName);
        //参数file中数据写入到这个空文件中
        try {
            //将数据写入到目标文件中了
            file.transferTo(dest);
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadException("文件读写异常");
        }
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        //返回头像的路径
        String avatar="/upload/"+fileName;
        userService.changeAvatar(uid,avatar,username);
        //返回用户图形的路径给前端页面
        return new JsonResult<String>(OK,avatar);

    }


}
