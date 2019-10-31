
var prefix = "/biz/orgInfo"
$(function() {
    debugger
    load(orgPid);
});

function load(pid) {
    $('#orgInfoTable')
        .bootstrapTreeTable(
            {
                id : 'deptId',
                code : 'deptId',
                parentCode : 'parentId',
                type : "GET", // 请求数据的ajax类型
                url : prefix + '/list', // 请求数据的ajax的url
                ajaxParams : {}, // 请求数据的ajax的data属性
                expandColumn : '1', // 在哪一列上面显示展开按钮
                striped : true, // 是否各行渐变色
                bordered : true, // 是否显示边框
                expandAll : false, // 是否全部展开
                expandFirst : false, // 是否默认第一级展开--expandAll为false时生效
                rootCodeValue: pid,            // 设置根节点code值----可指定根节点，默认为null,"",0,"0"
                // expanderExpandedClass : 'glyphicon glyphicon-chevron-down',// 展开的按钮的图标
                // expanderCollapsedClass : 'glyphicon glyphicon-chevron-right',// 缩起的按钮的图标
                // toolbar : '#exampleToolbar',
                columns : [
                    {
                        title : '编号',
                        field : code,
                        visible : false,
                        align : 'left',
                        valign : 'center',
                        width : "50px",
                        checkbox : true
                    },
                    {
                        field : 'name',
                        title : orgName,
                        align : 'left',
                        valign : 'center',
                        width : "300px"
                    },
                    {
                        field : 'orderNum',
                        title :sort,
                        align : 'left',
                        valign: 'center',
                        width : "300px"
                    },
                    {
                        field : 'delFlag',
                        title : status5,
                        align : 'left',
                        valign: 'center',
                        width : "300px",
                        formatter : function(item, index) {
                            if (item.delFlag == '0') {
                                return '<span class="label label-danger">disabled5</span>';
                            } else if (item.delFlag == '1') {
                                return '<span class="label label-primary">normal5</span>';
                            }
                        }
                    },
                    {
                        title : operation5,
                        field : 'id',
                        align : 'left',
                        valign : 'center',
                        formatter : function(item,index) {
                            debugger
                            //初始化默认显示新增和编辑按钮
                            s_add_h = '';
                            s_edit_h = '';
                            var type = item.type;
                            if(type == 3){
                                s_add_h = 'hidden';
                                s_edit_h = 'hidden';
                            }
                            s_remove_h = '';
                            if(deptId==item.deptId){
                                s_remove_h = 'hidden';
                            }
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + item.deptId
                                + '\')"><i class="fa fa-edit"></i>'+edit5+'</a> ';
                            var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '" href="#" title="新增下級"  mce_href="#" onclick="add('
                                + item.deptId +","+item.type
                                + ')"><i class="fa fa-plus"></i>'+add5+'</a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="removeone(\''
                                + item.deptId
                                + '\')"><i class="fa fa-remove">'+delete5+'</i></a> ';
                            var f = '<a class="btn btn-success btn-sm＂ href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + item.deptId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + a + d;
                        }
                    } ]
            });
}


function reLoad(orgPid) {
    load(orgPid);
}
function add(pId,type) {
    debugger

    layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add/?pId=' + pId+"&type="+type
    });
}
function edit(id) {
    layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}
function removeone(id) {
    layer.confirm(sureDeleteSelectedRecord, {
        btn : [ yesT, noT ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'deptId' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    //操作成功
                    layer.msg(operationSuccess);
                    reLoad(orgPid);
                } else if(r.code==1) {
                    //演示系统不允许修改,完整体验请部署程序
                    layer.msg(removeoneTitle1T);
                }else if(r.code==2) {
                    //包含下级部门,不允许修改
                    layer.msg(removeoneTitle2T);
                }else if(r.code==3) {
                    //部门包含用户,不允许修改
                    layer.msg(removeoneTitle3T);
                }
            }
        });
    })
}

function resetPwd(id) {
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['deptId'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix + '/batchRemove',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {});
}





