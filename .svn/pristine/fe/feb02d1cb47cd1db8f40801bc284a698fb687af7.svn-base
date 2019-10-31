
var prefix = "/biz/deviceInfo"
$(function() {
	load();
    selectByDeviceNo();
    // selectByIsDistribution();


    $('#deviceStatus').combobox({
        valueField:'id',
        textField:'text',
        panelHeight:100,
		width:220,
		height:38,
        data:[{
            id:"",
            text:stateT,
            selected:true
        },{
            id:"1",
            text:state1T,
        },{
            id:"0",
            text:state0T,
        },{
            id:"3",
            text:state3T
        }],
        onSelect:function (value) {
            var opt = {
                query : {
                    // isDistribution:$("#isDistribution").val(),
                    status:value.id
                }
            };
            $('#deviceInfoTable').bootstrapTable('refresh',opt);
        }
    });

    // if(roleType != 2 ){
    //     initJLSelect();
    // }
});

function load() {
    // selectLoad();
    $('#deviceInfoTable')
        .bootstrapTable(
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
						// deviceNo:$("#deviceNo").val(),
                        // isDistribution:$("#isDistribution").val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求

                /**
				 *  id, deviceNo, brand, model, dateOfPro, activateTime, freezeTime, receiveTime, departName
                 */
                columns :[[		{
									field : 'deviceNo',
									title : neckLet5,
									align: "center"
								},{
									field: 'brand',
									title: brand5,
									align: "center"

								},{
									field : 'model',
									title : model5,
									align: "center"
								},
																{
									field : 'dateOfPro',
									title : productDate5,
									align: "center",
									formatter:function (value, row, index) {
										return changeDateFormat(value);
									}
								},
								// {
								// 	field : 'receiveTime',
								// 	title : '领取时间',
								// 	align: "center",
								// 	formatter:function (value, row, index) {
								// 		if(value==null){
								// 			return  "";
								// 		}
								// 		return changeDateFormat(value);
								// 	}
								// },{
								// 	field : 'departName',
								// 	title : '领取部门',
								// 	align: "center"
								// },
								{
									field : 'status',
									title : deviceStatus5,
									align: "center",
									formatter:function (value, row, index) {
										var str = "";
										if(value==0){
											str = "未激活";
										}else if(value==1){
                                            str = "启用";
										}else if(value==2){
                                            str = "注销";
                                        }
                                        return str;
                       				 }
								},{
									field : 'activateTime',
									title : effectiveDate5,
									align: "center",
									formatter:function (value, row, index) {
                                        if(value==null){
                                            return  "";
                                        }
										return changeDateFormat(value);
									}
								},{
									field : 'deadline',
									title : failureDate5,
									align: "center",
									formatter:function (value, row, index) {
                                        if(value==null){
                                            return  "";
                                        }
										return changeDateFormat(value);
									}
								}
								]]
            });
}

function reLoad() {
	$('#deviceInfoTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
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
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
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
			ids[i] = row['id'];
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
	}, function() {

	});
}

//根据项圈编号查询
function selectByDeviceNo() {

    $("#search").click(function() {
    	debugger
        var opt = {
            query : {
                // isDistribution:$("#isDistribution").val(),
                status:$("#deviceStatus").val(),
                deviceNo:$("#deviceNo").val()
            }
        }
        $('#deviceInfoTable').bootstrapTable('refresh',opt);
    });
}


//初始化级联下拉框(分配未分配 和状态的级联)
// function initJLSelect(){
// 		$('#isDistribution').combobox({
// 			valueField:'id',
// 			textField:'text',
// 			panelHeight:50,
// 			data:[{
// 				id:"1",
// 				text:'已分配',
// 				selected:true
// 			},{
// 				id:"0",
// 				text:'未分配'
// 				// selected:true
// 			}],
// 			onSelect:function (value) {
// 				if(value.id==0){
// 					var dt = [{
// 						id:"0",
// 						text:'未激活',
// 						selected:true
// 					}];
//
// 					$('#deviceStatus').combobox('loadData',dt);
// 				}else if(value.id==1){
// 					var dd = [{
// 						id:"",
// 						text:'全部状态',
// 						selected:true
// 					},{
// 						id:"0",
// 						text:'未激活',
// 					},{
// 						id:"1",
// 						text:'已激活',
// 					},{
// 						id:"3",
// 						text:'已冻结'
// 					}];
// 					$('#deviceStatus').combobox('loadData',dd);
// 				}
//
// 				var opt = {
// 					query : {
// 						isDistribution:value.id
// 					}
// 				}
// 				$('#deviceInfoTable').bootstrapTable('refresh',opt);
// 			}
// 		});
// }


