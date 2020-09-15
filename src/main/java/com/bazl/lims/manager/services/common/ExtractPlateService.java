package com.bazl.lims.manager.services.common;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.ExtractPlate;
import com.bazl.lims.manager.model.vo.ExtractPlateVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface ExtractPlateService {

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
     * 分页查询列表信息
     * @param query
     * @param pageInfo
     * @return
     */
    public List<ExtractPlateVo> selectVoList(ExtractPlateVo query, PageInfo pageInfo);

    /**
     * 根据条件查询数量
     * @param query
     * @return
     */
    public int selectVOCount(ExtractPlateVo query);

    /**
     * 删除信息
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
