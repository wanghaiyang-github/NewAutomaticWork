package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.dao.EventRecordDao;
import com.bazl.lims.manager.model.po.EventRecord;
import com.bazl.lims.manager.services.common.EventRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class EventRecordServiceImpl extends BaseService implements EventRecordService {

    @Autowired
    EventRecordDao eventRecordDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return eventRecordDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(EventRecord record) {
        try {
            return eventRecordDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public EventRecord selectByPrimaryKey(String id) {
        try {
            return eventRecordDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<EventRecord> selectAll() {
        try {
            return eventRecordDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(EventRecord record) {
        try {
            return eventRecordDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }
}
