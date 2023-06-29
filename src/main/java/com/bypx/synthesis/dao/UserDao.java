package com.bypx.synthesis.dao;

import com.bypx.synthesis.bean.User;

import com.bypx.synthesis.bean.inputparam;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void add(User user) {
        String sql = "insert into user2(id,name,account,password) values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAccount(), user.getPassword());
    }

    public void dele(User user) {
        String sql = "delete  from  user2 where id=?";
        jdbcTemplate.update(sql, user.getId());
    }

    public void edit(User user) {

        String sql = "update user2 set account=?,name=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getAccount(), user.getId());
    }

    public List<Map<String, Object>> query(User user, com.bypx.synthesis.bean.inputparam inputparam) {
        String sql = "select * from  user2 where 1=1 ";
        if (!StringUtils.isEmpty(user.getAccount())) {//account不为空
            sql += " and  account like   '%" + user.getName() + "%'";
        }
        if (!StringUtils.isEmpty(user.getName())) {//name不为空
            sql += " and  name like  '%" + user.getName() + "%'";
        }
        if (inputparam.getPage() != null || inputparam.getSize() != null) {
            sql += " limit " + (inputparam.getPage() - 1) * inputparam.getSize() + ","
                    + inputparam.getSize();
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }

    public long queryaccount(User user) {
        String sql = " select count(*) as total from user2 where 1=1 ";
        if (!StringUtils.isEmpty(user.getAccount())) {//account不为空
            sql += " and  account like   '%" + user.getName() + "%'";
        }
        if (!StringUtils.isEmpty(user.getName())) {//name不为空
            sql += " and  name like  '%" + user.getName() + "%'";
        }
        Map<String, Object> res = jdbcTemplate.queryForMap(sql);
        long total = Long.valueOf(res.get("total").toString());
        return total;
    }


}
