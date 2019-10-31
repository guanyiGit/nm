
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
		layer.msg("请输入犬主姓名!");
		form.name.focus();
		return false;
	}
	if(form.phoneNum.value==''){
		layer.msg("请输入犬主电话!");
		form.phoneNum.focus();
		return false;
	}
	if(form.cardNum.value==''){
		layer.msg("请输入犬主证件号码!");
		form.cardNum.focus();
		return false;
	}
	
	var re=/^[1][3,4,5,7,8][0-9]{9}$/;    
	if(!re.test(form.phoneNum.value.trim())){
		console.log(form.phoneNum.value);
		layer.msg('请输入正确的手机号码!');
		form.phoneNum.focus();
		return false;   
	}
	//检查身份证号
	var reg = /^([0-9]{15}|[0-9]{18})$/;
	if(!reg.test(form.cardNum.value.trim())){
		layer.msg('请输入正确的身份证号码!');
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
        		layer.msg("该身份证号码已经存在!请重新输入!");
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
        		layer.msg("该电话号码已经存在!请重新输入!");
        		form.phoneNum.focus();
        	}else{
        		flag2 = true;
        	}
        	
        	
        },
        error: function () {
        	layer.alert("未知错误!请联系管理员!");
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
            	layer.confirm('修改成功!', {
          	         btn : [ '确定' ]
          	     }, function() {
          	    	window.location="/biz/dogOwner/dogOwner_List";
          	     })
            	
            },
            error:function (error) {
            	layer.confirm('未知错误,请联系管理员!', {
         	         btn : [ '确定' ]
         	     }, function() {
         	    	window.location="/biz/dogOwner/dogOwner_List";
         	     })
            }
        });
    }
    

}
