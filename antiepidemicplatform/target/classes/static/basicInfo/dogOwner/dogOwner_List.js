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
    orgId:null,
    pro:null
}
var prefix = "/biz/dogOwner"
$(function() {
	//获取登录用户信息,待完善
	//var user=
    load();
    
    $("#enterKey").keydown(function(e) {
    	if (e.keyCode == 13) {
    		reLoad();
    	}
    });
    initOrgSelect();
    initPro();
});



//初始化组织选择框
function initOrgSelect(){
    $('#orgSelect').combotree({
        url: '/biz/orgInfo/initOrgSelect2',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        width:'260px',
        height:'38px',
        panelWidth:260,
        onSelect:function (data) {
        //刷新列表
            // data.id 组织id
            //reLoad2(data.id,null);
            model.orgId=data.id;
            reLoad();
            
        },
        onLoadSuccess:function(node,data){
            $("#orgSelect").combotree('setValue',data[0].text);
        }
    });
}


function initPro(){
    $('#proSelect').combotree({
        url: '/biz/dogInfo/findProtector2',
        method:'get',
        required: false,
        valueField:'userId',
        textField:'text',
        width:'220px',
        height:'38px',
        panelWidth:220,
        onSelect:function (data) {
        //刷新列表
            //返回参数说明：
            // data.id 防疫员id
            //data.userId 用户id
            //data.text 防疫员姓名
            	 model.pro=data.userId;
            	 reLoad();
           
        },
        onLoadSuccess:function(node,data){
            $("#proSelect").combotree('setValue',data[0].text);
        }
    });
}


function load() {
    // selectLoad();
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/findDogOwnerList", // 服务器数据的加载地址
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
                    	searchKey: $("#searchKey").val().trim(),
                    	orgId:model.orgId,
                    	pro:model.pro
//                    	pro:$("#proSelect").val()
//                    	orgId:$("#orgSelect").combotree('getSelected').id,
//                    	pro:$("#proSelect").combotree('getSelected').userId
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
                        title: photo,
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
                        field : 'name',
                        title : nam,
                        width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'sex',
                        title : gender,
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==0){
                        		return male;
                        	}else{
                        		return female;
                        	}
                        }
                    },
                    {
                        field : 'birthDay',
                        title : birthday,
                        align: 'center',
                        formatter : function(value, row, index) {
                                return value?model.formatDate(value):"";
                        }
                    },
                    {
                        field : 'nation',
                        title : national,
                        align: 'center'
                    },
                    {
                        field : 'degreeOfEducation',
                        title : levelEducation,
                        align: 'center',
                        formatter : function(value, row, index) {
                            if (value=="小学") {
                                return primarySchool
                            }else if (value=="初中") {
                                return juHiSchool
                            }else if (value=="高中") {
                                return highSchool
                            }else if (value=="大专") {
                                return college
                            }else if (value=="本科及以上") {
                                return baDeAbove
                            }else {
                                return ""
                            }
                            return value?model.formatDate(value):"";
                        }
                    },
                    {
                        field : 'phoneNum',
                        title : phone,
                        align: 'center'
                    },
                    {
                        field : 'townList',
                        title : belTownship,
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
                        field : 'tCommittee',
                        title : villageCommit,
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==null){
                        		return "";
                        	}else{
                        		return value.name;
                        	}
                        }
                    },
                    {
                        field : 'grassLandArea',
                        title : pastureArea,
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==null){
                        		return "";
                        	}else{
                        		return value+mu;
                        	}
                        }
                    },
                    {
                        title : operation,
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                        	 var f = '<a class="btn btn-success btn-sm '+s_check_h+'" href="#" mce_href="#" title="查看" onclick="check(\''
                             + row.id
                             + '\')"><i class="fa"></i>'+checkk+'</a> ';
                          var e = '<a class="btn btn-info btn-sm '+s_edit_h+'" href="#" mce_href="#" title="修改" onclick="edit(\''
                              + row.id
                              + '\')"><i class="fa"></i>'+updatee+'</a> ';
                          var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                              + row.id
                              + '\')"><i class="fa"></i>'+deletee+'</a> ';
                          return f+ e + d;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('destroy');
    load();
}
function check(id) {
	window.location="/biz/dogOwner/findDogOwnerDetail?id="+id+"";
   /* layer.open({
        type : 2,
        title : '查看',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });*/
}
 function edit(id) {
	 window.location="/biz/dogOwner/toUpadatePage?id="+id+"";
    /* layer.open({
         type : 2,
         title : '编辑',
         maxmin : true,
         shadeClose : false, // 点击遮罩关闭层
         area : [ '800px', '520px' ],
         content : prefix + '/edit/' + id // iframe的url
     });*/
 }
 function remove(id) {
     layer.confirm(sureDeleteSelectedRecord, {
         btn : [ determine, cancel ]
     }, function() {
         $.ajax({
             url : prefix + "/deleteDogOwner",
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
