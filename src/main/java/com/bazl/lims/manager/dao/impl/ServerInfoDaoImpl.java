package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.ServerInfoDao;
import com.bazl.lims.manager.model.po.ServerInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/14.
 */
@Repository
public class ServerInfoDaoImpl extends BaseDaoImpl implements ServerInfoDao {

    @Override
    public String namespace() { return ServerInfo.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(ServerInfo record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public ServerInfo selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<ServerInfo> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(ServerInfo record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }
}
