package com.bypx.synthesis.service;

import com.bypx.synthesis.bean.*;
import com.bypx.synthesis.dao.JxcproductInterface;
import com.bypx.synthesis.dao.JxcproductorderInterface;
import com.bypx.synthesis.dao.User2Interface;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StockOutService {
    @Resource
    JxcproductorderInterface jxcproductorderInterface;
    @Resource
    JxcproductInterface jxcproductInterface;
    @Resource
    User2Interface User2Interface;
    public ResInfo query(inputparam inputparam,jxcproductorder jxcproductorder1){

        User user= new User();
        String creater = jxcproductorder1.getCreater();
        user.setAccount(creater);
        User query = User2Interface.query(user);
        String role = query.getRole();
        String role2="管理员";
        if (role2.equals(role)){
            jxcproductorder1.setCreater(null);
        }


        Map map = new HashMap();
        Page<User> result = PageHelper.startPage(inputparam.getPage(), inputparam.getSize());
        List<jxcproductorder> list = jxcproductorderInterface.queryStoreout(jxcproductorder1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        for (jxcproductorder jxcproductorder : list) {
            //将date类型转换为字符串
            Date date = new Date();
            String strDate = sdf.format(date);
jxcproductorder.setStringdate(strDate);
            //将status转换为中文
            int status = jxcproductorder.getStatus();
            if (status==1){
                jxcproductorder.setStatuss("在途");
            }else if (status==2){
                jxcproductorder.setStatuss("驳回");
            }else{
                jxcproductorder.setStatuss("结束");
            }
        }

        map.put("info", result.getResult());//页面数据
        map.put("total", result.getTotal());//页面分页 包含page和size
        return ResInfo.succes(map);

    }


    public ResInfo dele(String id) {
        jxcproductorderInterface.dele(id);
        return null;
    }

    public ResInfo upload(jxcproductorder jxcproductorder) {
        jxcproductorderInterface.upload(jxcproductorder);
        return null;
    }

    public ResInfo judge(jxcproductorder jxcproductorder) {
        //查询数量
        //传入typeid 查询库存
        String typeid = jxcproductorder.getTypeid();
        jxcproduct judge = jxcproductInterface.judge(typeid);
if (judge==null){
    jxcproduct a=new jxcproduct();
    a.setTotal(0);
    return ResInfo.succes(a);
}else {
    return ResInfo.succes(judge);
}




    }


}
