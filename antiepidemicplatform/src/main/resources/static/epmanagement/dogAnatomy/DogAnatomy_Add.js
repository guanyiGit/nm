
let picIsSuccess = 0;
let videoIsSuccess = 0;
var dogAnatomyId=null;
let videoSelected = false;
var testAmount = null;
var positiveAmount = null;
var number_regex = /^[0-9.]+$/;
$(function () {
	
	

}
)

var compute_positiveRate = function() {
        console.log("testAmount: " + testAmount + ", positiveAmount: " + positiveAmount);
        if (testAmount == null || positiveAmount == null) {
            $("#positiveRate").val(waitCompute);
        } else {
            if (testAmount > 0 && positiveAmount >=0 && positiveAmount <= testAmount) {
                var positiveRate = positiveAmount / testAmount * 100;
                $("#positiveRate").val(positiveRate.toFixed(2));
            } else {
                $("#positiveRate").val(errorCompute);
                layer.msg(positiveShouldSmallThanTest);
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

        compute_positiveRate();
    });

$("#positiveAmount").on('input propertychange', function(){
        positiveAmount = null;

        if (number_regex.test($(this).val())) {
            var tmp = parseFloat($(this).val());
            if (tmp >= 0) {
                positiveAmount = tmp;
            } else {
            	layer.msg(positiveShouldBigThanZero);
            }
        } else {
        	layer.msg(pleaseInputLegal);
        }

        compute_positiveRate();
    });



function addDogAnatomy(form) {
	
	if(form.testAmount.value==''){
		layer.msg(pleaseInputTestNumber);
		form.testAmount.focus();
		return false;
	}
	if(form.positiveAmount.value==''){
		layer.msg(pleaseInputPositiveNum);
		form.positiveAmount.focus();
		return false;
	}
	
	if(form.harmlessTreatAmount.value==''){
		layer.msg(pleaseInputHarmlessNum);
		form.harmlessTreatAmount.focus();
		return false;
	}
	
	var check= /^\d+(\.{0,1}\d+){0,1}$/;
	if(!check.test(form.testAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.testAmount.focus();
	return false;
	}
	
	if(!check.test(form.positiveAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.positiveAmount.focus();
	return false;
	}
	
	if(!check.test(form.harmlessTreatAmount.value))
	{
		layer.msg(pleaseInputLegal);
		form.harmlessTreatAmount.focus();
	return false;
	}
	
	if(!check.test(form.positiveRate.value))
	{
		layer.msg(positiveRateError);
		form.positiveRate.focus();
	return false;
	}
	
	
	if(form.files.value==''){
		layer.msg(pleaseSelectPhoto);
		return false;
	}
	
	var flag=false;
	 var data = $("#dogAnatomyAdd").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/dogAnatomy/save",
        data: data,
        dataType: "json",
        async:false,
        success: function (data) {
        	if(data.id==null){
        		layer.msg(operationFailure);
        	}else{
        		flag=true;
        		dogAnatomyId=data.id;
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
        location.href='/biz/dogAnatomy/toDogAnatomy_List';
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
            id: dogAnatomyId,
            type: 15
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
            id: dogAnatomyId,
            type: 15
        };
        return data;
    }

    // maxFileCount:4,
    // previewFileIcon:"<iclass='glyphicon glyphicon-king'></i>",
    // uploadAsync:false,
    }).on("filebatchselected", function(event, files) {
    	videoSelected=true;
    });