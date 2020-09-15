package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.PcrPlateDao;
import com.bazl.lims.manager.model.po.PcrPlate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class PcrPlateDaoImpl extends BaseDaoImpl implements PcrPlateDao {

    @Override
    public String namespace() { return PcrPlate.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(PcrPlate record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public PcrPlate selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<PcrPlate> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(PcrPlate record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }

    public int deleteFlagById(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteFlagById", id);
    }
}
