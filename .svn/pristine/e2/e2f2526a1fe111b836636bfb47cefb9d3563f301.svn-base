let videoSelected = false;
let picIsSuccess = 0;
let videoIsSuccess = 0;
var testAmount=null;
var qualifiedAmount=null;
var antibodyId=null;
var number_regex = /^[0-9.]+$/;
$(function () {
	
//	$("#qualifiedAmount").blur(function(){
//		 qualifiedAmount=$("#qualifiedAmount").val();
//	});
	//计算合格率
	//cal();
}
)
var compute_qualifiedRate = function() {
        if (testAmount == null || qualifiedAmount == null) {
            $("#qualifiedRate").val(waitCompute);
        } else {
            if (testAmount > 0 && qualifiedAmount >=0 && qualifiedAmount <= testAmount) {
                var qualifiedRate = qualifiedAmount / testAmount * 100;
                $("#qualifiedRate").val(qualifiedRate.toFixed(2));
            } else {
                $("#qualifiedRate").val(errorCompute);
                layer.msg(qualifyShouldSmallThanTest);
            }
        }
    }


$("#testAmount").on('input propertychange', function(){
        testAmount = null;

        if (number_regex.test($(this).val())) {
            var tmp = parseFloat($(this).val());
            if (tmp > 0) {
                testAmount = tmp;
            } else {
            	layer.msg(TestShouldBigThanZero);
            }
        } else {
        	layer.msg(pleaseInputLegal);
        }

        compute_qualifiedRate();
    });

$("#qualifiedAmount").on('input propertychange', function(){
        qualifiedAmount = null;

        if (number_regex.test($(this).val())) {
            var tmp = parseFloat($(this).val());
            if (tmp >= 0) {
                qualifiedAmount = tmp;
            } else {
            	layer.msg(qualifiedAmountShouldBigThanZero);
            }
        } else {
        	layer.msg(pleaseInputLegal);
        }

        compute_qualifiedRate();
    });

function addAntibodyDetection(form) {
	
	if(form.testAmount.value==''){
		layer.msg(pleaseInputTestNumber);
		form.testAmount.focus();
		return false;
	}
	if(form.qualifiedAmount.value==''){
		layer.msg(pleaseInputQualifyNum);
		form.qualifiedAmount.focus();
		return false;
	}
	
	
	var check= /^\d+(\.{0,1}\d+){0,1}$/;
	
	if(!check.test(form.testAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.testAmount.focus();
	return false;
	}
	
	if(!check.test(form.qualifiedAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.qualifiedAmount.focus();
	return false;
	}
	
	
	if(!check.test(form.qualifiedRate.value))
	{
		layer.msg(qualifyRateError);
		form.qualifiedRate.focus();
	return false;
	}
	
	if(form.files.value==''){
		layer.msg(pleaseSelectPhoto);
		return false;
	}
	
	var flag=false;
	 var data = $("#antibodyDetectionAdd").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/antibodyDetection/save",
        data: data,
        dataType: "json",
        async:false,
        success: function (data) {
        	if(data.id==null){
        		layer.msg(addFail);
        	}else{
        		flag=true;
        		antibodyId=data.id;
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
        location.href='/biz/antibodyDetection/toAntibodyDetection_List';
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
            id: antibodyId,
            type: 16
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
            id: antibodyId,
            type: 16
        };
        return data;
    }

    // maxFileCount:4,
    // previewFileIcon:"<iclass='glyphicon glyphicon-king'></i>",
    // uploadAsync:false,
    }).on("filebatchselected", function(event, files) {
    	videoSelected=true;
    });