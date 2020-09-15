package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.ExtractPlate;
import com.bazl.lims.manager.model.vo.ExtractPlateVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface ExtractPlateDao extends BaseDao {

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
    public int insert(ExtractPlate record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public ExtractPlate selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<ExtractPlate> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ExtractPlate record);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);

    /**
     * 根据条件查询提取表信息
     * @param extractPlateVo
     * @return
     */
    public List<ExtractPlateVo> selectListVo(ExtractPlateVo extractPlateVo);
}