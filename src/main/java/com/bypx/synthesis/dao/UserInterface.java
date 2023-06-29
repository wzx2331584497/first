package com.bypx.synthesis.dao;
/*mybatis 抽象接口类*/
import com.bypx.synthesis.bean.*;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public interface UserInterface {
    public void add(User user);
    public void dele(User user);
    public void edit(User user);
    public List<User> query(User user);
    public User login(User user);
    public void register(User user);
    public List<User> query2(User user);
    public List<menu> menu(String account);
    public List<menu> menuall();
    public List<Material> MaterialManagement(Material Material);
    public void addmenu(menu menu);
    public void setMenu(menu menu);
    public void delemenu(menu menu);
    public List<menu> menu1(menu menu);
    public void materialsdele(Material material);
    public List<Material> Material(Material Material);
    public void materialsadd(Material Material);
    public void MaterialsetMenu(Material material);
    public List<role> rolequery(role role);
    public List<role1> rolecorrelation(role1 role1);
    public void addmenu2(menu menu);
    public void Materialaddmenu2(Material material);
    public void roleadd(role role);
    public void roleedit(role role);
    public void roledele(role role);
    public void RoleMenuAssociation(RoleMenuAssociation ac);
    public RoleMenuAssociation queryRoleMenuAssociation(RoleMenuAssociation a);
    public accountrole queryRole( accountrole account);
    public void insertaccountrole(accountrole ar);
    public void uploadaccountrole(accountrole ar);
    public void deleaccountrole(accountrole ar);
    public void delerolemenu(RoleMenuAssociation ar);
    public void uploaduserrole(User user);
    public void insertlabellabel();
    public role queryrolemenu(String  account);
    public void delelabellabel();
    public List<menu> chaxunmenu(String account);




}