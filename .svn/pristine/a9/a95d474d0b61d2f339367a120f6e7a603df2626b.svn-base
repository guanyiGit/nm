$(function () {
    validateRule();
    //初始化组织下拉列表
    new MySelct({
        id:"orgSelect",
        url:"/biz/orgInfo/initOrgSelect4",
        value:"id",
        text:"departName",
        sv:sv||''
    });
})

$.validator.setDefaults({
    submitHandler : function(form) {
        $(form).find(":submit").attr("disabled", true).attr("value", "Submitting...");
        update();
    }
});


function update() {
    let data = $('#saveForm').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/deviceDistribution/update',
        data: data,
        dataType: "json",
        success: function (r) {
            layer.confirm(operationSuccess, {
                btn : [ determine ]
            }, function() {
                location.href='/biz/deviceDistribution';
            })
        },
        error: function () {
            // console.log(r.msg);
        }
    })
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#saveForm").validate({
        rules : {
            amount : {
                required : true,
                min : 1,
                digits:true
            }
        },
        messages : {
            amount : {
                required : icon + pleaseInputNumber,
                digits : icon + onlyInputPosInt
            }
        }
    })
}