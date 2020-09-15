package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.ServerDetailInfoDao;
import com.bazl.lims.manager.model.po.ServerDetailInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/14.
 */
@Repository
public class ServerDetailInfoDaoImpl extends BaseDaoImpl implements ServerDetailInfoDao {

    @Override
    public String namespace() { return ServerDetailInfo.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(ServerDetailInfo record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public ServerDetailInfo selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<ServerDetailInfo> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(ServerDetailInfo record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }
}
