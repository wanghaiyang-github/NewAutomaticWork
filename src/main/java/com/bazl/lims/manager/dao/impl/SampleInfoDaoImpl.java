package com.bazl.lims.manager.dao.impl;

import com.bazl.lims.manager.dao.SampleInfoDao;
import com.bazl.lims.manager.model.po.SampleInfo;
import com.bazl.lims.manager.model.vo.SampleInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
@Repository
public class SampleInfoDaoImpl extends BaseDaoImpl implements SampleInfoDao {

    @Override
    public String namespace() { return SampleInfo.class.getName(); }

    public int deleteByPrimaryKey(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteByPrimaryKey", id);
    }

    public int insert(SampleInfo record){
        return this.getSqlSessionTemplate().insert(this.namespace() + ".insert", record);
    }

    public SampleInfo selectByPrimaryKey(String id){
        return this.getSqlSessionTemplate().selectOne(this.namespace() + ".selectByPrimaryKey", id);
    }

    public List<SampleInfo> selectAll(){
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectAll");
    }

    public int updateByPrimaryKey(SampleInfo record){
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPrimaryKey", record);
    }

    public List<SampleInfoVo> selectVoListBySampleInfo(SampleInfoVo query) {
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectVoListBySampleInfo", query);
    }

    public List<SampleInfoVo> selectListVo(SampleInfoVo query) {
        return this.getSqlSessionTemplate().selectList(this.namespace() + ".selectListVo", query);
    }

    public int deleteFlagById(String id) {
        return this.getSqlSessionTemplate().delete(this.namespace() + ".deleteFlagById", id);
    }

    public int updateBySampleTableId(SampleInfo sampleInfo) {
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateBySampleTableId", sampleInfo);
    }

    public int updateByExtractPlateId(SampleInfo sampleInfo) {
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByExtractPlateId", sampleInfo);
    }

    public int updateByPcrPlateId(SampleInfo sampleInfo) {
        return this.getSqlSessionTemplate().update(this.namespace() + ".updateByPcrPlateId", sampleInfo);
    }
}
