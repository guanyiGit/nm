$(function () {
    validateRule();
    //初始化接收人下拉列表(本乡组织的所有防疫员)
    new MySelct({
        id:"receiver",
        url:"/biz/protector/initProtectorSel2",
        value:"id",
        text:"name"
    });
})

$.validator.setDefaults({
    submitHandler : function(form) {
        $(form).find(":submit").attr("disabled", true).attr("value", "Submitting...");
        save();
    }
});
// $("#saveForm").validate({
//     submitHandler: function(form) {
//         $(form).find(":submit").attr("disabled", true).attr("value",
//             "Submitting...");
//         save();
//     }
// })

function save() {
    let data = $('#saveForm').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/deviceDistribution/save',
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