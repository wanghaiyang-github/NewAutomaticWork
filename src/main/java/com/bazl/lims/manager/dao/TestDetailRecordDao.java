package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.TestDetailRecord;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface TestDetailRecordDao extends BaseDao {

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 插入信息
     * @param record
     * @return
     */
    public int insert(TestDetailRecord record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public TestDetailRecord selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<TestDetailRecord> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(TestDetailRecord record);
}