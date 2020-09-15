package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.ServerDetailInfo;
import java.util.List;

/**
 * @author wanghaiyang
 * @date 2020/2/14.
 */
public interface ServerDetailInfoDao {

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
    public int insert(ServerDetailInfo record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public ServerDetailInfo selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     * @return
     */
    public List<ServerDetailInfo> selectAll();

    /**
     * 更新信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ServerDetailInfo record);
}