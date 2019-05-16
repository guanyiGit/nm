


$(function () {
	
	$("#save").click(function(){
		updateVetrinarian();
		});
	

    laydate.render({
        elem: '#birth', //指定元素
        format:"yyyy-MM-dd"
    });

}
)

function updateVetrinarian() {
    var data = $("#vetrinarianUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/veterinarian/updateVeterinarian",
        data: data,
        dataType: "json",
        success: function (data) {
        	
        	window.location="/biz/veterinarian/veterinarian_List";
        },
        error:function (error) {
        	alert("未知错误,请联系管理员");
        }
    });

}
