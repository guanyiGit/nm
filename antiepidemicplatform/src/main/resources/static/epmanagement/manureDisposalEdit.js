let images = [];
let videos = [];
let deviceNoUsed = 0;   //溯源ID默认合法

$(function () {
    debugger
    //初始化处理方法下拉列表
    new MySelct({
        id:"processMode",
        url:"/biz/manureDisposal/initSelectData",
        value:"value",
        text:"name",
        sv:sv||'',
        params: {
            type: "process_mode"
        }
    });
})

//检查该溯源ID是否合法
function checkDeviceNo() {
    deviceNoUsed = 1;
    let traceId = $('#traceId').val().trim();
    if(traceId == '') {
        layer.msg(traceIdCantBeEmpty);
        deviceNoUsed = 2;   //为空的标志
        return false;
    }
    $.ajax( {
        type : "get",
        url :"/biz/dogCancel/findDogByTraceId",
        data : {
            traceId: traceId
        },
        success : function(res) {
            if(res.status == 200) {
                deviceNoUsed = 0;   //该溯源Id合法
            }else {
                layer.msg(dogNotExit);
            }
        }
    });
}

function updateManureDisposal() {
    if(deviceNoUsed == 1) {
        layer.msg(traceIdNotExit);
        return false;
    }
    if(deviceNoUsed == 2) {
        layer.msg(traceIdCantBeEmpty);
        return false;
    }
    var data = $('#updateManureDisposal').serialize();
    $.ajax({
        type:"POST",
        url:"/biz/manureDisposal/update",
        data:data,
        dataType:"json",
        async:false,
        success: function (r) {
            if(r.code == 0)  {
                layer.confirm(r.msg, {
                    btn : [ sure5 ]
                }, function() {
                    location.href='/biz/manureDisposal';
                })
            }else {
                layer.alert(operationFailure);
            }
        },
        error: function (r) {
            layer.alert(unKnownError);
        }
    })
}

// function initImages() {
//     for (let i in picUrlList) {
//         let img = '<img src="'+picUrlList[i]['url']+'" style="max-width:100%;max-height:100%;">'
//         images[i] = img;
//     }
// }
//
// function initVideos() {
//     for (let i in videoUrlList) {
//         let video = '<video style="width:100%; height:100%; object-fit: fill" th:src="'+videoUrlList[i]['url']+'"  controls></video>';
//         videos[i] = video;
//     }
// }



// function initFileInput() {
//     $("#file-1").fileinput('destroy');
//     //初始化图片
//     initImages();
//     $("#file-1").fileinput({
//         language: 'zh', //设置语言
//         // uploadUrl: GLOBAL.URL + 'project/saveFile.do?id=' + bugId, //上传的地址
//         showUpload: false, //是否显示上传按钮
//         showCaption: false,//是否显示标题
//         maxFileCount: 6, //表示允许同时上传的最大文件个数
//         fileActionSettings: {showUpload: false},//是否显示上文件上的上传按钮
//         overwriteInitial: false,
//         allowedPreviewTypes: ['image'],
//         /*
//         ！这里是预览的数据格式
//         initialPreview: [
//             // IMAGE RAW MARKUP
//             'http://localhost:8080/amp/base/imgs/app-default.jpg',
//             // IMAGE RAW MARKUP
//             'http://localhost:8080/amp/base/imgs/app-default.jpg',
//             // TEXT RAW MARKUP
//             '123asd啊实打实',
//             'http://localhost:8080/amp/base/imgs/123.docx'
//         ],*/
//         initialPreview: images,
//         initialPreviewAsData: false, // allows you to set a raw markup
//         initialPreviewFileType: 'image', // image is the default and can be overridden in config below
//         // initialPreviewDownloadUrl: GLOBAL.URL + 'project/downFile.do?image={key}',
//
//         /*
//         ！这里是回显的数据格式，后台查询后，按照格式展示即可，url是删除访问地址，key是删除的id
//         initialPreviewConfig: [
//             {type: "image", caption: "Image-1.jpg", size: 847000, url: "/amp/project/delFile.do", key: 1},
//             {type: "image", caption: "Image-2.jpg", size: 817000, url: "/amp/project/delFile.do", key: '1519713821098wwp4h8v'},  // set as raw markup
//             {type: "text", size: 1430, caption: "LoremIpsum.txt", url: "/amp/project/delFile.do", key: 3},
//             {type: "office", size: 102400, caption: "123.docx", url: "/amp/project/delFile.do", key: '1519788281200pwxfx87'}
//         ],*/
//         // initialPreviewConfig: data.initialPreviewConfig,
//         preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
//         previewFileIconSettings: { // configure your icon file extensions
//             'doc': '<i class="fa fa-file-word-o text-primary"></i>',
//             'xls': '<i class="fa fa-file-excel-o text-success"></i>',
//             'ppt': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
//             'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
//             'txt': '<i class="fa fa-file-text-o text-info"></i>',
//             'zip': '<i class="fa fa-file-archive-o text-muted"></i>',
//             'htm': '<i class="fa fa-file-code-o text-info"></i>',
//             'mov': '<i class="fa fa-file-movie-o text-warning"></i>',
//             'mp3': '<i class="fa fa-file-audio-o text-warning"></i>'
//         },
//         previewFileExtSettings: { // configure the logic for determining icon file extensions
//             'doc': function (ext) {
//                 return ext.match(/(doc|docx)$/i);
//             },
//             'xls': function (ext) {
//                 return ext.match(/(xls|xlsx)$/i);
//             },
//             'ppt': function (ext) {
//                 return ext.match(/(ppt|pptx)$/i);
//             },
//             'zip': function (ext) {
//                 return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
//             },
//             'htm': function (ext) {
//                 return ext.match(/(htm|html)$/i);
//             },
//             'mov': function (ext) {
//                 return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
//             },
//             'mp3': function (ext) {
//                 return ext.match(/(mp3|wav)$/i);
//             },
//             'txt': function (ext) {
//                 return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
//             }
//         },
//         layoutTemplates: {
//             actionZoom: ''
//         },
//         uploadExtraData: {
//             img_key: "1000",
//             img_keywords: "happy, nature"
//         }
//     });
// }
//
//
//
//
// function initVideo(){
//     //初始化视频
//     initVideos();
//     alert(videos);
//     $("#file-2").fileinput({
//         previewFileType: 'video',
//         initialPreview: videos,
//         enctype: 'multipart/form-data',
//         theme: 'fa',
//         uploadUrl: '/biz/manureDisposal/uploadVideo', // you must set a valid URL here else you will get an error
//         // allowedFileExtensions: ['jpg', 'png', 'gif','video','flash'],
//         overwriteInitial: false,
//         maxFileSize: 0,
//         // maxFilesNum: 10,
//         // allowedFileTypes:['jpg', 'png', 'gif','video','flash'],
//         // previewFileType:['video', 'flash'],
//         showUpload:false,
//         showCancel:true,
//         uploadAsync:false,       //设置为同步
//         slugCallback: function (filename) {
//             return filename.replace('(', '_').replace(']', '_');
//         },
//         uploadExtraData:function(){//向后台传递参数
//             var data={
//                 id: mid,
//                 type: 6
//             };
//             return data;
//         }
//     })
// }
//
