package bjl.application.role.representation;


import bjl.application.permission.representation.PermissionRepresentation;
import bjl.core.enums.EnableStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by pengyi on 2016/3/30 0030.
 */
public class RoleRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String name;                    //角色名称
    private String description;                //角色描述
    private List<PermissionRepresentation> permissions;   //角色包含的权限集合
    private EnableStatus status;            //状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PermissionRepresentation> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionRepresentation> permissions) {
        this.permissions = permissions;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
