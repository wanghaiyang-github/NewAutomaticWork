package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.dao.ServerInfoDao;
import com.bazl.lims.manager.model.po.ServerInfo;
import com.bazl.lims.manager.services.common.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/14.
 */
@Service
public class ServerInfoServiceImpl  extends BaseService implements ServerInfoService {

    @Autowired
    ServerInfoDao serverInfoDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            return serverInfoDao.deleteByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id删除信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int insert(ServerInfo record) {
        try {
            return serverInfoDao.insert(record);
        }catch(Exception ex){
            logger.error("添加信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public ServerInfo selectByPrimaryKey(String id) {
        try {
            return serverInfoDao.selectByPrimaryKey(id);
        }catch(Exception ex){
            logger.error("根据id查询信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public List<ServerInfo> selectAll() {
        try {
            return serverInfoDao.selectAll();
        }catch(Exception ex){
            logger.error("查询所有信息错误！", ex);
            throw ex;
        }
    }

    @Override
    public int updateByPrimaryKey(ServerInfo record) {
        try {
            return serverInfoDao.updateByPrimaryKey(record);
        }catch(Exception ex){
            logger.error("更新信息错误！", ex);
            throw ex;
        }
    }
}
