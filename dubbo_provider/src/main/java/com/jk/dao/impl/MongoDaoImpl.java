package com.jk.dao.impl;

import com.jk.dao.MongoDao;
import com.jk.entity.Loger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;
@Repository
public class MongoDaoImpl implements MongoDao {

    @Resource
    private MongoTemplate  mongoTemplate;

    public List<Loger> queryLog(String conditionSearch) {

        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        if (conditionSearch != null && !"".equals(conditionSearch.trim())) {

            Criteria criteria = Criteria.where("logMsg").regex(conditionSearch);
            query.addCriteria(criteria);
        }
        List<Loger> loger = mongoTemplate.find(query, Loger.class, "loger");
        return loger;
    }

    public void deleteLog(String s) {

        Criteria criteria = Criteria.where("_id").is(s);
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(criteria);
        mongoTemplate.remove(query, Loger.class, "loger");
    }

    public void saveLog(Loger loger) {

        mongoTemplate.insert(loger, "loger");
    }

    public Loger queryLogById(String id) {

        Criteria criteria = Criteria.where("_id").is(id);
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(criteria);
        List<Loger> loger = mongoTemplate.find(query, Loger.class, "loger");
        if(loger.size() > 0){
            return loger.get(0);
        }
        return  null;
    }

    public void updateLog(Loger loger) {

        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").is(loger.get_id()));
        mongoTemplate.updateFirst(query, new Update().set("logMsg", loger.getLogMsg()).set("createTime", loger.getCreateTime()), Loger.class, "loger");
    }
}