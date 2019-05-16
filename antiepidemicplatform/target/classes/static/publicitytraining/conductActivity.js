let prefix = "/biz/activityInfo";
let orgId = "";

$(function () {
    if(type == 5 || type == 6 || type == 9 || type ==10) {
        //县级和州级才会有组织下拉搜索
        initOrgSelect();
    }
    load();
    //监听键盘输入事件
    $('#activitySubject').keydown(function (e) {
        if(e.keyCode == 13) { //enter键
            reLoad();
        }
    })

})


//初始化组织选择框
function initOrgSelect(){
    $('#orgSelect').combotree({
        url: '/biz/goodsInfo/initOrgSelect',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        panelWidth:270,
        // panelHeight:120,
        width:'270px',
        height:'38px',
        onLoadSuccess:function(node,data){
            $("#orgSelect").combotree('setValue',data[0].text);
        },
        onLoadError:function (error) {

        },
        onSelect:function (data) {
            orgId = data.id
            // let opt = {
            //     query : {
            //         goodsName: $('#goodsName').val(),
            //         orgId : data.id
            //     }
            // }
            // $('#exampleTable').bootstrapTable('refresh');
            reLoad();
        }

    });
}

function openImgShow(imgUrl){
    imgShow("#outerdiv", "#innerdiv", "#bigimg", imgUrl);
}

//点击查看大图
function imgShow(outerdiv, innerdiv, bigimg, src){
    // var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    debugger
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
                        activitySubject: $('#activitySubject').val().trim(),
                        orgId : orgId,
                        activityType: 0        //宣传活动
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
                        title : photo,
                        align: 'center',
                        formatter:function (value,row) {
                            var img='<img onclick="javascript:openImgShow(&quot;'+row.url+'&quot;);" src="'+value+'" alt="" />';
                            return  img;
                        }
                    },
                    {
                        field : 'activityTime',
                        title : time,
                        align: 'center',
                        //获取日期列的值进行转换
                        formatter: function (value, row, index) {
                            return changeDateFormat(value);
                        }

                    },
                    {
                        field : 'activityAddress',
                        title : place,
                        align: 'center'
                    },
                    {
                        field : 'activitySubject',
                        title : theTheme,
                        align: 'center'
                    },
                    {
                        field : 'holdOrgName',
                        title : hostUnits,
                        align: 'center'
                    },
                    {
                        field : 'amount',
                        title : issueLiteratureNum,
                        align: 'center'
                    },
                    {
                        visible : false,
                        field : 'sort',
                        title : Sort
                    },
                    {
                        visible : false,
                        field : 'parentId',
                        title : parentNumber
                    },
                    {
                        visible : false,
                        field : 'createBy',
                        title : theCreator
                    },
                    {
                        visible : false,
                        field : 'createDate',
                        title : creatorTime
                    },
                    {
                        visible : false,
                        field : 'updateBy',
                        title : updateThe
                    },
                    {
                        visible : false,
                        field : 'updateDate',
                        title : updateTime
                    },
                    {
                        visible : false,
                        field : 'remarks',
                        title : remarkInfo
                    },
                    {
                        visible : false,
                        field : 'delFlag',
                        title : deleteFlag
                    },
                    {
                        title : operation,
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var f = '<a class="btn btn-success btn-sm" href = "'+prefix+'/detail/'+row.id+'" target="menuFrame" title="查看">'+checkk+'</a>';
                            var e = '<a class="btn btn-info btn-sm" href = "'+prefix+'/edit/'+row.id+'" target="menuFrame" title="修改">'+updatee+'</a>';
                            var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')">'+deletee+'</a> ';
                            if(row.userId == row.operatorId) {      //自己创建的记录才有“修改”“删除”权限
                                return f+ ' '+ e+ ' ' + d;
                            }else {
                                return f;
                            }
                        }
                    } ]
            });
}


// function retOrgId() {
//     debugger
//     if(type == 5 || type == 6){
//         //县级、州级管理员
//         return $("#orgSelect").combotree("getValue");
//     }else {
//         return "";  //乡级管理员不根据orgId查
//     }
//
// }

//点击搜索
function reLoad() {
    // let opt = {
    //     query : {
    //         orgId : $('[name=deptId]').val(),
    //         activitySubject : $('#activitySubject').val()
    //     }
    // }
    // $('#exampleTable').bootstrapTable('refresh',opt);
    // $('#exampleTable').bootstrapTable('refresh');
    $('#exampleTable').bootstrapTable('destroy');
    load();
}

function remove(id) {
    layer.confirm(sureDeleteSelectedRecord, {
        btn : [determine, cancel ]
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
                    debugger
                    reLoad();
                } else {
                    layer.msg(operationFailure);
                }
            }
        });
    })
}