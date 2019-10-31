
let picIsSuccess = 0;
let videoIsSuccess = 0;
let videoSelected = false;
var corpseId=null;
$(function () {
	 //初始化死亡原因下拉框
    new MySelct({
        id:"selectDeath",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        params:{type:"casuse_of_death"}
    });
    
  //初始化处理方法下拉框
    new MySelct({
        id:"selectMethod",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        params:{type:"corpse_handle"}
    });
	
  //初始化项圈处理方式
    new MySelct({
        id:"deviceDealWay",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        params:{type:"device_deal_way"}
    });
    
}
)

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
                $("#deviceDealWay").empty();
                var option = $("<option ></option>");
                option.val(-1);
                option.text(unBindNecklet);
                $("#deviceDealWay").append(option);
            }
            else{
                $("#deviceDealWay").empty();
                //$("#selectCancelReason").empty();
              //初始化项圈处理方式
                new MySelct({
                    id:"deviceDealWay",
                    url:"/biz/manureDisposal/initSelectData",
                    value:"value",
                    text:"name",
                    params:{type:"device_deal_way"}
                });
            }
        }
    });
}

function addCorpse(form) {
	
	
	if(form.files.value==''){
		layer.msg(pleaseSelectPhoto);
		return false;
	}
	if(form.traceId.value==''){
		layer.msg(pleaseInputCorrectTraceID);
		form.traceId.focus();
		return false;
	}
	
		 var data = $("#corpseAdd").serialize();
		    $.ajax({
		        type: 'POST',
		        url: "/biz/corpseDisposal/save",
		        data: data,
		        dataType: "json",
		        success: function (result) {
		        	if(result.code==1){
		        		layer.alert(addCorpseFail);
		        	}else if(result.id==-1){
		        		layer.alert(dogNotExit);
		        		 form.traceId.focus();
		        	}
		        	else
		        	{
		        		corpseId=result.id;
			        	uploadPic();
		        	}
		        },
		        error:function (error) {
		        	 layer.alert(unKnownError);
		        }
		    });
   
}

function uploadPic() {
	 $("#file-1").fileinput("upload");
    if(videoSelected) {
        //视频选择了
        $("#file-2").fileinput("upload");
    }
    //TODO 需要做判断
    layer.confirm(operationSuccess, {
        btn : [ sure5 ]
    }, function() {
        location.href='/biz/corpseDisposal/toCorpseDisposal';
    })
}

$("#file-1").fileinput({
	enctype: 'multipart/form-data',
    theme: 'fa',
    uploadUrl: '/biz/manureDisposal/uploadPic', // you must set a valid URL here else you will get an error
    overwriteInitial: false,
    maxFileSize: 0,
    showUpload:false,
    //showCancel:true,
    uploadAsync:false,
    layoutTemplates:{
        actionDelete:'',
        actionUpload:'',
    },
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id: corpseId,
            type: 7
        };
        return data;
    }

    // maxFileCount:4,
    // previewFileIcon:"<iclass='glyphicon glyphicon-king'></i>",
    // uploadAsync:false,
    });

$("#file-2").fileinput({
    enctype: 'multipart/form-data',
    theme: 'fa',
    uploadUrl: '/biz/manureDisposal/uploadVideo', // you must set a valid URL here else you will get an error
    // allowedFileExtensions: ['jpg', 'png', 'gif','video','flash'],
    overwriteInitial: false,
    maxFileSize: 0,
    // maxFilesNum: 10,
    // allowedFileTypes:['jpg', 'png', 'gif','video','flash'],
    // previewFileType:['video', 'flash'],
    showUpload:true,
    //showCancel:true,
    uploadAsync:false,       //设置为同 步
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    layoutTemplates:{
        actionDelete:'',
        actionUpload:'',
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id: corpseId,
            type: 7
        };
        return data;
    }

    // maxFileCount:4,
    // previewFileIcon:"<iclass='glyphicon glyphicon-king'></i>",
    // uploadAsync:false,
    }).on("filebatchselected", function(event, files) {
    	videoSelected=true;
    });