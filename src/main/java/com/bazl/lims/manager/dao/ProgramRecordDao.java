package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.ProgramRecord;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface ProgramRecordDao extends BaseDao {

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
    public int insert(ProgramRecord record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public ProgramRecord selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<ProgramRecord> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ProgramRecord record);
}