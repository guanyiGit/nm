let antiRatio = "";
let unAntiRatio = "";
$(function () {
    //获取上月使用次数前十的药品
    // getTopTen();
    //获取防疫次数趋势
    getPerMonthTimes();
    //获取管理犬只数量趋势
    getChargeDogsTrend();

    //请求犬只总数、未防疫犬只、已防疫犬只数量
    //初始化犬只总数进度条
    //初始化未防疫犬只
    //初始化已防疫犬只数量
    initChart1();

    antiRatio = antiAmount == 0 ? "0%" : Math.round(antiAmount/totalAmount * 100) + "%";
    unAntiRatio = unAntiAmount == 0 ? "0%" : Math.round(unAntiAmount/totalAmount * 100) + "%";
    initChart2();
    initChart3();
})





function initChart1() {
    $("#chart1").circleChart({
            color: "rgb(51, 67, 165)",
            backgroundColor: "#e6e6e6",
            background: true,
            speed: 2000,
            widthRatio: 0.2,
            value: 100,
            unit: 'percent',
            counterclockwise: false,
            size: 110,
            startAngle: 0,
            animate: true,
            backgroundFix: true,
            lineCap: "round",
            animation: "easeInOutCubic",
            text: "100%",
            redraw: true,
            cAngle: 0,
            textCenter: true,
            textSize: true,
            textWeight: 'normal',
            textFamily: 'sans-serif',
            relativeTextSize: 1 / 7,
            autoCss: true,
            onDraw: false
        }
    );
}
function initChart2() {
    $("#chart2").circleChart({
            color: "rgb(0, 153, 0)",
            backgroundColor: "#e6e6e6",
            background: true,
            speed: 2000,
            widthRatio: 0.2,
            value: (antiAmount/totalAmount)*100,
            unit: 'percent',
            counterclockwise: false,
            size: 110,
            startAngle: 0,
            animate: true,
            backgroundFix: true,
            lineCap: "round",
            animation: "easeInOutCubic",
            text: antiRatio,
            redraw: true,
            cAngle: 0,
            textCenter: true,
            textSize: true,
            textWeight: 'normal',
            textFamily: 'sans-serif',
            relativeTextSize: 1 / 7,
            autoCss: true,
            onDraw: false
        }
    );
}
function initChart3() {
    $("#chart3").circleChart({
            color: "rgb(255, 0, 0)",
            backgroundColor: "#e6e6e6",
            background: true,
            speed: 2000,
            widthRatio: 0.2,
            value: (unAntiAmount/totalAmount)*100,
            unit: 'percent',
            counterclockwise: false,
            size: 110,
            startAngle: 0,
            animate: true,
            backgroundFix: true,
            lineCap: "round",
            animation: "easeInOutCubic",
            text: unAntiRatio,
            redraw: true,
            cAngle: 0,
            textCenter: true,
            textSize: true,
            textWeight: 'normal',
            textFamily: 'sans-serif',
            relativeTextSize: 1 / 7,
            autoCss: true,
            onDraw: false
        }
    );
}



// function getTopTen() {
//     $.ajax({
//         type: "get",
//         url: "/biz/homePage/getTopTen",
//         async: false,
//         success: function (result) {
//             if(result) {
//                 // console.log(result);
//                 var breedData = result;
//                 // alert(JSON.stringify(breedData));
//                 initEchart(breedData);
//             }
//         }
//     })
// }
// 防疫次数趋势
function getPerMonthTimes() {
    $.ajax({
        type: "get",
        url: "/biz/homePage/getPerMonthTimes",
        async: false,
        success: function (result) {
            if(result) {

                // alert(JSON.stringify(result));
                initEchart2(result);
            }
        }
    })
}
//管理犬只数量趋势
function getChargeDogsTrend() {
    $.ajax({
        type: "get",
        url: "/biz/homePage/getChargeDogsTrend",
        async: false,
        success: function (result) {
            if(result) {

                // alert(JSON.stringify(result));
                initEchart3(result);
            }
        }
    })
}



var vm = new Vue({
    el: '#app',
    data: {
        antiepidemicTimes: '',
        newAddedDogs: '',
        cancelDogs: '',
        deadDogs: '',
        homePageList: []
    },
    mounted:function() {
        // this.getAntiepidemicTimes();
        // this.getAddeddogNum();
        this.getDogNums();
        this.getHomePageUnreadMsg();
        // this.getAmounts();


    },
    methods: {
        //获取免疫次数
        // getAntiepidemicTimes : function(){
        //     $.ajax({
        //         type: "get",
        //         url: "/biz/homePage/getAntiepidemicTimes",
        //         async: false,
        //         success: (result)=> {
        //             this.antiepidemicTimes = result;
        //         }
        //     })
        // },
        //获取上月新增犬只
        // getAddeddogNum : function(){
        //     $.ajax({
        //         type: "get",
        //         url: "/biz/homePage/getAddedDogNum",
        //         async: false,
        //         success: (result)=> {
        //             this.newAddedDogs = result;
        //         }
        //     })
        // },
        getDogNums : function(){
            $.ajax({
                type: "get",
                url: "/biz/homePage/getDogNums",
                async: false,
                success: (result)=> {
                    this.newAddedDogs = result.addNumber;
                    this.cancelDogs = result.cancelNumber;
                    this.deadDogs = result.dealNumber;
                }
            })
        },
        //获取首页消息
        getHomePageUnreadMsg : function(){
            $.ajax({
                type: "get",
                url: "/biz/homePage/getHomePageUnreadedMsg",
                async: false,
                success: (result)=> {
                    // alert(JSON.stringify(result));
                    //转换日期格式
                    for(var i=0;i<result.length;i++) {
                        var createDate = result[i].createDate;
                        result[i].createDate = changeDateFormat(createDate);
                    }
                    this.homePageList = result;
                }
            })
        },
        //初始化防疫统计
        // getAmounts : function() {
        //     $.ajax({
        //         type: "get",
        //         url: "/biz/homePage/getAmounts",
        //         async: false,
        //         success: (result)=>  {
        //             if(result) {
        //
        //                 this.totalAmount = result.totalAmount;
        //                 this.unAntiAmount = result.unAntiAmount;
        //                 this.antiAmount = result.antiAmount;
        //             }
        //             // initChart1();
        //         }
        //     })
        // }
    }
})





    //上月使用次数前十药品
    function initEchart(breedData) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
        option = {
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : breedData['drugName'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'使用数量',
                    type:'bar',
                    barWidth: '60%',
                    data: breedData['times']
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }


//防疫次数趋势
function initEchart2(result) {

// 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('main2'));

// 指定图表的配置项和数据
    option = {

        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:[antiepidemicCount5]
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result['monthBetween']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:antiepidemicCount5,
                type:'line',
                stack: '总量',
                data:result['nums']
            },
        ]
    };


// 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option);

}

//管理犬只数量趋势
function initEchart3(result) {
    // 基于准备好的dom，初始化echarts实例
    var myChart3 = echarts.init(document.getElementById('main3'));

// 指定图表的配置项和数据
    option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:[dogCount5]
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: result['monthBetween']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:dogCount5,
                type:'line',
                stack: '总量',
                data: result['nums']
            },
        ]
    };


// 使用刚指定的配置项和数据显示图表。
    myChart3.setOption(option);

}

function read(id) {
    layer.open({
        type : 2,
        title : '消息详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/read/' + id +'/'+status,// iframe的url
        end: function () {
            location.reload();
        }
    });
}
