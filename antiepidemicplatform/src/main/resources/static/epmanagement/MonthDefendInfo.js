var model={
    orgId:null,
    pro:null
}
var prefix = "/biz/antiepidemic";
$(function() {
    laydate.render({
        elem: '#startTime', //指定元素
        type:"month",
    });
    laydate.render({
        elem: '#endTime', //指定元素
        type:"month",
    });

    $("#btn").click(function () {
        if($('#startTime').val()!='' && $('#endtime').val()!='' ){
            var starttime = $('#startTime').val();
            var endtime = $('#endTime').val();
            var start = new Date(starttime.replace("-", "/").replace("-", "/"));
            var end = new Date(endtime.replace("-", "/").replace("-", "/"));
            if(end<start){
                layer.msg(startCannotEnd)
                return false;
            }
        }
        reLoad();
    });
    load();
    //模糊查询
    $("#ss").click(function(){
        reLoad();
    });
    //keydowm事件
    $("#so").keydown(function(e) {
        if (e.keyCode == 13) {
            reLoad();
        }
    });

    initOrgSelect();
    initPro();
})

function change(e) {
    if(e==0){
        e=null;
    }
    return e;
}

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
        valueField:'id',
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
            model.pro=data.id;
            reLoad();
        }
    });
}

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix+"/pagelist", // 服务器数据的加载地址
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
                        // name:$('#searchName').val(),
                        type : 2 ,       //type=2 查看月月防信息
                        orgId:model.orgId,
                        protector:change(model.pro),
                        string : $.trim($('#inputStr').val()),
                        startTime:$('#startTime').val(),
                        endTime:$('#endTime').val()
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
                        field : 'smallUrl',
                        title :photo5,
                        align: 'center',
                        formatter:function (value,row) {
                            var img='<img onclick="javascript:openImgShow(&quot;'+row.bigUrl+'&quot;);" src="'+value+'" alt="" />';
                            return  img;
                        }
                    },
                    {
                        field : 'drugName',
                        title : antiepidemicDrug5,
                        align: 'center'
                    },
                    {
                        field : 'date',
                        title : antiepidemicTime5,
                        align: 'center',
                        formatter: function (value, row, index) {
                            return timeStamp2String(value)
                        }
                    },
                    {
                        field : 'period',
                        title : antiepidemicPeriod5,
                        align: 'center'
                    },
                    {
                        field : 'name',
                        title : protector5,
                        align: 'center'
                    },
                    {
                        field : 'traceId',
                        title : traceID5,
                        align: 'center'
                    }, {
                        field : 'dogName',
                        title : dogName5,
                        align: 'center'
                    },{
                        field : 'ownerName',
                        title : ownerName5,
                        align: 'center'
                    },{
                        field : 'phoneNum',
                        title : phone5,
                        align: 'center'
                    },{
                        field : 'town',
                        title : town5,
                        align: 'center'
                    },{
                        field : 'town',
                        title : address5,
                        align: 'center',
                        formatter:function (value,row) {
                            console.log(row);
                            return  row.pcName+row.no;
                        }
                    }, {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var f = '<a class="btn btn-success btn-sm '+check1+'" href="#" mce_href="#" title="查看" onclick="check(\''
                                + row.id
                                + '\')"><i class="fa"></i>'+detail5+'</a> ';
                            var e = '<a class="btn btn-info btn-sm '+edit1+'" href="#" mce_href="#" title="修改" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa"></i>'+edit5+'</a> ';
                            var d = '<a class="btn btn-warning btn-sm '+remove1+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa"></i>'+delete5 +'</a> ';
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
    window.location="/biz/antiepidemic/edit/2?id="+id+"";
}
function edit(id) {
    window.sessionStorage.setItem("defendId",id);
    window.location="/biz/antiepidemic/editDefend/2";
}

function remove(id) {
    layer.confirm(sureDeleteSelectedRecord, {
        btn : [ sure5, cancel5 ]
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
