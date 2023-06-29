package com.bypx.synthesis.controller;


import com.bypx.synthesis.bean.*;
import com.bypx.synthesis.config.redis.RedisUtil;
import com.bypx.synthesis.service.Userservice;
import org.apache.catalina.connector.Request;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.ServerCookies;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController  //接口层所有返回数据是一个json 就不需要额外添加 @ResponseBody
@RequestMapping("user")
public class UserController {
    @Resource
    private Userservice userservice;
    @Resource
    private RedisUtil redisUtil;
    /*psisystem——————————————————————*/
    //psi的账户注册
    @RequestMapping("addoredit")
    public ResInfo addoredit(User user) {

        if (StringUtils.isEmpty(user.getId())) {
            //id为空的时候增加用户
            //新增用户
            if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getName())) {
                return ResInfo.error(201, "数据不能为空");
            }

            try {
                return userservice.add(user);

            } catch (Exception e) {
                return ResInfo.error(e.getMessage());
            }


            //id不为空，修改用户


        } else {
            //反之id不为空的时候修改用户
            if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getName())) {
                return ResInfo.error(201, "不能为空");
            }
            try {
                return userservice.edit(user);
            } catch (Exception e) {
                return ResInfo.error(e.getMessage());
            }


        }


    }
    @RequestMapping("query")
    public ResInfo query(User user, inputparam inputparam) {
        if (!StringUtils.isEmpty(user.getName()) || !StringUtils.isEmpty(user.getAccount())) {
            String key = "redisquery" + user.getName() + user.getAccount();//给该查询建立索引键
            //判断该键存不存在于索引中
            boolean b = redisUtil.hasKey(key);
            ResInfo resInfo = null;
            if (!b) {
                resInfo = userservice.query(user, inputparam);
                System.out.println("数据库中中调取数据");
                redisUtil.set(key, resInfo, 60);
            } else {
//        存在直接缓存中取值赋值给返回对象
                resInfo = (ResInfo) redisUtil.get(key);
                System.out.println("缓存中调取数据");
            }//最后返回查询数据对象
            return resInfo;
        }
        if (StringUtils.isEmpty(user.getAccount())) {//如果账户不为空执行用账户查新
        }
        try {
            return userservice.query(user, inputparam);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    //账户校验
    @RequestMapping("login")
    public ResInfo login (HttpServletRequest request, HttpServletResponse response,String account, String password,Boolean vaule1) {
//

        if (account == null || password == null) {
            return ResInfo.error("账户或者密码为空");
        } else {
            return userservice.login(account, password);
        }
        //返回判断结果
    }
    //数据回填 获得cookt内账户密码

    //账户注册
    @RequestMapping("register")
    public ResInfo register(User user) {
//成功地话 账户传入数据库比较
        System.out.println(user);
        if (user.getAccount() != null && user.getPassword() != null) {
            return userservice.register(user);
        } else {
            return ResInfo.error("账户密码不能为空");
        }

    }
    //----  @RequestMapping("register")---------------------------------------------------------------
    @RequestMapping("chaxun")
    public ResInfo chaxun(User user, inputparam inputparam) {

        return userservice.chaxun(user, inputparam);
    }
    @RequestMapping("dele")
    public ResInfo dele(User user, String id) {
        try {
            user.setId(id);
            userservice.dele(user);
            return ResInfo.succes(user);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    @RequestMapping("edit")
    public ResInfo edit(User user, String id, String account, String name) {

        try {
            user.setId(id);
            user.setAccount(account);
            user.setName(name);
            userservice.edit(user);
            return ResInfo.succes(user);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    @RequestMapping("xingzheng")
    public ResInfo xingzheng(User user) {

        try {
            userservice.xinzheng(user);
            return ResInfo.succes(user);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    @RequestMapping("menu")
    public ResInfo menu(menu menu,String account) {
        try {

            return userservice.menu(menu,account);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    @RequestMapping(value = "uploadFile")
    //value要和前端传的数据名一样
    public ResInfo add(User user, @RequestParam(value = "file", required = false) MultipartFile photo) {
        System.out.println(photo);
        Map aa = new HashMap<>();//建立一个map容器
        aa.put("code", 1);//map容器内写入(code键，值为1)
        try {
            File file = new File("D:\\upload");//存储上传的文件
            if (!file.exists()) {//判断文件是否存在，如果不存在就执行括号内代码
                file.mkdirs();
            }//建立file路径的文件夹
            String Photoname = "photo.JPG";//获得源文件的名字
            File newfile = new File(file.getPath(), Photoname);//保存在file。getpath路径下 文件名字叫Photoname
            photo.transferTo(newfile); //写入文件到newfile中
        } catch (Exception e) {
            aa.put("code", 0);//map容器内写入(code键，值为0)
            e.printStackTrace();//抛出异常类型
        }
        return ResInfo.succes(200);

    }
    //系统设置菜单新增
    @RequestMapping("addmenu")
    public ResInfo addmenu(menu menu) {//32432
        userservice.addmenu(menu);
        return null;
    }
    //导航菜单新增
    @RequestMapping("addmenu2")
    public ResInfo addmenu2(menu menu) {
        userservice.addmenu2(menu);
        return null;
    }
    //修改菜单数据
    @RequestMapping("setmenu")
    public ResInfo setmenu(menu menu) {
        userservice.setmenu(menu);
        return null;
    }
    //删除菜单数据
    @RequestMapping("delemenu")
    public ResInfo delemenu(menu menu) {

        userservice.delemenu(menu);
        return null;
    }
    @RequestMapping("checkmenu")
    public ResInfo checkmenu(menu menu) {
        return null;
    }
    //关联角色数据query
    @RequestMapping("rolecorrelation")
    public ResInfo rolecorrelation(role1 role1) {
        return userservice.rolecooralation(role1);
    }
    //材料管理数据查询
    @RequestMapping("MaterialManagement")
    public ResInfo MaterialManagement(Material Material) {
        return userservice.MaterialManagement(Material);
    }
//材料管理tree数据增加
    @RequestMapping("materialsadd")
    public ResInfo materialsadd(Material Material) {
        userservice.materialsadd(Material);
        return null;
    }
//材料管理tree数据删除
    @RequestMapping("materialsdele")
    public ResInfo materialsdele(Material Material) {
  userservice.Materialdelemenu(Material);
        return null;
    }
//材料管理tree数据修改
    @RequestMapping("Materialsetmenu")
    public ResInfo Materialsetmenu(Material material) {
        userservice.Materialsetmenu(material);
        return null;
    }
    //材料管理tree数据一级数据增加
    @RequestMapping("Materialaddmenu2")
    public ResInfo Materialddmenu2(Material material) {
        userservice.Materialaddmenu2(material);
        return null;
    }
    //角色管理新增用户
    @RequestMapping("rolexingzheng")
    public ResInfo rolexingzheng(role role) {

        try {
            userservice.rolexinzheng(role);
            return ResInfo.succes(role);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    @RequestMapping("roledele")
    public ResInfo roledele(role role) {
        try {
            userservice.roledele(role);
            return ResInfo.succes(200);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    //用户管理-角色关联
    @RequestMapping("uploaduserrole")
    public ResInfo roleassociation(User user ) {
        try {
            System.out.println(user.getAccount()+""+user.getRole());
            userservice.uploaduserrole(user);
            return ResInfo.succes(200);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
}

