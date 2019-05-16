
let picIsSuccess = 0;
let videoIsSuccess = 0;
let videoSelected = false;
var infectionId=null;
$(function () {
	
}
)


function addInfectionInfo(form) {
	
	if(form.detectionAmount.value=='' || form.detectionAmount.value ==null){
		layer.msg(pleaseInputSurveyNum);
		form.detectionAmount.focus();
		return false;
	}
	if(form.infectionAmount.value === "" || form.infectionAmount.value ==null){
		layer.msg(pleaseInputInfectionNum);
		form.infectionAmount.focus();
		return false;
	}
	
	if(form.harmlessTreatAmount.value=='' || form.harmlessTreatAmount.value ==null){
		layer.msg(pleaseInputHarmlessNum);
		form.harmlessTreatAmount.focus();
		return false;
	}
	
	var check= /^\d+(\.{0,1}\d+){0,1}$/;
		if(!check.test(form.detectionAmount.value))
		{
			layer.msg(pleaseInputLegal);
			form.detectionAmount.focus();
		return false;
		}
	
		if(!check.test(form.infectionAmount.value))
		{
			layer.msg(pleaseInputLegal);
			form.infectionAmount.focus();
		return false;
		}

	
		if(!check.test(form.harmlessTreatAmount.value))
		{
			layer.msg(pleaseInputLegal);
			form.harmlessTreatAmount.focus();
		return false;
		}
	
	if(form.files.value==''){
		layer.msg(pleaseSelectPhoto);
		return false;
	}
	
	var flag=false;
	 var data = $("#infectionAdd").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/infectionInfo/save",
        data: data,
        dataType: "json",
        async:false,
        success: function (data) {
        	if(data.code==1){
        		layer.msg(addFail);
        	}else{
        		flag=true;
        		infectionId=data.id;
        	}
        	
        	
        	//window.location="/biz/corpseDisposal/toCorpseDisposal";
        },
        error:function (error) {
        	layer.msg(unKnownError);
        }
    });
	
	if(flag){
		uploadPic();
	}
	
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
	        location.href='/biz/infectionInfo/toInfectionInfo_List';
	    })
}

$("#file-1").fileinput({
	enctype: 'multipart/form-data',
    theme: 'fa',
    uploadUrl: '/biz/manureDisposal/uploadPic', // you must set a valid URL here else you will get an error
    // allowedFileExtensions: ['jpg', 'png', 'gif','video','flash'],
    overwriteInitial: false,
    maxFileSize: 0,
    // maxFilesNum: 10,
    // allowedFileTypes:['jpg', 'png', 'gif','video','flash'],
    // previewFileType:['video', 'flash'],
    showUpload:true,
    //showCancel:true,
    uploadAsync:false,      
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    layoutTemplates:{
        actionDelete:'',
        actionUpload:'',
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id: infectionId,
            type: 17
        };
        return data;
    }

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
    uploadAsync:false,       
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    layoutTemplates:{
        actionDelete:'',
        actionUpload:'',
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id: infectionId,
            type: 17
        };
        return data;
    }

    }).on("filebatchselected", function(event, files) {
    	videoSelected=true;
    });