let prefix = "/biz/goodsInfo";
let orgId = "";

$(function () {
    if(type == 5 || type == 6 || type == 9 || type == 10) {
        //县级和州级才会有组织下拉搜索
        initOrgSelect();
    }
	load();
	//监听键盘输入事件
    $('#goodsName').keydown(function (e) {
        if(e.keyCode == 13) { //enter键
            reLoad();
        }
    })
})


//初始化组织选择框
function initOrgSelect(){
    $('#orgSelect').combotree({
        url: '/biz/goodsInfo/initOrgSelect',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        panelWidth:270,
        // panelHeight:120,
        width:'270px',
        height:'38px',
        onLoadSuccess:function(node,data){
            $("#orgSelect").combotree('setValue',data[0].text);
        },
        onLoadError:function (error) {

        },
        onSelect:function (data) {
            // let opt = {
            //     query : {
            //         goodsName: $('#goodsName').val(),
            //         orgId : data.id
            //     }
            // }
            orgId = data.id;
            debugger
            reLoad()
            // $('#exampleTable').bootstrapTable('refresh');
        }
    });
}

function load() {
    // selectLoad();
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    debugger
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        goodsName: $('#goodsName').val().trim(),
                        // orgId: $("#orgSelect").combotree("getValue")
                        orgId: orgId
                    // orgId: $("#orgSelect").combotree("tree").tree('getSelected')['id']
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        field : 'goodsTypeName',
                        title : materialType,
                        align: 'center'
                    },
                    {
                        field : 'goodsName',
                        title : wzmc,
                        align: 'center'
                    },
                    {
                        field : 'goodsAmount',
                        title : issueNumber,
                        align: 'center'
                    },
                    {
                        field : 'distributorName',
                        title : issuingUnit,
                        align: 'center'
                    },
                    {
                        field : 'distributeDate',
                        title : outTime,
                        align: 'center',
                        //获取日期列的值进行转换
                        formatter: function (value, row, index) {
                            return changeDateFormat(value)
                        }

                    },
                    {
                        field : 'name',
                        title : receiving,
                        align: 'center',
                        formatter: function (value, row, index) {
                            if(row.receiverName != null) {
                                return row.receiverName;
                            }
                            if(row.orgName != null) {
                                return row.orgName;
                            }
                        }
                    },
                    {
                        visible : false,
                        field : 'sort',
                        title : Sort
                    },
                    {
                        visible : false,
                        field : 'parentId',
                        title : parentNumber
                    },
                    {
                        visible : false,
                        field : 'createBy',
                        title : theCreator
                    },
                    {
                        visible : false,
                        field : 'createDate',
                        title : creatorTime
                    },
                    {
                        visible : false,
                        field : 'updateBy',
                        title : updateThe
                    },
                    {
                        visible : false,
                        field : 'updateDate',
                        title : updateTime
                    },
                    {
                        visible : false,
                        field : 'remarks',
                        title : remarkInfo
                    },
                    {
                        visible : false,
                        field : 'delFlag',
                        title : deleteFlag
                    },
                    {
                        title : operation,
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var f = '<a class="btn btn-success btn-sm" href = "'+prefix+'/detail/'+row.id+'" target="menuFrame" title="查看">'+checkk+'</a>';
                            var e = '<a class="btn btn-info btn-sm" href = "'+prefix+'/edit/'+row.id+'" target="menuFrame" title="修改">'+updatee+'</a>';
                            var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')">'+deletee+'</a> ';
                            if(row.userId == row.operatorId) {      //自己创建的记录才有“修改”“删除”权限
                                return f+ ' '+ e+ ' ' + d;
                            }else {
                                return f;
                            }

                        }
                    } ]
            });
}

// function retOrgId() {
//     if(type == 5 || type == 6){
//         //县级、州级管理员
//         return $("#orgSelect").combotree("getValue");
//     }else {
//         return "";  //乡级管理员不根据orgId查
//     }
//
// }

//点击搜索
function reLoad() {
    // let opt = {
    //     query : {
    //         orgId : $('[name=deptId]').val(),
    //         goodsName : $('#goodsName').val()
    //     }
    // }
    // $('#exampleTable').bootstrapTable('refresh');
    $('#exampleTable').bootstrapTable('destroy');
    debugger
    load();
}

function remove(id) {
    layer.confirm(sureDeleteSelectedRecord, {
        btn : [determine, cancel ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(operationSuccess);
                    reLoad();
                } else {
                    layer.msg(operationFailure);
                }
            }
        });
    })
}
