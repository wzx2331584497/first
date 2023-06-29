package com.bypx.synthesis.service;
import com.bypx.synthesis.bean.*;
import com.bypx.synthesis.dao.UserDao;
import com.bypx.synthesis.dao.UserInterface;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.SunHints;

import javax.annotation.Resource;
import java.net.CookieHandler;
import java.util.*;

@Service
@Transactional

public class Userservice {
    @Resource
    private UserDao userDao;
    @Resource
    UserInterface userInterface;
    List<menu> a = new ArrayList<>();

    public ResInfo add(User user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        userInterface.add(user);
        return ResInfo.succes(user);
    }

    public ResInfo edit(role role) {
        userInterface.roleedit(role);
        return ResInfo.succes(null);
    }

    public ResInfo dele(User user) {
        userInterface.dele(user);
        return ResInfo.succes(user);
    }

    public ResInfo query(User user, inputparam inputparam) {
////每页数据
//        List<Map<String, Object>> map = userDao.query(user, inputparam);
////将total和list 封装在map中
        Map map = new HashMap();
        //分页插件会拦截下面第一条语句然后将它改成成分页的俩条语句执行 结果保存在result中
        //result  里面数据是result 数据总数是total
        Page<User> result = PageHelper.startPage(inputparam.getPage(), inputparam.getSize());
        userInterface.query(user);
        map.put("info", result.getResult());//页面数据
        map.put("total", result.getTotal());//页面分页 包含page和size

        return ResInfo.succes(map);
    }

    //传入密码作比较 账户传入数据库得到密码 返回密码在这里作比较
    public ResInfo login(String account, String password) {
        User user = new User();
        user.setAccount(account);
        //查找该账户的密码 可能空
        User login = userInterface.login(user);
        String password1 = login.getPassword();
        if (password.equals(password1)) {
            return ResInfo.succes(login);
        } else {
            return ResInfo.error("失败 userservice");
        }
    }

    //注册账户
    public ResInfo register(User user) {
//        查询取出看是否已经注册过
        User login = userInterface.login(user);

        if (login == null) {//为空没注册
//            生成id
            user.setId(UUID.randomUUID().toString().replaceAll("-", ""));

            userInterface.register(user);
            return ResInfo.succes("账户注册成功");

        } else {

            return ResInfo.error("账户已存在");

        }


    }

    public ResInfo chaxun(User user, inputparam inputparam) {
////每页数据
//        List<Map<String, Object>> map = userDao.query(user, inputparam);
////将total和list 封装在map中
        Map map = new HashMap();
        //分页插件会拦截下面第一条语句然后将它改成成分页的俩条语句执行 结果保存在result中
        //result  里面数据是result 数据总数是total
        Page<User> result = PageHelper.startPage(inputparam.getPage(), inputparam.getSize());
        userInterface.query2(user);
        map.put("info", result.getResult());//页面数据
        map.put("total", result.getTotal());//页面分页 包含page和size
        return ResInfo.succes(map);
    }
    public ResInfo xinzheng(User user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        userInterface.add(user);
        return ResInfo.succes("成功");

    }
    public ResInfo menu(menu menu, String account) {
        List<menu> aaa = new ArrayList<>();
        if (account == null) {
            List<com.bypx.synthesis.bean.menu> menuall = userInterface.menuall();
            aaa = menuall;
        }
            if (account != null) {
                List<com.bypx.synthesis.bean.menu> menu1 = userInterface.menu(account);
                aaa = menu1;
            }
            //所有菜单集合
            List<menu> list = new ArrayList<>();
            //拿到每一个菜单
            for (com.bypx.synthesis.bean.menu menu1 : aaa) {
                //获取每一个菜单的子菜单
                for (com.bypx.synthesis.bean.menu menu2 : aaa) {
                    if (menu1.getId().equals(menu2.getPid())) {
                        menu1.getChildren().add(menu2);
                    }
                    menu1.getChildren().sort(new Comparator<com.bypx.synthesis.bean.menu>() {
                        @Override
                        public int compare(com.bypx.synthesis.bean.menu a, com.bypx.synthesis.bean.menu b) {
                            return a.getOrder1() - b.getOrder1();
                        }
                    });
                }
                //把该子集加载到当前父集的childre中
                list.add(menu1);
                //选择pid为null或者为空的最高级菜单
                for (int i = 0; i < list.size(); i++) {
                    if ((list.get(i).getPid() != null && list.get(i).getPid() != "")) {
                        list.remove(i);
                    }
                }
            }
            //去重
            //保存list
            HashSet set = new HashSet(list);
            //清空list
            list.clear();
            //set全部添加会list2
            list.addAll(set);
            list.sort(new Comparator<com.bypx.synthesis.bean.menu>() {
                @Override
                public int compare(com.bypx.synthesis.bean.menu a, com.bypx.synthesis.bean.menu b) {
                    return a.getOrder1() - b.getOrder1();
                }
            });
        System.out.println("菜单数据");
        System.out.println(list);
            return ResInfo.succes(list);
        }



    //系统设置增加子菜单
    public ResInfo addmenu(menu menu) {

//定义menu的子集
        menu menuson = new menu();
//设置url label order icon
        menuson.setLabel(menu.getLabel());
        menuson.setUrl(menu.getUrl());
        menuson.setOrder1(menu.getOrder1());
        menuson.setIcon(menu.getIcon());
//设置他的子集id 和  指明上一级的pid
        menuson.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        menuson.setPid(menu.getId());
        userInterface.addmenu(menuson);
        return ResInfo.succes(200);
    }
    //系统设置内增加导航菜单
    public ResInfo addmenu2(menu menu) {
        System.out.println(menu);
        menu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        userInterface.addmenu2(menu);
        return ResInfo.succes(200);
    }
    //系统设置内修改菜单
    public ResInfo setmenu(menu menu) {

        userInterface.setMenu(menu);
        return ResInfo.succes(200);
    }
    //系统设置内删除导航菜单
    public ResInfo delemenu(menu menu) {
        //头节点查询
        userInterface.delemenu(menu);
        //根据menu的id去查他的子集
        menu a = new menu();
        //将节点的id存入menu
        a.setId(menu.getId());
        //传入menu到数据库中查询下一级
        List<com.bypx.synthesis.bean.menu> querymenu = querymenu(a);
        //查询到的集合传入delemenu1
        delemenu1(querymenu);
        return null;
    }
    //删除集合查询模板
    public void delemenu1(List<menu> list) {
        do {
            //遍历子集集合
            for (com.bypx.synthesis.bean.menu querymenu1 : list) {
                //删除子集
                delemenu(querymenu1);
            }
            //查询其中是否存在子集
            for (com.bypx.synthesis.bean.menu querymenu1 : list) {
                //不存在返回的是null  其中可以在查到子集的不为空
                //返回的是集合  对这些子集继续做查询
                List<menu> querymenu = querymenu(querymenu1);
                //存在子集的话 递归自身

                delemenu1(querymenu);
            }
            //集合为空不执行
        } while (list == null);

    }
    //返回查询到的所有自己的list集合
    public List<menu> querymenu(menu menu) {
        //查询条件 父节点id和子节点pid相同
        //调用查询interface 传入id
        return userInterface.menu1(menu);
    }
    //角色管理数据查询
    public ResInfo rolechaxun(role role, inputparam inputparam) {
////每页数据
//        List<Map<String, Object>> map = userDao.query(user, inputparam);
////将total和list 封装在map中
        Map map = new HashMap();
        //分页插件会拦截下面第一条语句然后将它改成成分页的俩条语句执行 结果保存在result中
        //result  里面数据是result 数据总数是total
        Page<User> result = PageHelper.startPage(inputparam.getPage(), inputparam.getSize());
        userInterface.rolequery(role);
        map.put("info", result.getResult());//页面数据
        map.put("total", result.getTotal());//页面分页 包含page和size
        return ResInfo.succes(map);
    }
    //角色关联数据
    public ResInfo rolecooralation(role1 role1) {
        List<role1> rolecorrelation = userInterface.rolecorrelation(role1);
        System.out.println(rolecorrelation.toString());
        return ResInfo.succes(rolecorrelation);
    }
    //材料数据查询（tree结构)
    public ResInfo MaterialManagement(Material Material) {
        //取值
        List<com.bypx.synthesis.bean.Material> materials = userInterface.MaterialManagement(Material);
        //以下是数据处理为tree结构
        System.out.println(materials);
        //所有菜单集合
        List<Material> list = new ArrayList<>();
        //拿到每一个菜单
        for (com.bypx.synthesis.bean.Material materials1 : materials) {
            //获取每一个菜单的子菜单

            for (com.bypx.synthesis.bean.Material materials2 : materials) {
                if (materials1.getId().equals(materials2.getP_id())) {
                    materials1.getChildren().add(materials2);
                }
            }
            //把该子集加载到当前父集的childre中

            list.add(materials1);

            //选择p_id为null或者为空的最高级菜

            for (int i = 0; i < list.size(); i++) {
                if ((list.get(i).getP_id() != null && list.get(i).getP_id() != "")) {
                    list.remove(i);
                }
            }
        }
        //去重
        //保存list
        HashSet set = new HashSet(list);
        //清空list
        list.clear();
        //set全部添加会list2
        list.addAll(set);

        //根据order 使用compare比较器对数据进行排序
//
        Collections.sort(list, new Comparator<com.bypx.synthesis.bean.Material>() {
            @Override
            public int compare(com.bypx.synthesis.bean.Material o1, com.bypx.synthesis.bean.Material o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });

        System.out.println(111111);
        System.out.println(list);
        return ResInfo.succes(list);

    }
    //材料数据增加（tree结构)
    public ResInfo materialsadd(Material Material) {

//定义menu的子集
        Material menuson = new Material();
//设置url label order icon

        //private String  id;
        //    private String  label;
        //    private String  p_id;
        //    private String  creat_time;
        //设置id label p_id create_time
        menuson.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        menuson.setLabel(Material.getLabel());
        menuson.setP_id(Material.getId());
        menuson.setCreat_time(time());
        userInterface.materialsadd(menuson);
        return ResInfo.succes(200);
    }
    public String time() {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DATE);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int mi = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);
        String time = y + "年" + m + "月" + d + "天" + h + "时" + mi + "秒" + s + "秒";
        return time;
    }
    //材料数据修改（tree结构)
    public ResInfo Materialsetmenu(Material Material) {
        userInterface.MaterialsetMenu(Material);
        return ResInfo.succes(200);
    }
    //材料数据删除（tree结构)
    public ResInfo Materialdelemenu(Material material) {
        //头节点查询
        userInterface.materialsdele(material);
        //根据menu的id去查他的子集
        Material a = new Material();
        //将节点的id存入menu
        a.setId(material.getId());
        //传入menu到数据库中查询下一级
        List<com.bypx.synthesis.bean.Material> querymenu = Materialquerymenu(a);
        //查询到的集合传入delemenu1
        Materialdelemenu1(querymenu);
        return null;
    }
    //删除集合查询模板
    public void Materialdelemenu1(List<Material> list) {
        do {
            //遍历子集集合
            for (com.bypx.synthesis.bean.Material querymenu1 : list) {
                //删除子集
                Materialdelemenu(querymenu1);
            }
            //查询其中是否存在子集
            for (com.bypx.synthesis.bean.Material querymenu1 : list) {
                //不存在返回的是null  其中可以在查到子集的不为空
                //返回的是集合  对这些子集继续做查询
                List<Material> querymenu = Materialquerymenu(querymenu1);
                //存在子集的话 递归自身

                Materialdelemenu1(querymenu);
            }
            //集合为空不执行
        } while (list == null);

    }
    //返回查询到的所有自己的list集合
    public List<Material> Materialquerymenu(Material material) {
        //查询条件 父节点id和子节点pid相同
        //调用查询interface 传入id
        return userInterface.Material(material);
    }
    //材料管理增加导航菜单
    public ResInfo Materialaddmenu2(Material material) {
        material.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        material.setCreat_time(time());
        userInterface.Materialaddmenu2(material);
        return ResInfo.succes(200);
    }
    //角色新增
    public ResInfo rolexinzheng(role role) {
        userInterface.roleadd(role);
        return ResInfo.succes("成功");

    }
    //角色编辑
    public ResInfo edit(User user) {
        userInterface.edit(user);
        return ResInfo.succes(user);
    }
    //角色删除
    public ResInfo roledele(role role) {
        userInterface.roledele(role);
        return ResInfo.succes(null);
    }

    //用户管理-角色关联(关联角色)
    public ResInfo uploaduserrole(User user) {
        userInterface.uploaduserrole(user);
        return ResInfo.succes(200);
    }















}



