package com.jk.dao;

import com.jk.entity.Loger;

import java.util.List;

public interface MongoDao {

    List<Loger> queryLog(String conditionSearch);

    void deleteLog(String s);

    void saveLog(Loger loger);

    Loger queryLogById(String id);

    void updateLog(Loger loger);
}
