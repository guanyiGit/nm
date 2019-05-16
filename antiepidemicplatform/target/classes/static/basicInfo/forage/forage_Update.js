


$(function () {
	
	$("#save").click(function(){
		updateForage();
		});

}
)

function updateForage() {
    var data = $("#forageUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/forage/updateForage",
        data: data,
        dataType: "json",
        success: function (data) {
        	alert("修改成功!")
        	window.location="/biz/forage/forage_List";
        },
        error:function (error) {
        	alert("未知错误,请联系管理员");
        }
    });

}

