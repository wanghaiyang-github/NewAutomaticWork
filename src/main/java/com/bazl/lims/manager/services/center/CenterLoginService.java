package com.bazl.lims.manager.services.center;

import com.bazl.lims.manager.model.po.LabUser;

import java.util.Set;

/**
 * Created by Administrator on 2016/12/28.
 */
public interface CenterLoginService {

    LabUser selectByLoginName(String loginName);

    Set<String> selectRoleIdsByUserId(Integer userId);

    Set<String> selectPermissionIdsByUserId(Integer userId);

}
