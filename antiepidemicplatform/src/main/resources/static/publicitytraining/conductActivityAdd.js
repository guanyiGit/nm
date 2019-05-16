let mid = '';
let picIsSuccess = 0;
let videoIsSuccess = 0;
let picSelected = false;
let videoSelected = false;

$(function () {
    validateRule();
    //初始化日期
     laydate.render({
        elem: '#activityTime', //指定元素
        type:"datetime",
        // format:"yyyy-MM-dd HH:mm:ss",
        value:new Date()
    });
})

$.validator.setDefaults({
    submitHandler : function(form) {
        $(form).find(":submit").attr("disabled", true).attr("value", "Submitting...");
        save();
    }
});

function save() {
    let data = $('#saveForm').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/activityInfo/save',
        data: data,
        dataType: "json",
        success: function (r) {
            // console.log(r.id);
            mid = r.id;
            uploadPicAndVideo();
        },
        error: function () {
            // console.log(r.msg);
        }
    })
}

function uploadPicAndVideo() {
    debugger
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
            debugger
            var code = data['response'].code;
            var msg = data['response'].msg;
            if(code == 0) {
                videoIsSuccess = 1;   //图片上传成功
            }
        });
    }
    //TODO 需要做判断
    layer.confirm(operationSuccess, {
        btn : [ determine ]
    }, function() {
        location.href='/biz/activityInfo/conductActivity';
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
            type: 13
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
            type: 13
        };
        return data;
    }
}).on("filebatchselected", function(event, files) {
    videoSelected=true;
});

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#saveForm").validate({
        rules : {
            activityAddress : {
                required : true
            },
            activitySubject : {
                required : true
            },
            amount : {
                required : true,
                min : 1,
                digits:true
            },
            activityContent : {
                required : true
            }
        },
        messages : {
            activityAddress : {
                required : icon + pleaseEnterLocation
            },
            activitySubject : {
                required : icon + pleaseEnterSubject
            },
            amount : {
                required : icon + pleaseInputPublicityPlay,
                digits : icon + onlyInputPosInt
            },
            activityContent : {
                required : icon + pleaseEnterActivityContent
            }
        }
    })
}