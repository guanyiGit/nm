var prefix = "/biz/antibodyDetection";
var url="/findAntibodyDetectionList";
var user=user;
$(function() {
	//初始化组织下拉框(州级和县级)
	initOrgSelect();
	
    load();
    
//    $("#enterKey").keydown(function(e) {
//    	if (e.keyCode == 13) {
//    		reLoad();
//    	}
//    });
});

function initOrgSelect(){
	$.ajax
	({
		url: "/biz/manureAntigen/findOrgList", 
		type: "get",
		dataType: 'json',
		async: false,
		success: function (e) 
		{ 
			// if(e==null){
			// 	layer.msg("初始化下拉组织失败!请联系管理员!");
			// }
			//渲染下拉框
			for (var i = 0; i < e.length; i++) {
				$('#selectOrg')
				.append('<option value="' + e[i].id + '">' + e[i].departName + '</option>');
			}
			$('#selectOrg').trigger('change');
		}, 
		error: function () 
			{ 
				//layer.msg("未知错误,请联系管理员!");
			} 
	});
	
}


//触发下拉框事件
function orgChange(){
	reLoad();
}


function load() {
    // selectLoad();
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+url, // 服务器数据的加载地址
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
                    	orgId1:$("#selectOrg").val()
//                    	,
//                    	searchKey : $('#searchKey').val()
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
                        title: photo5,
                        align:'center',
                        valign:"middle",
                        formatter:function (value,row,index,field) {
                        	var img='';
                        	if(value.length){
                        		img='<img src="'+value[0].thumbnailUrl+'" onclick="javascript:openImgShow(&quot;'+value[0].url+'&quot;);" alt="">';
                        	}
                        	 return  img;
                           
                        }
                    },
                    {
                        field : 'testAmount',
                        title : detectionCount5,
                        width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'qualifiedAmount',
                        title : qualifiedAmount5,
                        width : '100px',
                        align: 'center'
                    },
                    
                    {
                        field : 'qualifiedRate',
                        title : qualifiedRate5,
                        align: 'center'
                    },
                    {
                        field : 'testDate',
                        title : detectionTime5,
                        align: 'center',
                        //获取日期列的值进行转换
                        formatter: function (value, row, index) {
                        	if(value !=null){
                        		 return changeDateFormat(value)
                        	}
                        }
                    },
                    {
                        field : 'orgInfo',
                        title : dectectOrg5,
                        align: 'center',
                        formatter:function (value,row,index,field) {
                        	if(value !=null){
                        		return value.departName;
                        	}else{
                        		return '';
                        	}
                        }
                    },
                    {
                        title : operation5,
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                        	var f = '<a class="btn btn-success btn-sm '+s_check_h+'" href="#" mce_href="#" title="查看" onclick="check(\''
                            + row.id
                            + '\')"><i class="fa"></i>'+detail5+'</a> ';
	                         var e = '<a class="btn btn-info btn-sm '+s_edit_h+'" href="#" mce_href="#" title="修改" onclick="edit(\''
	                             + row.id
	                             + '\')"><i class="fa"></i>'+edit5+'</a> ';
	                         var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
	                             + row.id
	                             + '\')"><i class="fa"></i>'+delete5+'</a> ';
                        	if(row.createdBy==user.userId){
    	                         return f+ e + d;
                        	}
                        	else
                        	{
                        		return f;
                        	}
                        	
                        }
                    } ]
            });
}




function reLoad() {
	 $('#exampleTable').bootstrapTable('destroy');
	    load();
}
function check(id) {
	window.location="/biz/antibodyDetection/getAntibodyDetectionDetail?id="+id+"";
//    layer.open({
//        type : 2,
//        title : '查看',
//        maxmin : true,
//        shadeClose : false, // 点击遮罩关闭层
//        area : [ '800px', '520px' ],
//        content : prefix + '/edit/' + id // iframe的url
//    });
}
//跳转到修改页面
 function edit(id) {
	 window.location="/biz/antibodyDetection/edit?id="+id+"";
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
     layer.confirm(sureDeleteSelectedRecord, {
         btn : [ sure5, cancel5 ]
     }, function() {
         $.ajax({
             url : prefix + "/delete",
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
