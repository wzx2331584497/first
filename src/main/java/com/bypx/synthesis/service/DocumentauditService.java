package com.bypx.synthesis.service;

import com.bypx.synthesis.bean.*;
import com.bypx.synthesis.dao.JxcproductInterface;
import com.bypx.synthesis.dao.JxcproductorderInterface;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

//单据审核
@Service
@Transactional
public class DocumentauditService {
    @Resource
    JxcproductorderInterface jxcproductorderInterface;
    @Resource
    JxcproductInterface JxcproductInterface;
    public ResInfo queryDocumentaudit(inputparam inputparam, jxcproductorder jxcproductorder2,String type){
       //处理ordertype
        String a="入库单";
        String b="出库单";
        if(a.equals(type)){
            jxcproductorder2.setOrdertype(1);
        }else if (b.equals(type)){
            jxcproductorder2.setOrdertype(2);
        }else {

        }

        Map map = new HashMap();
        Page<User> result = PageHelper.startPage(inputparam.getPage(), inputparam.getSize());
        List<jxcproductorder> list = jxcproductorderInterface.queryDocumentaudit(jxcproductorder2);
//将status转换为中文
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        for (jxcproductorder jxcproductorder : list) {
            //将date类型转换为字符串
            Date date = new Date();
            String strDate = sdf.format(date);
            jxcproductorder.setStringdate(strDate);
            int status = jxcproductorder.getStatus();

            if (status==1){
                jxcproductorder.setStatuss("在途");
            }
        }
        map.put("info", result.getResult());//页面数据
        map.put("total", result.getTotal());//页面分页 包含page和size
        return ResInfo.succes(map);
    }



    public ResInfo pass(jxcproductorder jxcproductorder) {
        System.out.println("校验通过数据");
        System.out.println(jxcproductorder);
        //判断是出库单还是入库单
        //入库单
      if(jxcproductorder.getOrdertype()==1){
          //修改状态为通过(结束)
          jxcproductorderInterface.pass(jxcproductorder);
          jxcproduct jxcproduct=new jxcproduct();
          jxcproduct.setTypeid(jxcproductorder.getTypeid());
          // 先查询在product是否存在
          jxcproduct query = JxcproductInterface.query(jxcproduct);
          //null说明不存在
          if(query==null) {
              System.out.println("数据不存在插入");
              //不存在就新增  //
              //新建jxcproduct对象 赋值数据
              jxcproduct a=new jxcproduct();
              a.setId(UUID.randomUUID().toString().replaceAll("-", ""));
             a.setTypeid(jxcproductorder.getTypeid());
             a.setTotal(jxcproductorder.getNumber());
              JxcproductInterface.add(a);
              return ResInfo.succes("成功");
          } else {
              System.out.println("数据存在修改");
              // 存在更新该条数据
              //总数加上该订单number
              int total = query.getTotal();
              System.out.println(total);
              int number = jxcproductorder.getNumber();
              System.out.println(number);
              int sum=total+number;
              System.out.println(sum);
              jxcproduct.setTotal(sum);
              //更新total
              JxcproductInterface.upload(jxcproduct);
              return ResInfo.succes("成功");
          }
      }else{
          //出库单
          //修改状态为结束
          jxcproductorderInterface.pass(jxcproductorder);
          jxcproduct jxcproduct=new jxcproduct();
          jxcproduct.setTypeid(jxcproductorder.getTypeid());
          // 先查询在product是否存在
          jxcproduct query = JxcproductInterface.query(jxcproduct);
          //null说明不存在
          if(query==null) {
              System.out.println("数据不存在插入");
              return ResInfo.error("库存不够");
           //不存在
          } else {

              // 存在更新该条数据
              //总数加上该订单number
              int total = query.getTotal();
              int number = jxcproductorder.getNumber();
              System.out.println(total);
              System.out.println(number);
              int sum=total-number;
              System.out.println(sum);
              //如果product的订单为0就删除
              if (sum+1==1){
                  //删除product中该条数据的物品
                  System.out.println(jxcproduct);
                  JxcproductInterface.dele(jxcproduct);
                  return ResInfo.succes("成功");
              }else {
                  System.out.println("数据存在修改");
                  //查到了
                  //反之更新该条数据
                  jxcproduct.setTotal(sum);
                  //更新total
                  System.out.println(jxcproduct);
                  JxcproductInterface.upload(jxcproduct);
                  return ResInfo.succes("成功");
              }
          }
      }
    }
    public ResInfo reject(jxcproductorder jxcproductorder) {
        if (jxcproductorder.getCause()==null||jxcproductorder.getCause()=="" && jxcproductorder.getOrdertype()==2){
            jxcproductorder.setCause("管理员驳回");
        }else if(jxcproductorder.getCause()==null||jxcproductorder.getCause()=="" && jxcproductorder.getOrdertype()==1){
            jxcproductorder.setCause("库存容量不足，请尽快扩容");
        }
        //修改状态为驳回 添加原因
        jxcproductorderInterface.reject(jxcproductorder);
  return  null;
    }
}
