package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.SyPlate;
import com.bazl.lims.manager.model.vo.SyPlateVo;

import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/4.
 */
public interface SyPlateDao extends BaseDao {

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
    public int insert(SyPlate record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public SyPlate selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<SyPlate> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(SyPlate record);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public int deleteFlagById(String id);

    /**
     * 根据条件查询信息
     * @param syPlateVo
     * @return
     */
    public List<SyPlateVo> selectVoListBySyPlateVo(SyPlateVo syPlateVo);

    /**
     * 根据主键id查询样本板信息
     * @param id
     * @return
     */
    public List<SyPlateVo> selectSampleTableBySyPlateId(String id);
}