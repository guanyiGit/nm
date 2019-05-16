var model={
    protector:null,
    operator:null,
    orgId:null,
    positionList:[],
    fenceList:[]
}

$(function () {





    initPro();
    initOrgSelect();

    getdogposition();
    findFenceList();
    getmap();

});





//初始化组织选择框
function initOrgSelect(){
    $('#orgSelect').combotree({
        url: '/biz/orgInfo/initOrgSelect2',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        width:'260px',
        height:'38px',
        panelWidth:260,
        onSelect:function (data) {
            //刷新列表
            // data.id 组织id
            model.orgId=data.id;
            getdogposition();
            findFenceList();
            getmap();
        },
        onLoadSuccess:function(node,data){
            $("#orgSelect").combotree('setValue',data[0].text);
        }
    });
}

function initPro(){
    $('#proSelect').combotree({
        url: '/biz/dogInfo/findProtector2',
        method:'get',
        required: false,
        valueField:'id',
        textField:'text',
        width:'220px',
        height:'38px',
        panelWidth:220,
        onSelect:function (data) {
           model.operator= data.userId;
           model.protector=data.id;
            getdogposition();
            findFenceList();
            getmap();
        }
    });
}




function getdogposition(){
    $.ajax( {
        type : "get",
        url :"/biz/position/findDogsPositions",
        data:{protector:change(model.protector),orgId:model.orgId},
        async:false,
        success : function(res) {
            var data=res.data;
            model.positionList=data;
        }
    });
}
function findFenceList(){
    $.ajax( {
        type : "get",
        url :"/biz/position/findFenceList",
        data:{operator:change(model.operator),orgId:model.orgId},
        async:false,
        success : function(res) {
            var data=res.data;
            model.fenceList=data;
        }
    });
}


function transfrom(lng,lat){
    var gps = [lng, lat];
    var lnglats;
    AMap.convertFrom(gps, 'gps', function (status, result) {
        if (result.info === 'ok') {
            lnglats= result.locations; // Array.<LngLat>
           // console.log(result);
        }
    });
    return lnglats;
}

function change(e){
    if(e==0){
        e=null;
    }
    return e;
}

//获得地图
function getmap(){
    var lng=100.909737;
    var lat=36.954071;
    if(model.positionList!=null){
        lng=model.positionList[0].lng;
        lat= model.positionList[0].lat;
    }
    var map = new AMap.Map('container', {
        zoom:15,//级别
        center: [lng,lat],//中心点坐标
        viewMode:'3D'//使用3D视图
    });
   addMarker(map);
    addFence(map);
}


//添加标记到地图
function addMarker(map){
    if(model.positionList!=null){
        var count=model.positionList.length;
        for (var i=0;i<count;i++){
            var ownerName='';
            if(model.positionList[i].ownerName!=null){
                ownerName=model.positionList[i].ownerName+",";
            }
            var dogName='';
            if(model.positionList[i].dogName!=null){
                dogName=model.positionList[i].dogName+",";
            }
            // 创建一个 Marker 实例：
            var marker = new AMap.Marker({
                position: new AMap.LngLat(model.positionList[i].lng,model.positionList[i].lat),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]model.positionList[i].lng,model.positionList[i].lat
                icon: '/images/dog_1.png', // 添加 Icon 图标 URL
                title:ownerName+dogName+timeStamp2String(model.positionList[i].createDate)
            });
            model.positionList.push(marker);
        }
        // 将创建的点标记添加到已有的地图实例：
        map.add(model.positionList);
    }
}

//添加围栏到地图
function addFence(map){
    var path = [
        new AMap.LngLat(101.932375,36.477454),
        new AMap.LngLat(101.842375,36.567454),
        new AMap.LngLat(101.652375,36.657454),
        new AMap.LngLat(101.862375,36.747454),
    ];

    if(model.fenceList!=null){
        var count1=model.fenceList.length;
        for(var i=0;i<count1;i++){
            var  fenceListDetail=model.fenceList[i].tFenceDetail;
            var  fenceName=model.fenceList[i].fenceName;
            if(fenceListDetail!=null){
                var fenceList=[];
                for (var k=0;k<fenceListDetail.length;k++){
                    var marker= new AMap.LngLat(fenceListDetail[k].lng,fenceListDetail[k].lat);
                    fenceList.push(marker);
                }
                var polygon = new AMap.Polygon({
                    path:fenceList,
                    fillColor: '#fff', // 多边形填充颜色
                    fillOpacity:0,
                    borderWeight: 1, // 线条宽度，默认为 1
                    strokeColor: '#006600', // 线条颜色
                    //strokeStyle:'dashed',
                    //strokeDasharray:[10,10]
                });
                var text1 = new AMap.Text({
                    position: new AMap.LngLat(fenceList[0].lng,fenceList[0].lat),
                    text: fenceName,
                    offset: new AMap.Pixel(-20, -20)
                })

                map.add(text1);
                map.add(polygon);
            }
        }
    }


}
