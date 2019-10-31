var model = {
    formatDate: function(date) {
        var datetime = new Date(date);
        var year = datetime.getFullYear();
        var month = (datetime.getMonth() + 1) < 10 ? ("0" + (datetime.getMonth() + 1)) : (datetime.getMonth() + 1);
        var day = datetime.getDate() < 10 ? ("0" + datetime.getDate()) : (datetime.getDate());
        var hours = datetime.getHours() < 10 ? ("0" + datetime.getHours()) : (datetime.getHours());
        var min = datetime.getMinutes() < 10 ? ("0" + datetime.getMinutes()) : (datetime.getMinutes());
        // return year + "-" + month + "-" + day + " " + hours + ":" + min;
        return year + "-" + month + "-" + day;
    },
    DateToUnix: function(string) {
        var f = string.split('-', 2);
        var d = (f[0] ? f[0] : '').split('-', 3);
        var t = (f[1] ? f[1] : '').split(':', 3);
        return (new Date(
            parseInt(d[0], 10) || null,
            (parseInt(d[1], 10) || 1) - 1,
            parseInt(d[2], 10) || null,
            parseInt(t[0], 10) || null,
            parseInt(t[1], 10) || null,
            parseInt(t[2], 10) || null
        )).getTime()/1000;
    }
}
var prefix = "/biz/veterinarian";
var orgId=1;
$(function() {
	//获取登录用户信息,待完善
	//var user=
    load();
    
    
    $("#enterKey").keydown(function(e) {
    	if (e.keyCode == 13) {
    		reLoad();
    	}
    });
});



function load() {
    // selectLoad();
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/findVeterinarianList", // 服务器数据的加载地址
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
                    	index : params.offset,
                    	pageSize : params.limit,
                    	orgId:orgId,
                    	searchKey : $('#searchKey').val()
                        // name:$('#searchName').val(),
                        /*type : $('#searchName').val(),*/
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                	/*{
                        field: '',
                        title: '照片',
                        width:160,
                        checkbox:true,
                        showSelectTitle:true,
                        formatter(value,row,index,field){
                            let buffer = new StringBuffer();
                            buffer.append('<div  class="row" style="width:162px;height: 120px;">');
                            buffer.append('<div class="col-sm-12">');
                            buffer.append('<span style="display: inline-block;height: 20px;width: 20px; background-color: #fff;position: absolute;top: 1px;left: 16px;"></span>');
                            buffer.append('<img src=../../../static/img/1.png style="border: solid 1px #aaa;""/>');
                            buffer.append('<div>');
                            buffer.append('<div >');
                            return buffer.toString();
                        }
                    	
                    },*/
                    {
                        field : 'name',
                        title : '姓名',
                        align: 'center'
                    },
                    {
                        field : 'orgInfo',
                        title : '所属兽医站',
                        width : '100px',
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==null){
                        		return "";
                        	}else{
                        		return value.departName;
                        	}
                        }
                    },
                    {
                        field : 'sex',
                        title : '性别',
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==0){
                        		return "男";
                        	}if(value==1){
                        		return "女";
                        	}
                        }
                    },
                    {
                        field : 'birthDay',
                        title : '出生日期',
                        align: 'center',
                            formatter : function(value, row, index) {
                            	return value?model.formatDate(value):"";
                            }
                    },
                    {
                        field : 'professionalLevel',
                        title : '职称',
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==0){
                        		return "初级";
                        	}
                        	if(value==1){
                        		return "中级";
                        	}
                        	if(value==2){
                        		return "高级";
                        	}
                        }
                    },
                    {
                        field : 'phoneNum',
                        title : '联系电话',
                        align: 'center'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var f = '<a class="btn btn-success btn-sm '+s_check_h+'" href="#" mce_href="#" title="查看" onclick="check(\''
                            + row.id
                            + '\')"><i class="fa"></i>查看</a> ';
                         var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="修改" onclick="edit(\''
                             + row.id
                             + '\')"><i class="fa fa-edit"></i>修改</a> ';
                         var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                             + row.id
                             + '\')"><i class="fa fa-remove"></i>删除</a> ';
                         return f+ e + d;
                        }
                    } ]
            });
}
function reLoad() {
    var opt = {
        query : {
        	searchKey : $('#searchKey').val(),
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}
function check(id) {
	window.location="/biz/veterinarian/findVeterinarianDetail?id="+id+"";
//    layer.open({
//        type : 2,
//        title : '查看',
//        maxmin : true,
//        shadeClose : false, // 点击遮罩关闭层
//        area : [ '800px', '520px' ],
//        content : prefix + '/edit/' + id // iframe的url
//    });
}
 function edit(id) {
	 window.location="/biz/veterinarian/toUpdatePage?id="+id+"";
     /*layer.open({
         type : 2,
         title : '编辑',
         maxmin : true,
         shadeClose : false, // 点击遮罩关闭层
         area : [ '800px', '520px' ],
         content : prefix + '/edit/' + id // iframe的url
     });*/
 }
 function remove(id) {
     layer.confirm('确定要删除选中的记录？', {
         btn : [ '确定', '取消' ]
     }, function() {
         $.ajax({
             url : prefix + "/deleteVeterinarian",
             type : "post",
             data : {
                 'id' : id
             },
             success : function(r) {
                 if (r.code == 0) {
                     layer.msg(r.msg);
                     reLoad();
                 } else {
                     layer.msg(r.msg);
                 }
             }
         });
     })
 }
//
// function addD(type,description) {
//     layer.open({
//         type : 2,
//         title : '增加',
//         maxmin : true,
//         shadeClose : false, // 点击遮罩关闭层
//         area : [ '800px', '520px' ],
//         content : prefix + '/add/'+type+'/'+description // iframe的url
//     });
// }
// function batchRemove() {
//     var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
//     if (rows.length == 0) {
//         layer.msg("请选择要删除的数据");
//         return;
//     }
//     layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
//         btn : [ '确定', '取消' ]
//         // 按钮
//     }, function() {
//         var ids = new Array();
//         // 遍历所有选择的行数据，取每条数据对应的ID
//         $.each(rows, function(i, row) {
//             ids[i] = row['id'];
//         });
//         $.ajax({
//             type : 'POST',
//             data : {
//                 "ids" : ids
//             },
//             url : prefix + '/batchRemove',
//             success : function(r) {
//                 if (r.code == 0) {
//                     layer.msg(r.msg);
//                     reLoad();
//                 } else {
//                     layer.msg(r.msg);
//                 }
//             }
//         });
//     }, function() {});
// }