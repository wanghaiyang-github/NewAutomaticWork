package com.bazl.lims.manager.services.common.impl;

import com.bazl.lims.manager.dao.LabRoleDao;
import com.bazl.lims.manager.model.po.LabRole;
import com.bazl.lims.manager.services.common.LabRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
@Service
public class LabRoleServiceImpl implements LabRoleService {

    @Autowired
    LabRoleDao labRoleDao;

    public LabRole selectById(Integer labRoleId) {
        return labRoleDao.selectById(labRoleId);
    }

    public List<LabRole> selectAll() {
        return labRoleDao.selectAll();
    }

    public List<LabRole> selectByLabUserId(Integer labUserId){
        return labRoleDao.selectByLabUserId(labUserId);
    }

}
