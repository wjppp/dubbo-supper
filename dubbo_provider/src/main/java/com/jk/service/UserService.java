package com.jk.service;

import com.jk.entity.Loger;


import java.util.List;

public interface UserService {

    String test(String name);

    /*
     * 查询日志
     * */
    List<Loger> queryLog(String conditionSearch);
    /*
    * 批量删除日志
    * */
    void deleteLog(String id);

    void saveLog(Loger loger);

    Loger queryLogById(String id);

    void updateLog(Loger loger);
}
