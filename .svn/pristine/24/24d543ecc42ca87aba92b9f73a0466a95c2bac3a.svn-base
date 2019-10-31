var model={
    dogId:null,
    blag:false,
    isBind:false,
    isAge:true,
    isWeight:true
};

$(function () {
    debugger
    //初始化犬主
    InintOwner(1);

    //初始化犬种
    // selectBreed();
    //初始化犬种下拉列表
    new MySelct({
        id:"selectBreed",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        params: {
            type: "dog_breed"
        }
    });

    //初始化电子围栏
    selectFence(1);

    //新增犬只
    $("#ajaxBtn").click(function() {
        addGog();
        uploadPic();
    })

});

function checkAge(e) {
    if(!isAge(e.value) && e.value!=''){
        layer.msg(falseAge);
        model.isAge=false;
        return false;
    }else {
        model.isAge=true;
    }
}

function checkWeight(e) {
    if(!isWeight(e.value) && e.value!=''){
        layer.msg(falseWeight);
        model.isWeight=false;
        return false;
    }else {
        model.isWeight=true;
    }
}

function addGog(){
    if($.trim( $("#deviceNo").val())==''){
        model.isBind=true;
    }
    if(model.isAge==false){
        layer.msg(falseAge);
        return false;
    }
    if(model.isWeight==false){
        layer.msg(falseWeight);
        return false;
    }
    if(model.isBind==false){
        layer.msg(disNecklet);
        return false;
    }
    if(model.blag==false){
        layer.msg(pleaseSelectPhoto);
        return false;
    }
    var params = $("#myForm").serialize();
    $.ajax( {
        type : "POST",
        url :"/biz/dogInfo/save",
        data : params,
        async: false,
        success : function(res) {
            if(res.status==200){
                model.dogId=res.data;
            }
        }
    });
}

function getDeviceNo(e) {
    if($.trim(e.value)==''){
        return false
    }
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/checkDeviceIsUse",
        data : {deviceNo:$.trim(e.value)},
        success : function(res) {
            if(res.code != 0) {
                layer.msg(res.msg);
                model.isBind=false;
            }else {
                model.isBind=true;
            }
        }
    });
}

function InintOwner(id){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getDogOwner",
        data : {Id:id},
        success : function(res) {
            var data=res.data;
            if(data!=null){
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].id);
                    option.text(data[i].name);
                    $("#selectOwner").append(option);
                }
            }
        }
    });
}
function selectBreed(){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getDogBreed",
        success : function(res) {
            var data=res.data;
            if(data!=null){
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].name);
                    option.text(data[i].name);
                    $("#selectBreed").append(option);
                }
            }
        }
    });

}
function selectFence(id){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getFence",
        data:{Id:id},
        success : function(res) {
            var data=res.data;
            if(data!=null){
                for (var i = 0; i < data.length; i++) {
                    var option = $("<option ></option>");
                    option.val(data[i].id);
                    option.text(data[i].name);
                    $("#selectFence").append(option);
                }
            }
        }
    });
}

function uploadPic() {
    if(model.dogId==null){
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
            id:model.dogId,
            type:0
        };
        return data;
    },
}).on("filebatchuploadsuccess", function (event, data, previewId, index){
    if(data.response.code == 0)
    {
        layer.confirm(operationSuccess, {
            btn : [ sure5 ]
        }, function(){
            window.location="/biz/dogInfo/dogMasterFile";
        })
    }else{
        layer.msg(operationFailure);
    }
}).on('filebatchuploaderror', function(event, data, msg) {
    layer.msg(failUpPhoto);
}).on('filecleared', function(event, key) {
    model.blag=false;
}).on("filebatchselected", function(event, files) {
    model.blag=true;
});