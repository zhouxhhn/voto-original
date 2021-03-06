[@override name="title"]账号管理 - 账号授权[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li><a href="/account/pagination">账号管理</a></li>
    <li>账号授权</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/account/authorize" method="post" data-parsley-validate>

            <input type="hidden" name="id" value="${account.id!account.id}"/>
            <input type="hidden" name="version" value="${account.version!account.version}"/>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">用户名*</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" value="${account.userName!}" disabled/>
                </div>
            </div>

            [@spring.bind "command.roles"/]
            <div class="form-group">
                <label for="description" class="col-md-3 control-label">角色*</label>
                <div class="col-md-9">
                    <div class="col-md-10 div-input role-data">
                        [#if account.roles?size > 0]
                        ${account.roles[0]}
                        [/#if]
                    </div>
                    <input type="hidden" name="roles" value="${account.roles[0]!}" id="role"/>
                    <button type="button" class="btn btn-primary col-md-2 role-modal-search-modal">点击选择角色</button>
                    [@spring.showErrors "role" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="submit" class="btn btn-success">授权</button>
            </div>
        </form>
    </div>
    <div class="col-lg-3">
        <ul class="blog-sidebar-list font-18">创建注意事项
            <li>*位必填项</li>
        </ul>
    </div>
</div>


<!-- 选择角色弹窗 -->
<div class="modal fade" id="role-modalSearch">
    <div class="modal-content">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"
                                                                               class="red">&times;</span><span
                        class="sr-only">Close</span></button>
                <p class="modal-title">角色列表--勾选添加到已选角色列表
                    <small class="text-muted"></small>
                </p>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-9 input-list">
                        <!-- 查询表单 -->
                        <form class="form-inline margin-md" role="form" action="[@spring.url '/permission/list' /]">
                            <div class="form-group">
                                <label class="control-label col-md-5" for="permissionName">角色名称</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" id="roleName"
                                           name="roleName" value="${command.roleName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-6" for="status">角色状态</label>
                                <div class="col-md-6">
                                    <select name="status" id="status" class="form-control">
                                        [#assign status = (command.status!)?default("") /]
                                        <option value="ALL">全部</option>
                                        <option value="ENABLE" [@mc.selected status "ENABLE" /]>启用</option>
                                        <option value="DISABLE" [@mc.selected status "DISABLE" /]>禁用</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-md btn-success">查询</button>
                            </div>
                        </form>
                        <!-- table数据 -->
                        <table class="table table-bordered table-sortable table-hover">
                            <thead>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <!-- 分页信息 -->
                        <div class="row">
                            <div class="col-sm-4 text-center">
                                <small class="inline table-options paging-info">
                                </small>
                            </div>
                            <div class="col-sm-4 text-right sm-center">
                                <ul class="pagination pagination-sm no-margin pagination-custom no-m-left">
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="tile-header">
                            <h4><strong>已选</strong>列表</h4>
                        </div>
                        <div class="tile-body selector-box modal-search-selector">
                            <button class="btn margin-top-15 btn-success role-modal-search-hide-modal">确定</button>
                            <button class="btn margin-top-15 btn-danger role-selector-remove-all">删除全部</button>
                            <div class="role-selector-box-data"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/js/modal-search-pengyi.js'/]"></script>
<script type="/resources/main/text/javascript">

    var _roleName = $(".role-data");
    var _roleData = $("#role");
    var _oldRoleIds = [];
    _oldRoleIds.push(_roleData.val());
    var _roleModal = new ModalSearch({
        url: "/role/list",
        pageSize: 6,
        isSingle: true,
        id: "role-modalSearch",
        openModalClass: ".role-modal-search-modal",
        hideModalClass: ".role-modal-search-hide-modal",
        removeAllClass: ".role-selector-remove-all",
        selectorBoxClass: ".role-selector-box-data",
        headers: ['角色名称', '角色描述'],
        rowDataName: ["name", "description"],
        selectorDateName: ["name"],
        oldDataIds: _oldRoleIds,
        hideModalHandler: function (jsonDataArr) {
            _roleName.text("");
            _roleData.val("");
            $.each(jsonDataArr, function (a, b) {
                _roleName.text(b.name + "-------" + b.description);
                _roleData.val(b.id);
            })
        }
    });
</script>
[/@override]
[@extends name="/decorator.ftl"/]