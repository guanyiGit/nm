var model={
    strayId:null,
    blag:false,
    isAge:true,
    isWeight:true
};
$(function () {
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

    $("#ajaxBtn").click(function () {
        addStrayGog();
        uploadPic();
    });
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

function selectBreed(){
    $.ajax( {
        type : "get",
        url :"/biz/dogInfo/getDogBreed",
        success : function(res) {
            var data=res.data;
            for (var i = 0; i < data.length; i++) {
                var option = $("<option ></option>");
                option.val(data[i].name);
                option.text(data[i].name);
                $("#selectBreed").append(option);
            }
        }
    });
}

function addStrayGog(){
    if(model.isAge==false){
        layer.msg(falseAge);
        return false;
    }
    if(model.isWeight==false){
        layer.msg(falseWeight);
        return false;
    }
    if($("#processMode").val()==''){
        layer.msg(falseHandleWay);
        return false;
    }if(model.blag==false){
        layer.msg(pleaseSelectPhoto);
        return false;
    }
    var params = $("#myFrom3").serialize();
    $.ajax( {
        type : "POST",
        url :"/biz/strayDog/save",
        data : params,
        async: false,
        success : function(res) {
            if(res.status==200){
                model.strayId=res.data;
            }
        }
    });
}

function uploadPic() {

    if(model.strayId==null){
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
            id:model.strayId,
            type:1
        };
        return data;
    },
}).on("filebatchuploadsuccess", function (event, data, previewId, index){
    if(data.response.code == 0)
    {
        layer.confirm(operationSuccess, {
            btn : [ sure5 ]
        }, function(){
            window.location="/biz/strayDog/StrayDogFile";
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
