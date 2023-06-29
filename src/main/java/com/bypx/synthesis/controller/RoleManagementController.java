package com.bypx.synthesis.controller;


import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.inputparam;
import com.bypx.synthesis.bean.role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@RestController
@RequestMapping("RoleManagement")
public class RoleManagementController {

    @Resource
    private com.bypx.synthesis.service.RoleManagementService RoleManagementService;

    //表格数据查询
    @RequestMapping("rolequery")
    public ResInfo rolequery(role role, inputparam inputparam) {
        return RoleManagementService.rolequery(role, inputparam);
    }
//角色删除

    @RequestMapping("roledele")
    public ResInfo roledele(role role){


            RoleManagementService.roledele(role);
            return ResInfo.succes(200);

    }
//角色编辑
    @RequestMapping("roleset")
    public ResInfo roleset(role role) {
        System.out.println("测试");
        System.out.println(role);
        try {
          RoleManagementService.roleset(role);
            return ResInfo.succes(200);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    //角色新增
    @RequestMapping("roleadd")
    public ResInfo roleadd(role role) {
        try {
            RoleManagementService.roleadd(role);
            return ResInfo.succes(role);
        } catch (Exception e) {
            return ResInfo.error(e.getMessage());
        }
    }
    //菜单关联
    @RequestMapping("menurole")
    public  ResInfo menurole( String id ,String menu,String role){
        try{
            RoleManagementService.menurole( id,menu,role);
            return ResInfo.succes(200);
        }catch (Exception e){
            return  ResInfo.error(e.getMessage());
        }
    }
    @RequestMapping("defaultid")
//默认勾选的tree 返回权限菜单的id
    public  ResInfo defaultid(String role){
        try{
            return    RoleManagementService.defaultid(role);

        }catch (Exception e){
            return  ResInfo.error(e.getMessage());
        }
    }




}
