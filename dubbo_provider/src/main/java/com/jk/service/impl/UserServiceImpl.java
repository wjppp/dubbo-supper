package com.jk.service.impl;

import com.jk.dao.MongoDao;
import com.jk.entity.Loger;
import com.jk.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl  implements UserService {

    @Resource
    private MongoDao  mongoDao;

    public String test(String name) {

        System.out.println(name);

        return "我是返回的数据";
    }

    public List<Loger> queryLog(String conditionSearch) {

        //System.out.println("进步神速");
        return mongoDao.queryLog(conditionSearch);
    }

    public void deleteLog(String id) {

        String substring = id.substring(1);
        String[] split = substring.split("-");
        for (int i = 0; i < split.length; i++) {

            mongoDao.deleteLog(split[i]);
        }
    }

    public void saveLog(Loger loger) {

        System.out.println("生产者--"+loger.toString());
        mongoDao.saveLog(loger);
    }

    public Loger queryLogById(String id) {
        System.out.println("到达serviceimpl---"+id);
        return mongoDao.queryLogById(id);
    }

    public void updateLog(Loger loger) {

        System.out.println(loger.toString());
        mongoDao.updateLog(loger);
    }


}
