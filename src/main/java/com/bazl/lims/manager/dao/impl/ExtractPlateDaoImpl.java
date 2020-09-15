package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.ExtractPlateDao;
import com.bazl.lims.manager.model.po.ExtractPlate;
import com.bazl.lims.manager.model.vo.ExtractPlateVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class ExtractPlateDaoImpl extends BaseDaoImpl implements ExtractPlateDao {
    @Override
    public String namespace() { return ExtractPlate.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(ExtractPlate record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public ExtractPlate selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<ExtractPlate> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(ExtractPlate record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }

    public int deleteFlagById(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteFlagById", id);
    }

    public List<ExtractPlateVo> selectListVo(ExtractPlateVo extractPlateVo) {
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectListVo", extractPlateVo);
    }
}
