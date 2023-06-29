package com.bypx.synthesis.service;

import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.User;
import com.bypx.synthesis.bean.inputparam;
import com.bypx.synthesis.bean.jxcproductorder;
import com.bypx.synthesis.dao.JxcproductorderInterface;
import com.bypx.synthesis.dao.User2Interface;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StockInService {
    @Resource
    JxcproductorderInterface jxcproductorderInterface;
    @Resource
    com.bypx.synthesis.dao.User2Interface User2Interface;
    public ResInfo query(jxcproductorder jxcproductorder1, inputparam inputparam){
    //判断当前用户是否为管理员
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
        List<jxcproductorder> list = jxcproductorderInterface.queryStorein(jxcproductorder1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        for (jxcproductorder jxcproductorder : list) {
            //将date类型转换为字符串
            Date date = new Date();
            String strDate = sdf.format(date);
            jxcproductorder.setStringdate(strDate);
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
    public ResInfo dele(String id){
        jxcproductorderInterface.dele(id);
        return null;
    }
    public ResInfo upload(jxcproductorder jxcproductorder) {
        jxcproductorderInterface.upload(jxcproductorder);
        return null;
    }


}
