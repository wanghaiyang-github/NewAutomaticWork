package com.bazl.lims.manager.model.po;

import java.io.Serializable;
import java.util.List;

public class LabRolePermission implements Serializable {
    private Integer id;

    private Integer roleId;

    private String roleName;

    private Integer permissionId;

    private List<LabRolePermission> labRolePermissionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public List<LabRolePermission> getLabRolePermissionList() {
        return labRolePermissionList;
    }

    public void setLabRolePermissionList(List<LabRolePermission> labRolePermissionList) {
        this.labRolePermissionList = labRolePermissionList;
    }
}