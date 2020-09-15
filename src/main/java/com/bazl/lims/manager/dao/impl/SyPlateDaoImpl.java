package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.SyPlateDao;
import com.bazl.lims.manager.model.po.SyPlate;
import com.bazl.lims.manager.model.vo.SyPlateVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class SyPlateDaoImpl extends BaseDaoImpl implements SyPlateDao {

    @Override
    public String namespace() { return SyPlate.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(SyPlate record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public SyPlate selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<SyPlate> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(SyPlate record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }

    public int deleteFlagById(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteFlagById", id);
    }

    public List<SyPlateVo> selectVoListBySyPlateVo(SyPlateVo syPlateVo) {
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectVoListBySyPlateVo", syPlateVo);
    }

    public List<SyPlateVo> selectSampleTableBySyPlateId(String id) {
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectSampleTableBySyPlateId", id);
    }
}
