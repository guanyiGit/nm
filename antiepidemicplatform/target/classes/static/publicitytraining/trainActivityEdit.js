let mid = '';
let picIsSuccess = 0;
let videoIsSuccess = 0;
let picSelected = false;
let videoSelected = false;

$(function () {
    validateRule();
    //初始化日期
    laydate.render({
        elem: '#activityTime', //指定元素
        type:"datetime",
        // format:"yyyy-MM-dd HH:mm:ss",
        // value:new Date()
    });
})

$.validator.setDefaults({
    submitHandler : function(form) {
        $(form).find(":submit").attr("disabled", true).attr("value", "Submitting...");
        save();
    }
});

function save() {
    let data = $('#saveForm').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/activityInfo/update',
        data: data,
        dataType: "json",
        success: function (r) {
            if(r.code == 0) {
                layer.confirm(operationSuccess, {
                    btn : [ determine ]
                }, function() {
                    location.href='/biz/activityInfo/trainActivity';
                })
            }
        },
        error: function () {
            // console.log(r.msg);
        }
    })
}

function save() {
    let data = $('#saveForm').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/activityInfo/update',
        data: data,
        dataType: "json",
        success: function (r) {
            if(r.code == 0) {
                layer.confirm(operationSuccess, {
                    btn : [ determine ]
                }, function() {
                    location.href='/biz/activityInfo/trainActivity';
                })
            }
        },
        error: function () {
            // console.log(r.msg);
        }
    })
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#saveForm").validate({
        rules: {
            activityAddress: {
                required: true
            },
            activitySubject: {
                required: true
            },
            activityTarget: {
                required: true
            },
            participateAmount: {
                required: true,
                min: 1,
                digits: true
            },
            activityContent: {
                required: true
            }
        },
        messages: {
            activityAddress: {
                required: icon + pleaseEnterLocation
            },
            activitySubject: {
                required: icon + pleaseEnterSubject
            },
            activityTarget: {
                required: icon + pleaseEnterActiveObject
            },
            participateAmount: {
                required: icon + pleaseEnterNumberParticipants,
                digits : icon + onlyInputPosInt
            },
            activityContent: {
                required: icon + pleaseEnterActivityContent
            }
        }
    })
}