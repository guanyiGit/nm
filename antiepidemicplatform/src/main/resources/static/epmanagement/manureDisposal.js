let prefix = "/biz/manureDisposal"
let operatorId = 0
let orgId = 0
$(function() {
    //keydowm事件
    $("#so").keydown(function(e) {
        debugger;
        if (e.keyCode == 13) {
            reLoad();
        }
    });

    if(type == 5 || type == 6 || type == 9 || type == 10) {
        //县级、州级角色
        initOrgSelect();
    }
    if(type == 3 || type == 8) {
        //乡级
        initPro();
    }
    load();

    // $(".pimg").click(function(){
    //     var _this = $(this);//将当前的pimg元素作为_this传入函数
    //     imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    // });

});

function openImgShow(imgUrl){
    imgShow("#outerdiv", "#innerdiv", "#bigimg", imgUrl);
}

//点击查看大图
function imgShow(outerdiv, innerdiv, bigimg, src){
    // var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性
    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
        if(realHeight>windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }

        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg

    });

    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
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
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        traceId: $.trim($('#traceId').val()),
                        orgId : retOrgId(),
                        operator : retOperator()
                        // name:$('#searchName').val(),
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
                        field : 'thumbnailUrl',
                        title : photo5,
                        align: 'center',
                        formatter:function (value,row) {
                            var img='<img onclick="javascript:openImgShow(&quot;'+row.url+'&quot;);" src="'+value+'" alt="" />';
                            return  img;
                        }
                    },
                    {
                        field : 'traceId',
                        title : traceID5,
                        // width : '100px',
                        align: 'center'
                    },
                    // {
                    //     field : 'deviceNo',
                    //     title : '设备编号',
                    //     // width : '100px',
                    //     align: 'center'
                    // },
                    {
                        field : 'dogName',
                        title : dogName5,
                        // width : '100px',
                        align: 'center'
                    },
                    // {
                    //     field : 'breed',
                    //     title : '犬种',
                    //     // width : '100px',
                    //     align: 'center'
                    // },
                    {
                        field : 'ownerName',
                        title : ownerName5,
                        // width : '100px',
                        align: 'center'
                    },{
                        field : 'phoneNum',
                        title : phone5,
                        // width : '100px',
                        align: 'center'
                    },
                    // {
                    //     field : 'cardNum',
                    //     title : '身份证号码',
                    //     // width : '100px',
                    //     align: 'center'
                    // },
                    {
                        field : 'areaName',
                        title : town5,
                        // width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'detailAddress',
                        title : address5,
                        // width : '100px',
                        align: 'center'
                    },
                    {
                        field : 'mode',
                        title : handleWay5,
                        // width : '100px',
                        align: 'center'
                    },
                    // {
                    //     visible : 'false',
                    //     field : 'methodDes',
                    //     title : '方法说明',
                    //     align: 'center',
                    //     formatter: function (value, row, index) {
                    //         return handleVal(value)
                    //     }
                    //
                    // },
                    {
                        field : 'dealTime',
                        title : handleDate5,
                        align: 'center',
                        //获取日期列的值进行转换
                        formatter: function (value, row, index) {
                            return changeDateFormat(value)
                        }

                    },
                    {
                        field : 'name',
                        title : handlePerson5,
                        align: 'center'
                    },
                    {
                        title : operation5,
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var f = '<a class="btn btn-success btn-sm ' + s_check_h + '" href = "/biz/manureDisposal/detail/'+row.id+'" target="menuFrame" title="查看"><i class="fa"></i>'+detail5+'</a>';
                            var e = '<a class="btn btn-info btn-sm '+s_edit_h+'" href = "'+prefix+'/edit/'+row.id+'" target="menuFrame" title="修改">'+edit5+'</a>';
                            var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')">'+delete5 +'</a> ';
                            return f+ ' '+ e+ ' ' + d;
                        }
                    } ]
            });
}

function retOrgId() {
    if(orgId == 0) {
        orgId = null
    }
    return orgId
}

function retOperator() {
    if(operatorId == 0) {
        operatorId = null
    }
    return operatorId
}

function handleVal(val) {
    let length = val.length;
    if(length > 32) {
        return val.slice(0,32)+"...";
    }else {
        return val;
    }
}

function reLoad() {
    // $('#exampleTable').bootstrapTable('refresh');
    // load();
    $('#exampleTable').bootstrapTable('destroy');
    load();
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


//初始化组织选择框
function initOrgSelect(){
    $('#orgSelect').combotree({
        url: '/biz/orgInfo/initOrgSelect2',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        panelWidth: 260,
        width: '260',
        height: '38px',
        onSelect:function (data) {
            //刷新列表
            // data.id 组织id
            // reLoad2(data.id,null);
            orgId = data.id,
            reLoad()
        },
        onLoadSuccess:function(node,data){
            $("#orgSelect").combotree('setValue',data[0].text);
        }
    });
}

//初始化防疫员选择框
function initPro(){
    $('#proSelect').combotree({
        url: '/biz/dogInfo/findProtector2',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        panelWidth: 220,
        width: '220px',
        height: '38px',
            onSelect:function (data) {
            //刷新列表
            //返回参数说明：
            // data.id 防疫员id
            //data.userId 用户id
            //data.text 防疫员姓名
            operatorId = data.userId,
            reLoad()
            // reLoad3(data.userId);
        }
    });
}


