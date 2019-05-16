


$(function () {
	

}
)

function updateDrug(form) {
	if(form.drugName.value==''){
		layer.msg(qsrypm);
		form.drugName.focus();
		return false;
	}
    var data = $("#drugUpdate").serialize();
    $.ajax({
        type: 'POST',
        url: "/biz/drug/updateDrug",
        data: data,
        dataType: "json",
        success: function (data) {
        	if(data.msg=="0"){
        		layer.confirm(updateSuccess, {
                    btn : [ determine ]
                     }, function() {
                     
                     window.location="/biz/drug/antiepidemic_Drug_List";
                     })
        	}
        	else{
        		window.location="/biz/drug/therapeutical_Drug_List";
        	}
        	
        },
        error:function (error) {
        	layer.alert(wzcwqlxgly);
        }
    });

}

