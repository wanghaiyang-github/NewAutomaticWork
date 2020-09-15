package com.bazl.lims.manager.services.common;

import com.bazl.lims.manager.model.po.LabPermission;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
public interface LabPermissionService {

    public List<LabPermission> selectAll();

    public List<LabPermission> selectListByRoleId(Integer roleId);
}
