let mid = '';

$(function () {

    //初始化处理人下拉列表
    new MySelct({
        id:"operator",
        url:"/biz/userInfo/initHandlerSel",
        value:"id",
        text:"name",
    });



    laydate.render({
        elem: '#dealTime', //指定元素
        type:"datetime",
        format:"yyyy-MM-dd HH:mm:ss",
        value:new Date()
    });
})

// 保存
function saveManureDisposal() {
    var data = $('#saveManureDisposal').serialize();
    $.ajax({
        type: 'POST',
        url: '/biz/manureDisposal/save',
        data: data,
        dataType: "json",
        success: function (r) {
            console.log(r.id);
            mid = r.id;
            // console.log(r.code);
            uploadPic();
        },
        error: function () {
            // console.log(r.msg);
        }
    })
}



function uploadPic() {
    $("#file-1").fileinput("upload");
}

$("#file-1").fileinput({
    enctype: 'multipart/form-data',    theme: 'fa',
    uploadUrl: '/biz/manureDisposal/uploadPic', // you must set a valid URL here else you will get an error
    // allowedFileExtensions: ['jpg', 'png', 'gif','video','flash'],
    overwriteInitial: false,
    maxFileSize: 0,
    // maxFilesNum: 10,
    // allowedFileTypes:['jpg', 'png', 'gif','video','flash'],
    // previewFileType:['video', 'flash'],
    showUpload:true,
    showCancel:true,
    uploadAsync:false,       //设置为异步
    slugCallback: function (filename) {
        return filename.replace('(', '_').replace(']', '_');
    },
    uploadExtraData:function(){//向后台传递参数
        var data={
            id: mid,
            type: 6
        };
        return data;
    }

    // maxFileCount:4,
    // previewFileIcon:"<iclass='glyphicon glyphicon-king'></i>",
    // uploadAsync:false,
    }).on("filebatchuploadsuccess",function (event,data) {
        // todo 弹框提示，页面跳转
        var code = data['response'].code;
        var msg = data['response'].msg;

        if (code == 0) {
            layer.alert(msg);
            layer.confirm(msg, {
                btn : [ '确定' ]
            }, function() {
                location.href='/biz/manureDisposal';
            })
            // self.location='/biz/manureDisposal';
            // location.href='/biz/manureDisposal';
        } else {
            layer.alert(msg);
        }
    });
