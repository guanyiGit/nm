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
var prefix = "/biz/protector";
var orgId=1;
$(function() {
	//获取登录用户信息,待完善
	//var user=
    load();
    console.log(user.userId);
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
                url : prefix+"/findProtectorList", // 服务器数据的加载地址
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
                    	searchKey : $('#searchKey').val().trim()
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
                	{
                		field: 'imageList',
                        title: '照片',
                        align:'center',
                        valign:"middle",
                        formatter:function (value,row,index,field) {
                        	var img='';
                        	if(value.length){
                        		img='<img onclick="javascript:openImgShow(&quot;'+value[0].url+'&quot;);" src="'+value[0].thumbnailUrl+'" alt="">';
                        	}
                        	 return  img;
                        }
                    },
                    {
                        field : 'name',
                        title : '姓名',
                        align: 'center'
                    },
                    {
                        field : 'townList',
                        title : '所属乡镇',
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==null){
                        		return "";
                        	}else{
                        		var b=value[2].name;
                            	var c= value[1].name;
                            	var d= value[0].name;
                        		return b+c+d;
                        	}
                        }
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
                         var e = '<a class="btn btn-info btn-sm '+s_edit_h+'" href="#" mce_href="#" title="修改" onclick="edit(\''
                             + row.id
                             + '\')"><i class="fa"></i>修改</a> ';
                         var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                             + row.id
                             + '\')"><i class="fa"></i>删除</a> ';
                         if(row.createBy==user.userId){
	                         return f+ e + d;
                    	}
                    	else
                    	{
                    		console.log(row.createdBy);
                    		return f;
                    	}
                        }
                    } ]
            });
}
function reLoad() {
    var opt = {
        query : {
        	searchKey : $('#searchKey').val().trim(),
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}
function check(id) {
	 window.location="/biz/protector/findProtectorDetail?id="+id+"";
   /* layer.open({
        type : 2,
        title : '查看',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });*/
}
	//修改
 function edit(id) {
	 window.location="/biz/protector/toUpdatePage?id="+id+"";
//     layer.open({
//         type : 2,
//         title : '编辑',
//         maxmin : true,
//         shadeClose : false, // 点击遮罩关闭层
//         area : [ '800px', '520px' ],
//         content : prefix + '/edit/' + id // iframe的url
//     });
 }
 function remove(id) {
     layer.confirm('确定要删除选中的记录？', {
         btn : [ '确定', '取消' ]
     }, function() {
         $.ajax({
             url : prefix + "/deleteProtector",
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
 
 
