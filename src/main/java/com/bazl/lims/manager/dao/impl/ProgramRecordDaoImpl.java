package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.ProgramRecordDao;
import com.bazl.lims.manager.model.po.ProgramRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class ProgramRecordDaoImpl extends BaseDaoImpl implements ProgramRecordDao {

    @Override
    public String namespace() { return ProgramRecord.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(ProgramRecord record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public ProgramRecord selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<ProgramRecord> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(ProgramRecord record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }
}
