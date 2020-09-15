package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.TestRecordDao;
import com.bazl.lims.manager.model.po.TestRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class TestRecordDaoImpl extends BaseDaoImpl implements TestRecordDao {

    @Override
    public String namespace() { return TestRecord.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(TestRecord record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public TestRecord selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<TestRecord> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(TestRecord record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }

    public int deleteFlagById(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteFlagById", id);
    }
}
