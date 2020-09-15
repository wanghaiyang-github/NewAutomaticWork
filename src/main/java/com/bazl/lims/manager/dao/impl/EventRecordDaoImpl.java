package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.EventRecordDao;
import com.bazl.lims.manager.model.po.EventRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class EventRecordDaoImpl extends BaseDaoImpl implements EventRecordDao {

    @Override
    public String namespace() { return EventRecord.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(EventRecord record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public EventRecord selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<EventRecord> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(EventRecord record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }
}
