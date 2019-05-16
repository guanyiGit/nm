var prefix = "/biz/homePage"
$(function() {
    load();
});

function load() {
    $('#exampleTable1')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/getUnreadMsgs", // 服务器数据的加载地址
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
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        status: 0   //查询未读消息
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
                        field: 'id',
                        visible: false
                    },
                    {
                        field : 'title',
                        // title : '标题',
                        align: 'center',
                        formatter: function(value,row,index){
                            return '<a href="#" onclick="read(\''+row.id+'\',\''+row.status+'\')">'+row.title+'</a>';
                        }
                    },
                    {
                        field : 'createDate',
                        // title : '时间',
                        align: 'center',
                        //获取日期列的值进行转换
                        formatter: function (value, row, index) {
                            return changeDateFormat(value)
                        }
                    }
                    // {
                    //     title : '操作',
                    //     field : 'id',
                    //     align : 'center',
                    //     formatter : function(value, row, index) {
                    //         /*var f = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="查看" onclick="detail(\''
                    //             + row.perMonth
                    //             + '\')"><i class="fa"></i>查看</a> ';*/
                    //         var f = '<a class="btn btn-primary btn-sm" href = "'+prefix+'/detail/'+row.perMonth+'" target="menuFrame" title="查看"><i class="fa"></i>查看</a>';
                    //         var e = '<a class="btn btn-success btn-sm" href="#" mce_href="#" title="导出" onclick="edit(\''
                    //             + row.perMonth
                    //             + '\')"><i class="fa"></i>导出</a> ';
                    //         return f+e;
                    //     }
                    // }
                ]
            });
    $('#exampleTable2')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/getReadedMsgs", // 服务器数据的加载地址
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
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        status: 1       //查询已读消息
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
                        field: 'id',
                        visible: false
                    },
                    {
                        field : 'title',
                        // title : '标题',
                        align: 'center',
                        formatter: function(value,row,index){
                            return '<a href="#" onclick="read(\''+row.id+'\',\''+row.status+'\')">'+row.title+'</a>';
                        }
                    },
                    {
                        field : 'createDate',
                        // title : '时间',
                        align: 'center',
                        //获取日期列的值进行转换
                        formatter: function (value, row, index) {
                            return changeDateFormat(value)
                        }
                    }
                    // {
                    //     title : '操作',
                    //     field : 'id',
                    //     align : 'center',
                    //     formatter : function(value, row, index) {
                    //         /*var f = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="查看" onclick="detail(\''
                    //             + row.perMonth
                    //             + '\')"><i class="fa"></i>查看</a> ';*/
                    //         var f = '<a class="btn btn-primary btn-sm" href = "'+prefix+'/detail/'+row.perMonth+'" target="menuFrame" title="查看"><i class="fa"></i>查看</a>';
                    //         var e = '<a class="btn btn-success btn-sm" href="#" mce_href="#" title="导出" onclick="edit(\''
                    //             + row.perMonth
                    //             + '\')"><i class="fa"></i>导出</a> ';
                    //         return f+e;
                    //     }
                    // }
                ]
            });
}
function read(id,status) {
    layer.open({
        type : 2,
        title : '消息详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/read/' + id +'/'+status,// iframe的url
        end: function () {
            // location.reload();
            $('#exampleTable1').bootstrapTable('refresh');
            $('#exampleTable2').bootstrapTable('refresh');
        }
    });
}
