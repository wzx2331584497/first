package com.bypx.synthesis.dao;

import com.bypx.synthesis.bean.Material;
import com.bypx.synthesis.bean.jxcproduct;
import com.bypx.synthesis.bean.jxcproductorder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

//material表
@Repository
public interface MaterialInterface {
 public List<jxcproduct> queryall();


}
