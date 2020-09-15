package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.dao.ExtractPlateDao;
import com.bazl.lims.manager.model.po.ExtractPlate;
import com.bazl.lims.manager.model.vo.ExtractPlateVo;
import com.bazl.lims.manager.services.common.ExtractPlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Service
public class ExtractPlateServiceImpl extends BaseService implements ExtractPlateService {

    @Autowired
    ExtractPlateDao extractPlateDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return extractPlateDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(ExtractPlate record) {
        try {
            return extractPlateDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public ExtractPlate selectByPrimaryKey(String id) {
        try {
            return extractPlateDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<ExtractPlate> selectAll() {
        try {
            return extractPlateDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(ExtractPlate record) {
        try {
            return extractPlateDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<ExtractPlateVo> selectVoList(ExtractPlateVo query, PageInfo pageInfo) {
        List<ExtractPlateVo> extractPlateVoList = null;
        try {
            int pageNo = pageInfo.getPage();
            int pageSize = pageInfo.getEvePageRecordCnt();
            query.setOffset((pageNo - 1) * pageSize);
            query.setRows(pageSize);

            extractPlateVoList = extractPlateDao.selectPaginationList(query);
        } catch(Exception ex) {
            logger.info("分页查询列表信息错误："+ex);
            return null;
        }

        return extractPlateVoList;
    }

    @Override
    public int selectVOCount(ExtractPlateVo query) {
        try {
            return extractPlateDao.selectCount(query);
        }catch(Exception ex){
            logger.error("根据条件查询数量错误！", ex);
            throw ex;
        }
    }

    @Override
    public int deleteFlagById(String id) {
        try {
            return extractPlateDao.deleteFlagById(id);
        } catch (Exception ex) {
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<ExtractPlateVo> selectListVo(ExtractPlateVo extractPlateVo) {
        try {
            return extractPlateDao.selectListVo(extractPlateVo);
        } catch (Exception ex) {
            logger.error("根据条件查询提取表信息！", ex);
            throw ex;
        }
    }
}
