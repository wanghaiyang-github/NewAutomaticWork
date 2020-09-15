package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.dao.SampleInfoDao;
import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import com.bazl.lims.manager.services.common.SampleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class SampleInfoServiceImpl extends BaseService implements SampleInfoService {

    @Autowired
    SampleInfoDao sampleInfoDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return sampleInfoDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(SampleInfo record) {
        try {
            return sampleInfoDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public SampleInfo selectByPrimaryKey(String id) {
        try {
            return sampleInfoDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SampleInfo> selectAll() {
        try {
            return sampleInfoDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(SampleInfo record) {
        try {
            return sampleInfoDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SampleInfoVo> selectVoList(SampleInfoVo query, PageInfo pageInfo) {
        List<SampleInfoVo> sampleInfoVoList = null;
        try {
            int pageNo = pageInfo.getPage();
            int pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(pageSize);

            sampleInfoVoList = sampleInfoDao.selectPaginationList(query);
        } catch(Exception ex) {
            logger.info("分页查询列表信息错误："+ex);
            return null;
        }

        return sampleInfoVoList;
    }

    @Override
    public int selectVOCount(SampleInfoVo query) {
        try {
            return sampleInfoDao.selectCount(query);
        }catch(Exception ex){
            logger.error("根据条件查询数量错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SampleInfoVo> selectVoListBySampleInfo(SampleInfoVo query) {
        try {
            return sampleInfoDao.selectVoListBySampleInfo(query);
        }catch(Exception ex){
            logger.error("根据条件查询检材信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SampleInfoVo> selectListVo(SampleInfoVo query) {
        try {
            return sampleInfoDao.selectListVo(query);
        }catch(Exception ex){
            logger.error("根据条件查询不重复检材信息！", ex);
            throw ex;
        }
    }

    @Override
    public int deleteFlagById(String id) {
        try {
            return sampleInfoDao.deleteFlagById(id);
        }catch(Exception ex){
            logger.error("根据条件删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateBySampleTableId(SampleInfo sampleInfo) {
        try {
            return sampleInfoDao.updateBySampleTableId(sampleInfo);
        }catch(Exception ex){
            logger.error("根据条件更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByExtractPlateId(SampleInfo sampleInfo) {
        try {
            return sampleInfoDao.updateByExtractPlateId(sampleInfo);
        }catch(Exception ex){
            logger.error("根据条件更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPcrPlateId(SampleInfo sampleInfo) {
        try {
            return sampleInfoDao.updateByPcrPlateId(sampleInfo);
        }catch(Exception ex){
            logger.error("根据条件更新信息错误！", ex);
            throw ex;
        }
    }
}
