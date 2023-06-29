package com.bypx.synthesis.service;

import com.bypx.synthesis.bean.*;

import com.bypx.synthesis.dao.MenuroleInterface;
import com.bypx.synthesis.dao.textInterface;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class RoleManagementService {
    @Resource
    RoleManagementService RoleManagementService;
    @Resource
    com.bypx.synthesis.dao.RoleInterface roleInterface;
    @Resource
    MenuroleInterface menuroleInterface;
    @Resource
    textInterface textInterface;
    //角色查询
    public ResInfo rolequery(role role, inputparam inputparam) {
        Map map = new HashMap();
        Page<User> result = PageHelper.startPage(inputparam.getPage(), inputparam.getSize());
       roleInterface.rolequery(role);
        map.put("info", result.getResult());//页面数据
        map.put("total", result.getTotal());//页面分页 包含page和size
        return ResInfo.succes(map);
    }
//角色删除
    public ResInfo roledele(role role) {
        roleInterface.dele(role.getName());
        //同时也删除该用户在关联表中的关联数据
        return ResInfo.succes("成功");
    }
    //角色编辑
    public ResInfo roleset(role role) {
        roleInterface.set(role);
        return ResInfo.succes(null);
    }
    //角色增加
    public ResInfo roleadd(role role) {
        role.setId(UUID.randomUUID().toString().replaceAll("-",""));
        roleInterface.add(role);
        return ResInfo.succes("成功");
    }
    //菜单关联
  public  ResInfo menurole(String id,String menu,String role){
      String[] menus = menu.split(",");
      for (String s : menus) {
          System.out.println(s);
      }
      menurole menurole=new menurole();
        menurole.setId(UUID.randomUUID().toString().replaceAll("-",""));
        menurole.setRole(role);
        menuroleInterface.dele(role);
      for (String s : menus) {
          menurole.setMenu(s);

menuroleInterface.add(menurole);
      }
      return null;
  }
  public  ResInfo defaultid(String role ){
      System.out.println(role);
      List<menu> queryid = textInterface.queryid(role);
      System.out.println(queryid);
      List<String>  ids=new ArrayList<>();
      for (menu menu : queryid) {
          ids.add(menu.getId());
      }
      System.out.println(ids);
      return  ResInfo.succes(ids);
  }
}
