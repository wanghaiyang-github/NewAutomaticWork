package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.dao.TestDetailRecordDao;
import com.bazl.lims.manager.model.po.TestDetailRecord;
import com.bazl.lims.manager.services.common.TestDetailRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class TestDetailRecordServiceImpl extends BaseService implements TestDetailRecordService {

    @Autowired
    TestDetailRecordDao testDetailRecordDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return testDetailRecordDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(TestDetailRecord record) {
        try {
            return testDetailRecordDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public TestDetailRecord selectByPrimaryKey(String id) {
        try {
            return testDetailRecordDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<TestDetailRecord> selectAll() {
        try {
            return testDetailRecordDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(TestDetailRecord record) {
        try {
            return testDetailRecordDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }
}
