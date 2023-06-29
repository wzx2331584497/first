package com.bypx.synthesis.service;

import com.bypx.synthesis.bean.*;
import com.bypx.synthesis.dao.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional

public class StoreService {
    @Resource
   JxcproductorderInterface JxcproductorderInterface;
    @Resource
    UserInterface  userInterface;
    @Resource
    JxcproductInterface JxcproductInterface;
    @Resource
    User2Interface user2Interface;
    //工具
    //通过productorder更新product表
    public void uploadproduct(){
        //更新product表    动态加载
        //只更新结束状态的
        List<jxcproductorder> queryall = JxcproductorderInterface.queryall(3);
        for (jxcproductorder jxcproductorder : queryall) {
            jxcproduct data = new jxcproduct();
            data.setTotal(jxcproductorder.getNumber());
            data.setTypeid(jxcproductorder.getTypeid());
            jxcproduct query = JxcproductInterface.query(data);
            System.out.println(query);
            if(query==null) {
                //不存在就新增  //
                data.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                JxcproductInterface.add(data);
            } else {
                // 存在更新该条数据
                data.setTypeid(query.getTypeid());
                JxcproductInterface.upload(data);
            }
        }

    }
    public ResInfo queryall(jxcproduct data, inputparam inputparam){
        Map map = new HashMap();
        //分页插件会拦截下面第一条语句然后将它改成成分页的俩条语句执行 结果保存在result中
        //result  里面数据是result 数据总数是total
        Page<jxcproduct> result = PageHelper.startPage(inputparam.getPage(),
                inputparam.getSize());
        //获得数据
        JxcproductInterface.queryall(data);
        map.put("info", result.getResult());//页面数据
        map.put("total", result.getTotal());//页面分页 包含page和size
        return ResInfo.succes(map);
    }
    //工具获得用户id
    public  String queryaccountid(String account){
        System.out.println("account");
        User queryaccountid = user2Interface.queryaccountid(account);

        String id = queryaccountid.getId();
        return id;
    }
    //工具 获得date类型日期数据
public Date  date(){
    Date date = new Date();
    return  date;
}
    public ResInfo MaterialManagement(Material Material) {
        //取值
        List<com.bypx.synthesis.bean.Material> materials = userInterface.MaterialManagement(Material);
        //以下是数据处理为tree结构
        //所有菜单集合
        List<Material> list = new ArrayList<>();
        //拿到每一个菜单
        for (com.bypx.synthesis.bean.Material materials1 : materials) {
            //获取每一个菜单的子菜单
            for (com.bypx.synthesis.bean.Material materials2 : materials) {
                if (materials1.getId().equals(materials2.getP_id())) {
                    materials2.setDisabled(false);
                    materials1.setDisabled(true);
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
        return ResInfo.succes(list);
    }
    public ResInfo procurementAndStorage(jxcproductorder data ,String account){
        data.setId(UUID.randomUUID().toString().replaceAll("-",""));
        String queryaccountid = queryaccountid(account);
        data.setCreaterid(queryaccountid);
        data.setCreater(account);
        Date date = date();
        data.setCreatedate(date);
        JxcproductorderInterface.add_reduce(data);
        return  ResInfo.succes(200);
    }
    public ResInfo SaleOutOfStorage(jxcproductorder data, String account ){
        data.setId(UUID.randomUUID().toString().replaceAll("-",""));
        String queryaccountid = queryaccountid(account);
        data.setCreaterid(queryaccountid);
        data.setCreater(account);

        Date date = date();
        data.setCreatedate(date);
        JxcproductorderInterface.add_reduce(data);
        return  ResInfo.succes(200);
    }

    public ResInfo queryproduct(jxcproduct jxcproduct){
        com.bypx.synthesis.bean.jxcproduct query = JxcproductInterface.query(jxcproduct);
        return ResInfo.succes(query);
    }

}
