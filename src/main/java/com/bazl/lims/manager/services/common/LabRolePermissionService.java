package com.bazl.lims.manager.services.common;

import com.bazl.lims.manager.model.po.LabRolePermission;

/**
 * Created by Administrator on 2017/10/11.
 */
public interface LabRolePermissionService {

    public int add(LabRolePermission labRolePermission);

    public int update(LabRolePermission labRolePermission);

}
