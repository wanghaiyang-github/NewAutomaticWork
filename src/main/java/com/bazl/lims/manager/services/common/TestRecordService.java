package com.bazl.lims.manager.services.common;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.TestRecord;
import com.bazl.lims.manager.model.vo.TestRecordVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface TestRecordService {

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
    public int insert(TestRecord record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public TestRecord selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<TestRecord> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(TestRecord record);

    /**
     * 分页查询列表信息
     * @param query
     * @param pageInfo
     * @return
     */
    public List<TestRecordVo> selectVoList(TestRecordVo query, PageInfo pageInfo);

    /**
     * 根据条件查询数量
     * @param query
     * @return
     */
    public int selectVOCount(TestRecordVo query);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);

}
