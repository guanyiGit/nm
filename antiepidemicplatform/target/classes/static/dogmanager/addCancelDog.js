var model={
    cancelId:null,
    blag:false,
    isExist:false
};
$(function () {
    getStatis();

    $("#ajaxBtn").click(function () {
        addCancelGog();

        uploadPic();
    });
});


//初始化
function getStatis(){
    $.ajax( {
        type : "get",
        url :"/biz/dogCancel/findTSysDict",
        success : function(res) {
            var dogCancelReasonList=res.data.dogCancelReason;
            var deviceDealList=res.data.deviceDeal;
            if(deviceDealList!=null){
                for (var i = 0; i < deviceDealList.length; i++) {
                    var option = $("<option ></option>");
                    option.val(deviceDealList[i].value);
                    option.text(deviceDealList[i].name);
                    $("#selectDeviceDeal").append(option);
                }
            }
            if(dogCancelReasonList!=null){
                for (var i = 0; i < dogCancelReasonList.length; i++) {
                    var option = $("<option ></option>");
                    option.val(dogCancelReasonList[i].value);
                    option.text(dogCancelReasonList[i].name);
                    $("#selectCancelReason").append(option);
                }
            }

        }
    });
}

function addCancelGog(){
   if(model.blag==false){
        layer.msg(pleaseSelectPhoto);
        return false;
    }
    if(model.isExist==false){
        layer.msg(dogNotExit);
        return false;
    }
    var params = $("#myFrom5").serialize();
    $.ajax( {
        type : "POST",
        url :"/biz/dogCancel/save",
        data : params,
        async: false,
        success : function(res) {
            if(res.status==200){
                model.cancelId=res.data;
            }
        }
    });
}

//判断犬只是否存在
function getDog(e){
    if($.trim(e.value)==''){
        return false;
    }
    $.ajax( {
        type : "get",
        url :"/biz/dogCancel/findDogByTraceId",
        data : { traceId:$.trim(e.value)},
        success : function(res) {
            if(res.status==400){
                layer.msg(dogNotExit);
                model.isExist=false;
            }else {
                model.isExist=true;
                checkDogDevice(e);
            }
        }
    });
}

//检查犬只是否绑定设备
function checkDogDevice(e) {
    if($.trim(e.value)==''){
        return false;
    }
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/checkDogDevice",
        data : { traceId:$.trim(e.value)},
        success : function(res) {
            if(res.status==9000){
                $("#selectDeviceDeal").empty();
                var option = $("<option ></option>");
                option.val(-1);
                option.text(unBindNecklet);
                $("#selectDeviceDeal").append(option);
            }else{
                $("#selectDeviceDeal").empty();
                $("#selectCancelReason").empty();
                getStatis();
            }
        }
    });
}





function uploadPic() {
    if(model.cancelId==null){
        return false;
    }
    $("#file-1").fileinput("upload");
}

$("#file-1").fileinput({
    enctype: 'multipart/form-data',
    theme: 'fa',
    uploadUrl: '/biz/manureDisposal/uploadPic', // you must set a valid URL here else you will get an error
    allowedFileExtensions: [ "jpg", "gif", "bmp"],
    overwriteInitial: false,
    maxFileSize: 0,
    // maxFilesNum: 10,
    // allowedFileTypes:['jpg', 'png', 'gif','video','flash'],
    // previewFileType:['video', 'flash'],
    showUpload:true,
    showCancel:true,
    uploadAsync:false,
   // maxFileCount:1,//允许同时上传的最大文件数
   // autoReplace:true,
    showUpload:false,
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    layoutTemplates:{
        actionDelete:'',
        actionUpload:'',
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id:model.cancelId,
            type:2
        };
        return data;
    },
}).on("filebatchuploadsuccess", function (event, data, previewId, index){
    if(data.response.code == 0)
    {
        layer.confirm(operationSuccess, {
            btn : [ sure5 ]
        }, function(){
            window.location="/biz/dogCancel/dogCancelFile";
        })
    }else{
        layer.msg(operationFailure);
    }
    $("#file-1").fileinput("clear");
    $("#file-1").fileinput("reset");
}).on('fileerror', function(event, data, msg) {
    layer.msg(failUpPhoto);
}).on("filebatchselected", function(event, files) {
    model.blag=true;
}).on('filecleared', function(event, key) {
    model.blag=false;
});
