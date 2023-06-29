package com.bypx.synthesis.service;
import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.jxcproduct;
import com.bypx.synthesis.bean.jxcproductorder;
import com.bypx.synthesis.dao.JxcproductInterface;
import com.bypx.synthesis.dao.JxcproductorderInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class loginService {
    @Resource
    JxcproductInterface JxcproductInterface;
    @Resource
    JxcproductorderInterface JxcproductorderInterface;
    //工具
    //通过productorder更新product表
    //对product校验  如果productorder中的订单类型在product中不存在




    public ResInfo queryproduct(jxcproduct jxcproduct){
        List<com.bypx.synthesis.bean.jxcproduct> queryall = JxcproductInterface.queryall(jxcproduct);
        return ResInfo.succes(queryall);
    }


}
