let mid = '';
let picIsSuccess = 0;
let videoIsSuccess = 0;
let picSelected = false;
let videoSelected = false;
let deviceNoUsed = 1;   //溯源ID默认不合法

$(function () {

    //初始化处理方法下拉列表
    new MySelct({
        id:"processMode",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        params: {
            type: "process_mode"
        }
    });

    //设置当前日期为处理时间
    var today=new Date();
    var submitTime=today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    $("#dealTime").attr('value',submitTime);
})

//检查该溯源ID是否存在
function checkDeviceNo() {
    deviceNoUsed = 1;
    let traceId = $('#traceId').val().trim();
    debugger
    if(traceId == '') {
        layer.msg(pleaseInputCorrectTraceID);
        return false;
    }
    $.ajax( {
        type : "get",
        url :"/biz/dogCancel/findDogByTraceId",
        data : {
            traceId: traceId
        },
        success : function(res) {
            debugger
            if(res.status == 200) {
                deviceNoUsed = 0;   //该溯源Id合法
            }else {
                layer.msg(dogNotExit);
            }
        }
    });
}
// 保存
function saveManureDisposal() {
    if(deviceNoUsed == 1) {
        layer.msg(pleaseInputCorrectTraceID);
        return false;
    }
    let data = $('#saveManureDisposal').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/manureDisposal/save',
        data: data,
        dataType: "json",
        success: function (r) {
            mid = r.id;
            // console.log(r.code);
            uploadPicAndVideo();
        },
        error: function () {
        }
    })
}



function uploadPicAndVideo() {
    // $("#file-1").fileinput("upload").on("filebatchuploadsuccess",function (event,data) {
    //     var code = data['response'].code;
    //     var msg = data['response'].msg;
    //     if(code == 0) {
    //         picIsSuccess = 1;   //图片上传成功
    //     }
    //     if(picIsSuccess == 1 && videoIsSuccess == 1) {
    //         //图片视频上传成功
    //         // layer.alert("上传成功");
    //         layer.confirm("操作成功", {
    //             btn : [ '确定' ]
    //         }, function() {
    //             location.href='/biz/manureDisposal';
    //         })
    //     }
    // });
    // $("#file-2").fileinput("upload").on("filebatchuploadsuccess",function (event,data) {
    //     debugger
    //     var code = data['response'].code;
    //     var msg = data['response'].msg;
    //     if(code == 0) {
    //         videoIsSuccess = 1;   //图片上传成功
    //     }
    //     debugger
    //     if(picIsSuccess == 1 && videoIsSuccess == 1) {
    //         //图片视频上传成功
    //         // layer.alert("上传成功");
    //         layer.confirm("操作成功", {
    //             btn : [ '确定' ]
    //         }, function() {
    //             location.href='/biz/manureDisposal';
    //         })
    //     }
    // });

    if(picSelected) {
        //照片选择了
        $("#file-1").fileinput("upload").on("filebatchuploadsuccess",function (event,data) {
            var code = data['response'].code;
            var msg = data['response'].msg;
            if(code == 0) {
                picIsSuccess = 1;   //图片上传成功
            }
        });
    }
    if(videoSelected) {
        //视频选择了
        $("#file-2").fileinput("upload").on("filebatchuploadsuccess",function (event,data) {
            var code = data['response'].code;
            var msg = data['response'].msg;
            if(code == 0) {
                videoIsSuccess = 1;   //图片上传成功
            }
        });
    }
    //TODO 需要做判断
    layer.confirm(operationSuccess, {
        btn : [ sure5 ]
    }, function() {
        location.href='/biz/manureDisposal';
    })

}

$("#file-1").fileinput({
    enctype: 'multipart/form-data',
    theme: 'fa',
    uploadUrl: '/biz/manureDisposal/uploadPic', // you must set a valid URL here else you will get an error
    allowedFileExtensions: ['jpg', 'jpeg', 'gif','bmp'],
    overwriteInitial: false,
    maxFileSize: 0,
    // maxFilesNum: 10,
    // allowedFileTypes:['image'],
    // previewFileType:['video', 'flash'],
    showUpload:false,
    // showCancel:true,
    uploadAsync:false,       //设置为同步
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    layoutTemplates:{
        // actionDelete:'',
        actionUpload:'',
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id: mid,
            type: 6
        };
        return data;
    }
    }).on("filebatchselected", function(event, files) {
        picSelected=true;
    });

$("#file-2").fileinput({
    enctype: 'multipart/form-data',
    theme: 'fa',
    uploadUrl: '/biz/manureDisposal/uploadVideo', // you must set a valid URL here else you will get an error
    // allowedFileExtensions: ['jpg', 'png', 'gif','video','flash'],
    overwriteInitial: false,
    maxFileSize: 0,
    // maxFilesNum: 10,
    allowedFileTypes:['video','flash'],
    // previewFileType:['video', 'flash'],
    showUpload:false,
    showCancel:true,
    uploadAsync:false,       //设置为同步
    layoutTemplates:{
        // actionDelete:'',
        actionUpload:'',
    },
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id: mid,
            type: 6
        };
        return data;
    }
    }).on("filebatchselected", function(event, files) {
        videoSelected=true;
    });