package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.SampleTable;
import com.bazl.lims.manager.model.vo.SampleTableVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface SampleTableDao extends BaseDao {

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
    public int insert(SampleTable record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public SampleTable selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<SampleTable> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(SampleTable record);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);
}