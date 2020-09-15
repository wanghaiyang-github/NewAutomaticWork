package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.dao.SyPlateDao;
import com.bazl.lims.manager.model.po.SyPlate;
import com.bazl.lims.manager.model.vo.SyPlateVo;
import com.bazl.lims.manager.services.common.SyPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class SyPlateServiceImpl extends BaseService implements SyPlateService {

    @Autowired
    SyPlateDao syPlateDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return syPlateDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(SyPlate record) {
        try {
            return syPlateDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public SyPlate selectByPrimaryKey(String id) {
        try {
            return syPlateDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SyPlate> selectAll() {
        try {
            return syPlateDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(SyPlate record) {
        try {
            return syPlateDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SyPlateVo> selectVoList(SyPlateVo query, PageInfo pageInfo) {
        List<SyPlateVo> syPlateVoList = null;
        try {
            int pageNo = pageInfo.getPage();
            int pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(pageSize);

            syPlateVoList = syPlateDao.selectPaginationList(query);
        } catch(Exception ex) {
            logger.info("分页查询列表信息错误："+ex);
            return null;
        }

        return syPlateVoList;
    }

    @Override
    public int selectVOCount(SyPlateVo query) {
        try {
            return syPlateDao.selectCount(query);
        }catch(Exception ex){
            logger.error("根据条件查询数量错误！", ex);
            throw ex;
        }
    }

    @Override
    public int deleteFlagById(String id) {
        try {
            return syPlateDao.deleteFlagById(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SyPlateVo> selectVoListBySyPlateVo(SyPlateVo syPlateVo) {
        try {
            return syPlateDao.selectVoListBySyPlateVo(syPlateVo);
        }catch(Exception ex){
            logger.error("根据条件查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<SyPlateVo> selectSampleTableBySyPlateId(String id) {
        try {
            return syPlateDao.selectSampleTableBySyPlateId(id);
        }catch(Exception ex){
            logger.error("根据主键id查询样本板信息！", ex);
            throw ex;
        }
    }
}
