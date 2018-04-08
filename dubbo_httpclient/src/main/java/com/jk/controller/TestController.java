package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.entity.Loger;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("http/client")
public class TestController {

        @RequestMapping(value = "updateLog", produces = {"application/text;charset=UTF-8"})
        @ResponseBody
        public String updateLog(String _id, String logId, String logMsg, String createTime) throws ParseException {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("_id", _id));
            nameValuePairs.add(new BasicNameValuePair("logId", logId));
            nameValuePairs.add(new BasicNameValuePair("logMsg", logMsg));
            nameValuePairs.add(new BasicNameValuePair("createTime", createTime));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpPut httpPut = new HttpPut("http://localhost:8042/app/user/updateLog");
                httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
                httpClient.execute(httpPut);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (httpClient != null) {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "修改成功";
        }

        @RequestMapping("queryLogById")
        public  String  queryLogById(String   id, Model model){

            System.out.println("--------"+id);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "http://localhost:8042/app/user/queryLogById";
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("id", id));
            HttpEntity entity=null;
            String  responseStr="";
            CloseableHttpResponse response = null;

            try {

                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
                response = httpClient.execute(httpPost);
                entity = response.getEntity();
                responseStr = EntityUtils.toString(entity);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (response != null) {

                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpClient != null) {

                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Loger loger = JSONObject.parseObject(responseStr, Loger.class);
            System.out.println(loger.toString());
            model.addAttribute("loger", loger);
            return "updateLog";
        }

        @ResponseBody
        @RequestMapping(value = "addLog", produces = {"application/text;charset=UTF-8"})
        public String  addLog(Integer logId, String logMsg, String createTime) throws ParseException {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(createTime);
            Loger loger = new Loger();
            loger.setLogId(logId);
            loger.setLogMsg(logMsg);
            loger.setCreateTime(date);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8042/app/user/saveLog");
            StringEntity entity = new StringEntity(JSON.toJSONString(loger), "UTF-8");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(entity);
            try {
                httpClient.execute(httpPost);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (httpClient != null) {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }

            return "添加成功";
        }

        @RequestMapping("skipAdd")
        public  String  skipAdd(){

            return "addLog";
        }

        @RequestMapping(value="batchDeleteLog", produces = {"application/text;charset=UTF-8"})
        @ResponseBody
        public  String  batchDeleteLog(String  id){

            //System.out.println("这是传过来的ID---"+id);
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpDelete httpdelete = new HttpDelete("http://localhost:8042/app/user/deleteLog/"+id+"");
            CloseableHttpResponse response = null;
            try {

                response = httpclient.execute(httpdelete);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (response != null) {

                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpclient != null) {

                    try {
                        httpclient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return "删除成功";
        }

        @RequestMapping("request")
        public void  Test(){

            System.out.println("请求已到达``");
        }

        @RequestMapping("start")
        public  String  start(){

            return "showPage";
        }

        @RequestMapping(value="start2", produces = {"application/text;charset=UTF-8"})
        @ResponseBody
        public String start2(String conditionSearch){

            CloseableHttpClient httpClient = HttpClients.createDefault();
            String url = "http://localhost:8042/app/user/queryLog";
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("conditionSearch", conditionSearch));
            String str = "";
            HttpEntity entity = null;
            String string = "";
            CloseableHttpResponse response = null;
            try {
                //转换为键值对
                str = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
                HttpGet httpGet = new HttpGet(url+"?"+str);
                response = httpClient.execute(httpGet);
                entity = response.getEntity();
                string = EntityUtils.toString(entity);

                //System.out.println("我是httpClient---"+string);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (response != null) {

                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpClient != null) {

                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return  string;
        }

}