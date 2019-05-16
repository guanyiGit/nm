$().ready(function() {
	validateRule();
    initAreaSelect();
    showByType(type);
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function  showByType(type){
	debugger
    if(type==1){
        $("#sheng").hide();
    }else if(type==2){
        $("#sheng").hide();
        $("#zhou").hide();
    }
}

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

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/orgInfo/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad(orgPid);
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}