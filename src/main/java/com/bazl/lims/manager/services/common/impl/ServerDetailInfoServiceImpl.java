package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.dao.ServerDetailInfoDao;
import com.bazl.lims.manager.model.po.ServerDetailInfo;
import com.bazl.lims.manager.services.common.ServerDetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/14.
 */
@Service
public class ServerDetailInfoServiceImpl extends BaseService implements ServerDetailInfoService {

    @Autowired
    ServerDetailInfoDao serverDetailInfoDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return serverDetailInfoDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(ServerDetailInfo record) {
        try {
            return serverDetailInfoDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public ServerDetailInfo selectByPrimaryKey(String id) {
        try {
            return serverDetailInfoDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<ServerDetailInfo> selectAll() {
        try {
            return serverDetailInfoDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(ServerDetailInfo record) {
        try {
            return serverDetailInfoDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }
}
