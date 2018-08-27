package bjl.domain.model.role;


import bjl.core.enums.EnableStatus;
import bjl.core.id.ConcurrencySafeEntity;
import bjl.domain.model.permission.Permission;

import java.util.Date;
import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public class Role extends ConcurrencySafeEntity {

    private String name;                    //角色名称
    private String description;                //角色描述
    private List<Permission> permissions;   //角色包含的权限集合
    private EnableStatus status;            //状态

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void changeStatus(EnableStatus status) {
        this.status = status;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public Role() {
        super();
    }

    public Role(String name, String description, List<Permission> permissions, EnableStatus status) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.status = status;
        this.setCreateDate(new Date());
    }
}
