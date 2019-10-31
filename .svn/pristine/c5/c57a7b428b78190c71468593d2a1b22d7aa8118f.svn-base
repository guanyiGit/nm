
$(function () {
	
    
    laydate.render({
        elem: '#birth', //指定元素
        type:"datetime",
        format:"yyyy-MM-dd",
        value:new Date()
    });
    
}
)

function updateDogOwner(form) {
	if(form.name.value==''){
		layer.msg(qsrqzxm);
		form.name.focus();
		return false;
	}
	if(form.phoneNum.value==''){
		layer.msg(qsrqzdh);
		form.phoneNum.focus();
		return false;
	}
	if(form.cardNum.value==''){
		layer.msg(qsrqzzjhm);
		form.cardNum.focus();
		return false;
	}
	
	var re=/^[1][3,4,5,7,8][0-9]{9}$/;    
	if(!re.test(form.phoneNum.value.trim())){
		console.log(form.phoneNum.value);
		layer.msg(qsrzzsjhm);
		form.phoneNum.focus();
		return false;   
	}
	//检查身份证号
	var reg = /^([0-9]{15}|[0-9]{18})$/;
	if(!reg.test(form.cardNum.value.trim())){
		layer.msg(qsrzzsfzhm);
		form.cardNum.focus();
		return false;   
	}
	var flag1 = false;
	//身份证唯一性判断
	$.ajax({
        type: 'GET',
        url: '/biz/dogOwner/judugeUpdateExit',
        data: {id:form.id.value,cardNum:form.cardNum.value.trim()},
        dataType: "json",
        async: false,
        success: function (r) {
        	if(r.code==1){
        		layer.msg(gsfzhmyjczqcxsr);
        		form.cardNum.focus();
        	}else{
        		flag1=true;
        	}
        },
        error: function () {
            // console.log(r.msg);
        }
    })
    
    var flag2 = false;
    //电话号码唯一性判断
    $.ajax({
        type: 'GET',
        url: '/biz/dogOwner/judugeUpdateExit',
        data: {id:form.id.value,phoneNum:form.phoneNum.value.trim()},
        dataType: "json",
        async: false,
        success: function (r) {
        	if(r.code==1){
        		layer.msg(gdhhmyjczqcxsr);
        		form.phoneNum.focus();
        	}else{
        		flag2 = true;
        	}
        	
        	
        },
        error: function () {
        	layer.alert(wzcwqlxgly);
        }
    })
    
    if(flag1&&flag2){
    	var data = $("#dogOwnerUpdate").serialize();
        $.ajax({
            type: 'POST',
            url: "/biz/dogOwner/updateDogOwner",
            data: data,
            dataType: "json",
            success: function (data) {
            	layer.confirm(updateSuccess, {
          	         btn : [ determine ]
          	     }, function() {
          	    	window.location="/biz/dogOwner/dogOwner_List";
          	     })
            	
            },
            error:function (error) {
            	layer.confirm(wzcwqlxgly, {
         	         btn : [ determine ]
         	     }, function() {
         	    	window.location="/biz/dogOwner/dogOwner_List";
         	     })
            }
        });
    }
    

}
