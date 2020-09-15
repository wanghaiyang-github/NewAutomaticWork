package com.bazl.lims.manager.dao;

import com.bazl.lims.manager.model.po.LabRole;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
public interface LabRoleDao extends BaseDao<LabRole, Integer> {

    public List<LabRole> selectAll();

    public List<LabRole> selectByLabUserId(Integer labUserId);

}
