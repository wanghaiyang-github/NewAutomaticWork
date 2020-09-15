package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.dao.ProgramRecordDao;
import com.bazl.lims.manager.model.po.ProgramRecord;
import com.bazl.lims.manager.services.common.ProgramRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class ProgramRecordServiceImpl extends BaseService implements ProgramRecordService {

    @Autowired
    ProgramRecordDao programRecordDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return programRecordDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(ProgramRecord record) {
        try {
            return programRecordDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public ProgramRecord selectByPrimaryKey(String id) {
        try {
            return programRecordDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<ProgramRecord> selectAll() {
        try {
            return programRecordDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(ProgramRecord record) {
        try {
            return programRecordDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }
}
