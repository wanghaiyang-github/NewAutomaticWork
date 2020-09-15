package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.dao.SampleTableDao;
import com.bazl.lims.manager.model.po.SampleTable;
import com.bazl.lims.manager.model.vo.SampleTableVo;
import com.bazl.lims.manager.services.common.SampleTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class SampleTableServiceImpl extends BaseService implements SampleTableService {

    @Autowired
    SampleTableDao sampleTableDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return sampleTableDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(SampleTable record) {
        try {
            return sampleTableDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public SampleTable selectByPrimaryKey(String id) {
        try {
            return sampleTableDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SampleTable> selectAll() {
        try {
            return sampleTableDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(SampleTable record) {
        try {
            return sampleTableDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SampleTableVo> selectVoList(SampleTableVo query, PageInfo pageInfo) {
        List<SampleTableVo> sampleTableVoList = null;
        try {
            int pageNo = pageInfo.getPage();
            int pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(pageSize);

            sampleTableVoList = sampleTableDao.selectPaginationList(query);
        } catch(Exception ex) {
            logger.info("分页查询列表信息错误："+ex);
            return null;
        }

        return sampleTableVoList;
    }

    @Override
    public int selectVOCount(SampleTableVo query) {
        try {
            return sampleTableDao.selectCount(query);
        }catch(Exception ex){
            logger.error("根据条件查询数量错误！", ex);
            throw ex;
        }
    }

    @Override
    public int deleteFlagById(String id) {
        try {
            return sampleTableDao.deleteFlagById(id);
        }catch(Exception ex){
            logger.error("根据条件删除信息错误！", ex);
            throw ex;
        }
    }
}
