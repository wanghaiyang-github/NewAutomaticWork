package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.TestDetailRecordDao;
import com.bazl.lims.manager.model.po.TestDetailRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class TestDetailRecordDaoImpl extends BaseDaoImpl implements TestDetailRecordDao {

    @Override
    public String namespace() { return TestDetailRecord.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(TestDetailRecord record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public TestDetailRecord selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<TestDetailRecord> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(TestDetailRecord record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }
}
