var model={
    dogId:null,
    type:null,
    deFendId:null,
    picIsSuccess : 0,
    videoIsSuccess : 0,
    picSelected :false,
    videoSelected :false,

}

$(function(){
    model.type=type;

   //初始化药品
    InitDrog();

   $("#ajaxBtn").click(function () {
       addDefend();
   });

   $("#cancel").click(function () {
       if(model.type==0){
           window.location="/biz/antiepidemic/springDefendInfo";
       }else if(model.type==1){
           window.location="/biz/antiepidemic/autumnDefendInfo";
       }else {
           window.location="/biz/antiepidemic/monthDefendInfo";
       }
   });

});


function InitDrog() {
    $.ajax( {
        type : "get",
        url :"/biz/antiepidemic/getDrug",
        data : {type:0},
        success : function(res) {
            if(res.status==200){
                var data=res.data;
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].id);
                    option.text(data[i].name);
                    $("#selectDrug").append(option);
                }
            }
        }
    });
}


function getDogId(e) {
    if($.trim(e.value)==''){
        return false;
    }
    $.ajax( {
        type : "get",
        url :"/biz/dogCancel/findDogByTraceId",
        data : {traceId:$.trim(e.value)},
        success : function(res) {
            if(res.status==200){
                model.dogId=res.data.id;
            }else{
                layer.msg(dogNotExit);
            }
        }
    });
}

function addDefend() {
    if(model.dogId==null){
        layer.msg(pleaseInputCorrectTraceID);
        return false;
    }
    if(model.picSelected==false){
        layer.msg(pleaseSelectPhoto);
        return false;
    }
    var params = $("#myFrom4").serialize();
    var data = $('#myFrom4').serialize()
                +'&'+$.param({'dogId': model.dogId})
                +'&'+$.param({'type': model.type});
    $.ajax( {
        type : "POST",
        url :"/biz/antiepidemic/save",
        data : data,
        async: false,
        success : function(res) {
            if(res.code==0){
                model.deFendId=res.id;
                uploadPicAndVideo();
            }
        }
    });
}




function uploadPicAndVideo() {
    if (model.picSelected) {
        //照片选择了
        $("#file-1").fileinput("upload").on("filebatchuploadsuccess", function (event, data) {
            var code = data['response'].code;
            var msg = data['response'].msg;
            if (code == 0) {
                model.picIsSuccess = 1;   //图片上传成功
            }
            skip();
        });
    }
    if (model.videoSelected) {
        //视频选择了
        $("#file-2").fileinput("upload").on("filebatchuploadsuccess", function (event, data) {
            var code = data['response'].code;
            var msg = data['response'].msg;
            if (code == 0) {
                model.videoIsSuccess = 1;   //图片上传成功
            }
            skip();
        });
    }


}

function skip(){
    if(model.videoSelected){
        if(model.picIsSuccess == 1 && model.videoIsSuccess == 1){
            layer.confirm(operationSuccess, {
                btn : [ sure5 ]
            }, function() {
                if (model.type == 0) {
                    window.location = "/biz/antiepidemic/springDefendInfo";
                } else if (model.type == 1) {
                    window.location = "/biz/antiepidemic/autumnDefendInfo";
                } else {
                    window.location = "/biz/antiepidemic/monthDefendInfo";
                }
            })
        }
    }else{
        if(model.picIsSuccess == 1){
            layer.confirm(operationSuccess, {
                btn : [ sure5 ]
            }, function() {
                if (model.type == 0) {
                    window.location = "/biz/antiepidemic/springDefendInfo";
                } else if (model.type == 1) {
                    window.location = "/biz/antiepidemic/autumnDefendInfo";
                } else {
                    window.location = "/biz/antiepidemic/monthDefendInfo";
                }
            })
        }
    }
}




    $("#file-1").fileinput({
        enctype: 'multipart/form-data',
        theme: 'fa',
        uploadUrl: '/biz/manureDisposal/uploadPic', // you must set a valid URL here else you will get an error
        allowedFileExtensions: ["jpg", "gif", "bmp"],
        overwriteInitial: false,
        maxFileSize: 0,
        // maxFilesNum: 10,
        // allowedFileTypes:['jpg', 'png', 'gif','video','flash'],
        // previewFileType:['video', 'flash'],
        showUpload: false,
       // showCancel: true,
        uploadAsync: false,
        //showRemove:false,
        // maxFileCount:1,//允许同时上传的最大文件数
        // autoReplace:true,
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        },
        layoutTemplates:{
            actionDelete:'',
            actionUpload:'',
        },
        uploadExtraData: function () {//向后台传递参数
            var data = {
                id: model.deFendId,
                type: 3
            };
            return data;
        },
    }).on("filebatchselected", function(event, files) {
        model.picSelected=true;
    }).on('filecleared', function(event, key) {
        model.picSelected=false;
    }).on('filebatchuploaderror', function(event, data, msg) {
        layer.msg(failUpPhoto);
    });


    $("#file-2").fileinput({
        enctype: 'multipart/form-data',
        theme: 'fa',
        uploadUrl: '/biz/manureDisposal/uploadVideo', // you must set a valid URL here else you will get an error
        allowedFileExtensions: ['AVI', 'wma', 'rmvb', 'rm', 'flash', 'mp4', 'mid', '3GP'],
        overwriteInitial: false,
        showUpload: false,
        showCancel: true,
        uploadAsync: false,
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        },
        layoutTemplates:{
            actionDelete:'',
            actionUpload:'',
        },
        uploadExtraData: function () {//向后台传递参数
            var data = {
                id: model.deFendId,
                type: 3
            };
            return data;
        }
    }).on("filebatchselected", function(event, files) {
        model.videoSelected=true;
    }).on('filecleared', function(event, key) {
        model.videoSelected=false;
    }).on('filebatchuploaderror', function(event, data, msg) {
        layer.msg(failUpVideo);
    });


