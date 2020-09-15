package com.bazl.lims.manager.services.common;

import com.bazl.lims.manager.model.po.LabRole;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
public interface LabRoleService {

    public LabRole selectById(Integer labRoleId);

    public List<LabRole> selectAll();

    public List<LabRole> selectByLabUserId(Integer labUserId);

}
