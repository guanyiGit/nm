
var x=[];
var y=[];
var map =null;
var dis= 0;
$(function() {
	//计算犬只轨迹坐标体系
	calRecord();
	//计算电子围栏坐标体系
	calFence();
	//初始化地图
	initMap();
	//初始化犬只轨迹
	initDogRecord();
	//初始化电子围栏
	initFence();

});

//计算犬只轨迹坐标体系
function calRecord(){
	if(record !=null && record.length>0){
		for(var i=0;i<record.length;i++){
			if(record[i].lng>0 && record[i].lat>0){
				var item = [record[i].lng,record[i].lat];
				x.push(item);
			}
		}
	}
	
}

//计算电子围栏坐标体系
function calFence(){
	var arr=fence.tFenceDetail;
	if(arr.length>0 && arr !=null) {
		for(var i=0;i<arr.length;i++){
			if(arr[i].lng>0 && arr[i].lat>0){
				var item = [arr[i].lng,arr[i].lat];
				y.push(item);
			}
		}
	}
}

//初始化地图
function initMap(){
	if (x.length > 0){
		 map = new AMap.Map("container", {
	        center: x[0],
	        zoom: 14
	    });
	 	
	}
	else if(x.length == 0){
		    map = new AMap.Map('container', {
		        resizeEnable: true
		    });
		    AMap.plugin('AMap.Geolocation', function() {
		        var geolocation = new AMap.Geolocation({
		            enableHighAccuracy: true,//是否使用高精度定位，默认:true
		            timeout: 10000,          //超过10秒后停止定位，默认：5s
		            buttonPosition:'RB',    //定位按钮的停靠位置
		            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
		            zoomToAccuracy: true,   //定位成功后是否自动调整地图视野到定位点

		        });
		        map.addControl(geolocation);
		    });
	}
	else{
		layer.msg(failMap);
	}
	 
	    
}

//初始化犬只轨迹
function initDogRecord(){
	if (x.length > 0){
		//犬只轨迹
	    var polyline = new AMap.Polyline({
	        path: x,
	        isOutline: true,
	        outlineColor: '#ffeeff',
	        borderWeight: 1,
	        strokeColor: "#3366FF", 
	        strokeOpacity: 1,
	        strokeWeight: 2,
	        // 折线样式还支持 'dashed'
	        strokeStyle: "solid",
	        // strokeStyle是dashed时有效
	        strokeDasharray: [10, 5],
	        lineJoin: 'round',
	        lineCap: 'round',
	        zIndex: 50,
	    });
	    map.add(polyline);
	    //计算两点之间的距离
		dis= calDistance();
		$("#dis").html(dis+"公里");
		$("#detailDate").html(detaiDate);
		$("#dog").html(ownerNameAndDogName.dogName);
		$("#owner").html(ownerNameAndDogName.ownerName);
		
		var marker = new AMap.Marker({
			map:map,   // 显示在地图上
			position:x[0], //  设置点的位置,如果缺失此属性，将以地图中心点作为标记点
			offset : new AMap.Pixel(-20,-52),  // 偏移值 如果不设置默认偏移值为 -10,-34 ，如果为0,0则是为左上角对准所设置的点
//			topWhenClick : true, // 鼠标点击将置顶 （如被其他覆盖物遮挡时，可以设置此属性点击置顶该标记）
//			topWhenMouseOver:true,//鼠标经过置顶
			icon : '/images/start.png', //点标记显示内容（以什么图片替代标记），也可以是本地图片
			//content:'images/start.png', //点标记显示内容，可以是HTML要素字符串或者HTML DOM对象。content有效时，icon属性将被覆盖
			draggable : false, //标记可拖动
//			raiseOnDrag:true, //设置拖拽点标记时是否开启点标记离开地图的效果(就是当拖拽时，标记会悬浮起来，以免遮挡住你要放的位置)
//			cursor : "cur",  //鼠标悬停在标记上 变个样式，
//			visible:false, //点标记是否可见，设置为false隐藏
//			zIndex:99,  //设置标记的叠加顺序，值越高的越在上面，默认为100，当然这个设置只是初始设置，如果也设置了topWhenClick，点击的时候还是会跑到顶上的，并不影响
//			title : "鼠标悬停时显示", // 鼠标悬停在标记上显示内容
//			bubble :true, //设置为true，事件可以穿透到地图，简单示例：双击标记后地图放大

		});
		
		var marker2 = new AMap.Marker({
			map:map,   // 显示在地图上
			position:x[x.length-1], //  设置点的位置,如果缺失此属性，将以地图中心点作为标记点
			offset : new AMap.Pixel(-20,-52),  // 偏移值 如果不设置默认偏移值为 -10,-34 ，如果为0,0则是为左上角对准所设置的点
//			topWhenClick : true, // 鼠标点击将置顶 （如被其他覆盖物遮挡时，可以设置此属性点击置顶该标记）
//			topWhenMouseOver:true,//鼠标经过置顶
			icon : '/images/end.png',//点标记显示内容（以什么图片替代标记），也可以是本地图片
			//content:'终点', //点标记显示内容，可以是HTML要素字符串或者HTML DOM对象。content有效时，icon属性将被覆盖
			draggable : false, //标记可拖动
//			raiseOnDrag:true, //设置拖拽点标记时是否开启点标记离开地图的效果(就是当拖拽时，标记会悬浮起来，以免遮挡住你要放的位置)
//			cursor : "cur",  //鼠标悬停在标记上 变个样式，
//			visible:false, //点标记是否可见，设置为false隐藏
//			zIndex:99,  //设置标记的叠加顺序，值越高的越在上面，默认为100，当然这个设置只是初始设置，如果也设置了topWhenClick，点击的时候还是会跑到顶上的，并不影响
//			title : "鼠标悬停时显示", // 鼠标悬停在标记上显示内容
//			bubble :true, //设置为true，事件可以穿透到地图，简单示例：双击标记后地图放大

		});
		
		for(var i=0;i<x.length;i++){
			var offset = new AMap.Pixel(-5,-5);	
			
			var y=new AMap.Marker({
				map:map,   // 显示在地图上
				position:x[i], 
				offset:offset,
				icon : '/images/point.png',
				draggable : false
				});
		}
		//设置地图自适应可见范围
		map.setFitView();
	}else{
		layer.msg(failPath);
	}
	
};
//初始化电子围栏
function initFence(){
	if (y.length > 0){
		//电子围栏
		var polygon = new AMap.Polygon({
			path: y,  
			fillColor: null, // 多边形填充颜色
			borderWeight: 2, // 线条宽度，默认为 1
			strokeColor: 'red', // 线条颜色
		});
		
		map.add(polygon);
	}else{
		layer.msg(failFence);
	}
};


//计算犬只活动距离
function calDistance(){
	//计算两个坐标之间的距离
	if(x.length-1<=0){
		return 0
	}
	var num=0;
	for(var i=0;i<x.length;i++){
		if(i+1==x.length){
			break; 
		}
		// 返回 p1 到 p2 间的地面距离，单位：米
		num+=(AMap.GeometryUtil.distance(x[i], x[i+1]));
	}
	return (num/1000).toFixed(1);
}

//在指定位置打开信息窗体
function openInfo() {
    //构建信息窗体中显示的内容
    var info = [];
    info.push('<p style="font-size:20px;color:black;">活动距离:'+dis+'公里</p>');
    infoWindow = new AMap.InfoWindow({
        content: info.join("")  //使用默认信息窗体框样式，显示信息内容
    });
    if(x !=null && x.length>0){
    	infoWindow.open(map,x[0]);
    }
    
}

