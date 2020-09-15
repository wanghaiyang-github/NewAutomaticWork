package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.SampleTableDao;
import com.bazl.lims.manager.model.po.SampleTable;
import com.bazl.lims.manager.model.vo.SampleTableVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class SampleTableDaoImpl extends BaseDaoImpl implements SampleTableDao {

    @Override
    public String namespace() { return SampleTable.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(SampleTable record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public SampleTable selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<SampleTable> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(SampleTable record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }

    public List<SampleTableVo> selectVoList(SampleTableVo query) {
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectVoList", query);
    }

    public int selectVOCount(SampleTableVo query){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectVOCount", query);
    }

    public int deleteFlagById(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteFlagById", id);
    }
}
