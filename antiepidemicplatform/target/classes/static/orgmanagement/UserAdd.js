$().ready(function() {
	// validateRule();
    initAreaSelect();
    initOrgSelect();




});

//初始化区域选择框
function initAreaSelect(){
    $('#areaSelect').combotree({
        url: '/biz/areaInfo/initAreaSelect',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        onLoadSuccess:function (node, data) {
        },
        onLoadError:function (error) {

        },
        onSelect:function (data) {
			// alert("id  :"+data.id+"  text  ::"+data.text+"  pid::"+data.parentId);
        }
    });
}

//初始化组织选择框
function initOrgSelect(){
    $('#orgSelect').combotree({
        url: '/biz/orgInfo/initOrgSelect',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        editable:true,
        lines:true,
        onLoadSuccess:function (node, data) {
        },
        onLoadError:function (error) {

        },
        onSelect:function (data) {
            // alert("id  :"+data.id+"  text  ::"+data.text+"  pid::"+data.parentId);
        }
    });
}



// $.validator.setDefaults({
// 	submitHandler : function() {
// 		save();
// 	}
// });
function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}

function checkRoleNum() {
    var roles = $("input:checkbox[name=role]:checked");
    if(roles.length>1){
        parent.layer.msg(multiRoleManagement);
        $("#bnts").hide();
    }else {
        $("#bnts").show();
    }
}

function save(form) {
    //输入前校验
    //1.校验所属区域
    if(form.areaId.value==''){
        layer.msg(pleaseSelectArea);
        return false;
    }
    //2.校验姓名
    if(form.name.value==''){
        layer.msg(pleaseInputName);
        form.name.focus();
        return false;
    }
    //3.校验登录账号
    if(form.username.value==''){
        layer.msg(pleaseInputAccount);
        form.username.focus();
        return false;
    }
    //4.校验电话号码
    if(form.mobile.value==''){
        layer.msg(pleaseInputMobile);
        form.mobile.focus();
        return false;
    }
    var re=/^[1][3,4,5,7,8][0-9]{9}$/;
    if(!re.test(form.mobile.value)){
        layer.msg(qsrzzsjhm);
        form.mobile.focus();
        return false;
    }
    //5.校验所属组织
    if(form.deptId.value==''){
        layer.msg(pleaseSelectOrg);
        return false;
    }

    //7.校验状态
    if(form.status.value==''){
        layer.msg(pleaseSelectStatus);
        return false;
    }
    //8.校验所属角色
    if(form.areaId.value==''){
        layer.msg(pleaseSelectRole);
        return false;
    }

    //9.‘校验用户账号是否重复
    var flag =true;
    $.ajax({
        type: 'GET',
        url: '/biz/userInfo/checkAccountRepeat',
        data: {account:form.username.value},
        dataType: "json",
        async: false,
        success: function (r) {
            debugger
            if(r.code==0){
                if(r.i !=0){
                    layer.msg(accountExit);
                    form.mobile.focus();
                    flag=false;
                }

            }else{
                layer.msg(unKnownError);
            }


        },
        error: function () {
            layer.msg(unKnownError);
        }
    })


    $("#roleIds").val(getCheckedRoles());
    if(flag){
        $.ajax({
            cache : true,
            type : "POST",
            url : "/biz/userInfo/save",
            data : $('#userAddForm').serialize(),// 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert(unKnownError);
            },
            success : function(data) {
                if (data.code == 0) {
                    location.href = "/biz/userInfo";
                    $('#userAddForm')[0].reset()
                    parent.layer.msg(operationSuccess);
                    // parent.reLoad();
                    // var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    // parent.layer.close(index);
                } else {
                    parent.layer.msg(operationFailure);
                }

            }
        });
    }

}




// function validateRule() {
// 	var icon = "<i class='fa fa-times-circle'></i> ";
// 	$("#signupForm").validate({
// 		rules : {
// 			name : {
// 				required : true
// 			},
// 			username : {
// 				required : true,
// 				minlength : 2,
// 				remote : {
// 					url : "/sys/user/exit", // 后台处理程序
// 					type : "post", // 数据发送方式
// 					dataType : "json", // 接受数据格式
// 					data : { // 要传递的数据
// 						username : function() {
// 							return $("#username").val();
// 						}
// 					}
// 				}
// 			},
// 			password : {
// 				required : true,
// 				minlength : 6
// 			},
// 			confirm_password : {
// 				required : true,
// 				minlength : 6,
// 				equalTo : "#password"
// 			},
// 			email : {
// 				required : true,
// 				email : true
// 			},
// 			topic : {
// 				required : "#newsletter:checked",
// 				minlength : 2
// 			},
// 			agree : "required"
// 		},
// 		messages : {
//
// 			name : {
// 				required : icon + "请输入姓名"
// 			},
// 			username : {
// 				required : icon + "请输入您的用户名",
// 				minlength : icon + "用户名必须两个字符以上",
// 				remote : icon + "用户名已经存在"
// 			},
// 			password : {
// 				required : icon + "请输入您的密码",
// 				minlength : icon + "密码必须6个字符以上"
// 			},
// 			confirm_password : {
// 				required : icon + "请再次输入密码",
// 				minlength : icon + "密码必须6个字符以上",
// 				equalTo : icon + "两次输入的密码不一致"
// 			},
// 			email : icon + "请输入您的E-mail",
// 		}
// 	})
// }

// var openDept = function(){
// 	layer.open({
// 		type:2,
// 		title:"选择部门",
// 		area : [ '300px', '450px' ],
// 		content:"/system/sysDept/treeView"
// 	})
// }


// function loadDept( deptId,deptName){
// 	$("#deptId").val(deptId);
// 	$("#deptName").val(deptName);
// }