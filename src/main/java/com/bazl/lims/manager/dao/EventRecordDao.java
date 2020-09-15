package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.EventRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface EventRecordDao extends BaseDao {

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
    public int insert(EventRecord record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public EventRecord selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<EventRecord> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(EventRecord record);
}