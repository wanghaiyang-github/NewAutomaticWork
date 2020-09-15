package com.bazl.lims.manager.services.common;

import com.bazl.lims.manager.PageInfo;
import com.bazl.lims.manager.model.po.PcrPlate;
import com.bazl.lims.manager.model.vo.PcrPlateVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface PcrPlateService {

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
    public int insert(PcrPlate record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public PcrPlate selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<PcrPlate> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PcrPlate record);

    /**
     * 分页查询列表信息
     * @param query
     * @param pageInfo
     * @return
     */
    public List<PcrPlateVo> selectVoList(PcrPlateVo query, PageInfo pageInfo);

    /**
     * 根据条件查询数量
     * @param query
     * @return
     */
    public int selectVOCount(PcrPlateVo query);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);
}
