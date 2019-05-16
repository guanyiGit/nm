


var prefix = "/biz/userInfo";
$(function () {
    // $("#userKeyDown").keydown((e)=>{
    //     if(e.keyCode=="13"){
    //         $("#search").click();
    //     }
    // });

    table.initUserInfoTable();
    table.selectByUserNameOrNo();
});

var  table = {
    initUserInfoTable:function () {
        $('#userInfoTable').bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                // toolbar : '#exampleToolbar',
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
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求

                /**
                 *inf.id,inf.user_no userNo,inf.`name` , inf.phone_num phoneNum,org.depart_name orgName,
                 role.role_name roleName,inf.create_date createDate,inf.`status`
                 */
                columns :[[
                    // {
                    //     field : 'userNo',
                    //     title : '用户编号',
                    //     align: "center"
                    // },
                    {
                        field : 'name',
                        title : name5,
                        align: "center"
                    },
                    {
                        field : 'mobile',
                        title : phone5,
                        align: "center",

                    },
                    {
                        field : 'deptName',
                        title : org5,
                        align: "center",

                    }
                    ,
                    {
                        field : 'roles',
                        title : role5,
                        align: "center",
                        formatter:function (value, row, index) {
                            var buffer = new StringBuffer();
                            for(var index in value){
                                 buffer.append(value[index].roleName+"  ");
                            }
                            return buffer.toString();
                        }

                    }
                    ,
                    {
                        field : 'gmtCreate',
                        title : createTime5,
                        align: "center",
                        formatter:function (value, row, index) {
                            return changeDateFormat(value);
                        }

                    }
                    ,
                    {
                        field : 'status',
                        title : isUse5,
                        align: "center",
                        formatter:function (value, row, index) {
                            if (value==0)
                                return sure5;
                            else
                                return notSure5;
                        }

                    },
                    {
                        field : '123',
                        title : operation5,
                        align: "center",
                        formatter:function (value, row, index) {

                            var e = '<a class="btn btn-info btn-sm '+'" href="/biz/userInfo/edit/ '+row.userId+'" ' +
                                ' mce_href="#" title="修改" ><i class="fa"></i>'+edit5+'</a> ';
                            var d = '<a class="btn btn-warning btn-sm '+'" href="#" title="删除"  mce_href="#" onclick="deleteUser('+row.userId+')"><i class="fa"></i>'+delete5+'</a> ';
                            return e + d;
                        }
                    } ]]
            });
    },
    selectByUserNameOrNo:function () {//根据电子围栏名称查询
        $("#search").click(function() {
            debugger
            var opt = {
                query : {
                    name:$("#userNameOrNo").val()
                }
            }
            $('#userInfoTable').bootstrapTable('refresh',opt);
        });
    }
};

function deleteUser(userId) {
    layer.confirm(yesDeleteUserT+'？', {
        btn : [ yesT, cancel5 ]
    }, function() {
        $.ajax({
            cache : true,
            type : "POST",
            url : "/biz/userInfo/remove",
            data : {id:userId},
            async : false,
            error : function(request) {
                layer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    debugger
                    $('#userInfoTable').bootstrapTable('refresh');
                    layer.msg(yesDeleteT);
                    // parent.reLoad();
                    // var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                } else {
                    layer.alert(data.msg)
                }
                // if (r.code == 0) {
                //     layer.msg(r.msg);
                //     // reLoad();
                // } else {
                //     layer.msg(r.msg);
                // }

            }
        });
    })

}



