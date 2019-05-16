
var prefix = "/biz/path";
var searchKey=null;
var selectedDate=null;
$(function() {
	 initSelectDate();
    load();
});

function initSelectDate(){
	laydate.render({
        elem: '#selectedDate', //指定元素
        //type:"datetime",
        format:"yyyy-MM-dd"
        
    });
	
}

function load() {
    // selectLoad();
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/findDogList", // 服务器数据的加载地址
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
                    	searchKey: searchKey,
                    	selectedDate: selectedDate
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
                		field: 'dogSmallUrl',
                        title: photo5,
                        align:'center',
                        valign:"middle",
                        formatter:function (value,row,index,field) {
                        	var img='<img onclick="javascript:openImgShow(&quot;'+row.dogBigUrl+'&quot;);" src="'+value+'" alt="" />';
                            return  img;
                        }
                    },
                    {
                        field : 'traceId',
                        title : traceID5,
                        width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'fenceName',
                        title : elecrail5,
                        width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'dogName',
                        title : dogName5,
                        width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'breed',
                        title : dogBreed5,
                        width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'sex',
                        title : sex5,
                        align: 'center',
                        formatter : function(value, row, index) {
                        	if(value==0){
                        		return male5;
                        	}else{
                        		return female5;
                        	}
                        }
                    },
                    {
                        field : 'age',
                        title : dogAge5,
                        align: 'center'
                    },
                    {
                        field : 'ownerName',
                        title : ownerName5,
                        align: 'center'
                    },
                    {
                        field : 'ownerPhone',
                        title : phone5,
                        align: 'center'
                    },
                    {
                        field : 'cardNum',
                        title : ID5,
                        align: 'center'
                    },
                    {
                        field : 'townList',
                        title : town5,
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
                        title : operation5,
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                        	 var f = '<a class="btn btn-success btn-sm '+s_check_h+'" href="#" mce_href="#" title="查看轨迹" onclick="check(\''
                             + [row.id,row.traceId]
                             + '\')"><i class="fa"></i>'+getPath+'</a> ';
                          return f;
                        }
                    } ]
            });
}
function reLoad() {
	 searchKey=$('#searchKey').val().trim();
	 selectedDate=$("#selectedDate").val();
	if(searchKey==null || searchKey==""){
		layer.msg('请输入查询条件!');
		return false;
	}
	if(selectedDate==null ||selectedDate==""){
		layer.msg('请选择日期!');
		return false;
	}
    $('#exampleTable').bootstrapTable('destroy');
    load();
}
function check(row) {
	var str=row.split(",");
	var traceId=str[1];
	var id=str[0];
	if(traceId==null || traceId==""){
		
		layer.alert('溯源ID不能为空!');
		return false;
		
	}
	else{
		window.location="/biz/path/getDogPath?traceId="+traceId+"&&selectedDate="+selectedDate+"&&id="+id;
	}
}