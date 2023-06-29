package com.bypx.synthesis.dao;
import com.bypx.synthesis.bean.menu;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface textInterface {
    public void add(menu name);
    public void dele(String name);
    public List<menu> queryall();
    public List<menu> query_id(menu name);
    public void set(menu name);
    public List<menu> queryid(String role);
}
