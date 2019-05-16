// 以下为官方示例
$().ready(function() {
	// validateRule();
	// $("#signupForm").validate();
    initOrgSelect();
    initAreaSelect();
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
        onLoadSuccess:function (node, data) {
        },
        onLoadError:function (error) {

        },
        onSelect:function (data) {
            // alert("id  :"+data.id+"  text  ::"+data.text+"  pid::"+data.parentId);
        }
    });
}

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update(form) {

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



    $("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/userInfo/update",
		data : $('#userEditForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert(unKnownError);
		},
		success : function(data) {
			if (data.code == 0) {
				location.href = "/biz/userInfo";
				parent.layer.msg(operationSuccess);
			} else {
				parent.layer.msg(operationFailure);
			}

		}
	});
}
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
// function setCheckedRoles() {
// 	var roleIds = $("#roleIds").val();
// 	alert(roleIds);
// 	var adIds = "";
// 	$("input:checkbox[name=role]:checked").each(function(i) {
// 		if (0 == i) {
// 			adIds = $(this).val();
// 		} else {
// 			adIds += ("," + $(this).val());
// 		}
// 	});
// 	return adIds;
// }
// function validateRule() {
// 	var icon = "<i class='fa fa-times-circle'></i> ";
// 	$("#signupForm").validate({
// 		rules : {
// 			name : {
// 				required : true
// 			},
// 			username : {
// 				required : true,
// 				minlength : 2
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
// 				minlength : icon + "用户名必须两个字符以上"
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
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}

function checkRoleNum() {
    var roles = $("input:checkbox[name=role]:checked");
    if(roles.length>1){
        parent.layer.msg("本系统暂时不支持多角色管理");
        $("#btnss").hide();
    }else {
        $("#btnss").show();
    }
}