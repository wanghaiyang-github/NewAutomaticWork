package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.dao.TestRecordDao;
import com.bazl.lims.manager.model.po.TestRecord;
import com.bazl.lims.manager.model.vo.TestRecordVo;
import com.bazl.lims.manager.services.common.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class TestRecordServiceImpl extends BaseService implements TestRecordService {

    @Autowired
    TestRecordDao testRecordDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return testRecordDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(TestRecord record) {
        try {
            return testRecordDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public TestRecord selectByPrimaryKey(String id) {
        try {
            return testRecordDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<TestRecord> selectAll() {
        try {
            return testRecordDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(TestRecord record) {
        try {
            return testRecordDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<TestRecordVo> selectVoList(TestRecordVo query, PageInfo pageInfo) {
        List<TestRecordVo> testRecordVoList = null;
        try {
            int pageNo = pageInfo.getPage();
            int pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(pageSize);

            testRecordVoList = testRecordDao.selectPaginationList(query);
        } catch(Exception ex) {
            logger.info("分页查询列表信息错误："+ex);
            return null;
        }

        return testRecordVoList;
    }

    @Override
    public int selectVOCount(TestRecordVo query) {
        try {
            return testRecordDao.selectCount(query);
        }catch(Exception ex){
            logger.error("根据条件查询数量错误！", ex);
            throw ex;
        }
    }

    @Override
    public int deleteFlagById(String id) {
        try {
            return testRecordDao.deleteFlagById(id);
        }catch(Exception ex){
            logger.error("根据条件查询数量错误！", ex);
            throw ex;
        }
    }
}
