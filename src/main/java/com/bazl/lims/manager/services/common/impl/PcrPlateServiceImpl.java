package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.dao.PcrPlateDao;
import com.bazl.lims.manager.model.po.PcrPlate;
import com.bazl.lims.manager.model.vo.PcrPlateVo;
import com.bazl.lims.manager.services.common.PcrPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class PcrPlateServiceImpl extends BaseService implements PcrPlateService {

    @Autowired
    PcrPlateDao pcrPlateDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return pcrPlateDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(PcrPlate record) {
        try {
            return pcrPlateDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public PcrPlate selectByPrimaryKey(String id) {
        try {
            return pcrPlateDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<PcrPlate> selectAll() {
        try {
            return pcrPlateDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(PcrPlate record) {
        try {
            return pcrPlateDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<PcrPlateVo> selectVoList(PcrPlateVo query, PageInfo pageInfo) {
        List<PcrPlateVo> pcrPlateVoList = null;
        try {
            int pageNo = pageInfo.getPage();
            int pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(pageSize);

            pcrPlateVoList = pcrPlateDao.selectPaginationList(query);
        } catch(Exception ex) {
            logger.info("分页查询列表信息错误："+ex);
            return null;
        }

        return pcrPlateVoList;
    }

    @Override
    public int selectVOCount(PcrPlateVo query) {
        try {
            return pcrPlateDao.selectCount(query);
        }catch(Exception ex){
            logger.error("根据条件查询数量错误！", ex);
            throw ex;
        }
    }

    @Override
    public int deleteFlagById(String id) {
        try {
            return pcrPlateDao.deleteFlagById(id);
        }catch(Exception ex){
            logger.error("根据id删除错误！", ex);
            throw ex;
        }
    }
}
