


$(function () {
	
	
	 //初始化民族下拉框
//    new MySelct({
//        id:"selectedNation",
//        url:"/biz/protector/findTotalNation",
//        value:"value",
//        text:"name",
//    });

    laydate.render({
        elem: '#birth', //指定元素
        format:"yyyy-MM-dd"
    });

}
)

function updateProtector(form) {
	
	if(form.name.value==''){
		layer.msg(qxzfyyxm);
		form.name.focus();
		return false;
	}
	if(form.phoneNum.value==''){
		layer.msg(qsrfyydh);
		form.phoneNum.focus();
		return false;
	}
	if(form.cardNum.value==''){
		layer.msg(qsrfyydhhm);
		form.cardNum.focus();
		return false;
	}
	
	var re=/^[1][3,4,5,7,8][0-9]{9}$/;    
	if(!re.test(form.phoneNum.value)){
		console.log(form.phoneNum.value);
		layer.msg(qsrzzsjhm);
		form.phoneNum.focus();
		return false;   
	}
	//检查身份证号
	var reg = /^([0-9]{15}|[0-9]{18})$/;
	if(!reg.test(form.cardNum.value)){
		layer.msg(qsrzzsfzhm);
		form.cardNum.focus();
		return false;   
	}
	var flag1 = false;
	//身份证唯一性判断
	$.ajax({
        type: 'GET',
        url: '/biz/protector/judugeUpdateExit',
        data: {id:form.id.value,cardNum:form.cardNum.value},
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
        url: '/biz/protector/judugeUpdateExit',
        data: {id:form.id.value,phoneNum:form.phoneNum.value},
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
        var data = $("#protectorUpdate").serialize();
        $.ajax({
            type: 'POST',
            url: "/biz/protector/updateProtector",
            data: data,
            dataType: "json",
            success: function (data) {
                debugger
            	if(data.code==0){//操作成功
					if(data.i==1){
                        layer.confirm(updateSuccess, {
                            btn : [determine ]
                        }, function() {
                            window.location="/biz/protector/protector_List";
                        })
					}else {
                        layer.msg(accountExit);
                        form.phoneNum.focus();
					}
				}else{
                    layer.alert(wzcwqlxgly);
				}


            },
            error:function (error) {
            	layer.alert(wzcwqlxgly);
            }
        });
    }
	
	
	

}
