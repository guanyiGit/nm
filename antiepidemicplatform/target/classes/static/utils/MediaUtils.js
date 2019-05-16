
function getImage(value) {
    if(value == undefined) {
        return '暂无照片';
    }else {
        ip = 'http://192.168.0.69/';
        var url = value.substring(3,value.length);
        var imgURL = '<img src="'+ip+''+url+'">'
        return imgURL;
    }
}