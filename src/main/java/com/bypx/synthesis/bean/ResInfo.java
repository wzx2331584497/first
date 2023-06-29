package com.bypx.synthesis.bean;

public class ResInfo {
  private  int code;
  private  Object data;
  private  String msg;

//  redis转json可能报错 对象加个无参构造器
//    写入redis中的所有对象都要有一个无参构造器
    public  ResInfo(){}

    public  ResInfo(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        //获得对象的数据类型
    }



    public static ResInfo error( int code,String msg){//失败传回
        return new ResInfo(code,null,msg );
    }
      public static ResInfo error(String msg){//失败传回
        return new ResInfo( 600,null,msg );
    }
    public static ResInfo succes(Object data){//成功传回
        return new ResInfo( 200,data,null);
    }

    public static ResInfo succes(String msg){//成功传回
        return new ResInfo( 200,null,msg);
    }
    public static ResInfo succes( Object data,String msg){//成功传回
        return new ResInfo( 200,data,msg);
    }
    public static ResInfo succes(int code){//成功传回
        return new ResInfo(code,null,null);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
