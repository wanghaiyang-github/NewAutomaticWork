package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.dao.LabPermissionDao;
import com.bazl.lims.manager.model.po.LabPermission;
import com.bazl.lims.manager.services.common.LabPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
@Service
public class LabPermissionServiceImpl implements LabPermissionService {

    @Autowired
    LabPermissionDao labPermissionDao;

    public List<LabPermission> selectAll() {
        return labPermissionDao.selectAll();
    }

    public List<LabPermission> selectListByRoleId(Integer roleId) {
        return this.labPermissionDao.selectListByRoleId(roleId);
    }

}
