package com.jk.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jk.entity.Loger;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("app/user")
public class UserController {

        @Resource
        private  UserService userService;

        @RequestMapping("updateLog")
        public void updateLog(@RequestParam("_id") String _id, @RequestParam("logId") String logId,
                              @RequestParam("logMsg") String logMsg, @RequestParam("createTime") String createTime) throws ParseException {
            Loger loger = new Loger();
            loger.set_id(_id);
            loger.setLogId(Integer.parseInt(logId));
            loger.setLogMsg(logMsg);
            loger.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse(createTime));
            System.out.println("consumer----"+loger.toString());
            userService.updateLog(loger);
        }

        @RequestMapping("queryLogById")
        public String queryLogById(@RequestParam("id") String id){

            Loger loger=userService.queryLogById(id);
            System.out.println(loger.toString());
            return com.alibaba.fastjson.JSON.toJSONString(loger);
        }

        @RequestMapping("saveLog")
        public void saveLog(@RequestBody Loger loger){

            System.out.println("消费者添加-"+loger.toString());
            userService.saveLog(loger);
        }

        @RequestMapping("deleteLog/{id}")
        public  void  deleteLog(@PathVariable("id") String id){
            //System.out.println("到达删除层");
            userService.deleteLog(id);
        }
    /**
     * @return
     */
        @RequestMapping("query")
        public String  query(){

            String s1 = userService.test("haa张三");

            System.out.println(s1);

            return s1;
        }

        @RequestMapping("queryLog")
        public String queryLog(String conditionSearch){
            System.out.println("到达消费者"+conditionSearch);
            List<Loger> logList = userService.queryLog(conditionSearch);
            JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
            return com.alibaba.fastjson.JSON.toJSONString(logList, SerializerFeature.WriteDateUseDateFormat);
        }



}
