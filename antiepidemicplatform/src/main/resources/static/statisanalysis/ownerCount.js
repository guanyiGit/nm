$(function () {
    //显示年龄分布
    getOwnerAge();
    //显示性别分布
    getOwnerSex();
    if(roleId == 5 || roleId == 6 || roleId == 9 || roleId == 10) {
    //显示区域分布
        getOwnerArea();
    }
})



function getOwnerAge() {
    $.ajax({
        type: "get",
        url: "/biz/ownerCount/getOwnerAge",
        async: false,
        success: function (result) {
            if(result) {
                // console.log(result);
                var breedData = result;
                for (var i=0;i<breedData.length;i++) {
                    if (breedData[i].name=="20岁以下") {
                        breedData[i].name=twentyAgeFollowing;
                    }else if (breedData[i].name=="21~30岁") {
                        breedData[i].name=twentyOneToThirtyAnnum;
                    }else if (breedData[i].name=="31~40岁") {
                        breedData[i].name=thirtyOneToFortyAnnum;
                    }else if (breedData[i].name=="41~50岁") {
                        breedData[i].name=fortyOneToFiftyAnnum;
                    }else if (breedData[i].name=="51~60岁") {
                        breedData[i].name=fiftyOneToSixtyAnnum;
                    }else if (breedData[i].name=="60岁以上") {
                        breedData[i].name=sixtyAgeFollowing;
                    }
                }
                initEchcart4(breedData);
            }
        }
    })
}

function getOwnerSex() {
    $.ajax({
        type: "get",
        url: "/biz/ownerCount/getOwnerSex",
        async: false,
        success: function (result) {
            if(result) {
                // console.log(result);
                var breedData = result;
                for (var i=0;i<breedData.length;i++) {
                    if (breedData[i].name=="男"){
                        breedData[i].name=male
                    }else if (breedData[i].name=="女") {
                        breedData[i].name=female
                    }
                }
                initEchcart5(breedData);
            }
        }
    })
}
function getOwnerArea() {
    $.ajax({
        type: "get",
        url: "/biz/ownerCount/getOwnerAreaInfo",
        async: false,
        success: function (result) {
            if(result) {
                // console.log(result);
                var breedData = result;
                initEchart6(breedData);
            }
        }
    })
}

//测试
// initEchart4();
// initEchart5();
// initEchart6();


//显示年龄分布
function initEchcart4(breedData) {
    var myChart4 = echarts.init(document.getElementById('main4'));
    option = {
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },

        series : [
            {
                name:radiusModel,
                type:'pie',
            },
            {
                // name:'面积模式',
                type:'pie',
                radius : [20, 110],
                center : ['50%', '50%'],
                roseType : 'area',
                data: breedData
                    /*[
                    {value:10, name:'5岁以上'},
                    {value:5, name:'8岁以上'},
                    {value:15, name:'10岁以上'},
                    {value:25, name:'15岁以上'},
                    {value:20, name:'18岁以上'},
                    {value:35, name:'20岁以上'},
                ]*/
            }
        ]
    };

    myChart4.setOption(option);
}
//显示性别分布
function initEchcart5(breedData) {
    var myChart5 = echarts.init(document.getElementById('main5'));
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },

        series: [
            {
                // name:'访问来源',
                type:'pie',
                radius: ['40%', '60%'],
                // label: {
                //     normal: {
                //         backgroundColor: '#eee',
                //         borderColor: '#aaa',
                //         borderWidth: 1,
                //         borderRadius: 4,
                //         rich: {
                //             a: {
                //                 color: '#999',
                //                 lineHeight: 22,
                //                 align: 'center'
                //             },
                //             hr: {
                //                 borderColor: '#aaa',
                //                 width: '100%',
                //                 borderWidth: 0.5,
                //                 height: 0
                //             },
                //             b: {
                //                 fontSize: 16,
                //                 lineHeight: 33
                //             },
                //             per: {
                //                 color: '#eee',
                //                 backgroundColor: '#334455',
                //                 padding: [2, 4],
                //                 borderRadius: 2
                //             }
                //         }
                //     }
                // },
                data: breedData
                    /*[
                    {value:135, name:'母（45%）'},
                    {value:310, name:'公（55%）'},

                ]*/
            }
        ]
    };

    myChart5.setOption(option);

}
//显示区域分布
function initEchart6(breedData) {
    var myChart6 = echarts.init(document.getElementById('main6'));

    option = {
        color: ['dodgerblue'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'line'        // 默认为直线，可选为：'line-数线' | 'shadow 阴影'
            }
        },
        grid: {
            left: '3%',
            right: '3%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : breedData['areaInfo'],
                    // ['岗龙乡', '清真乡','岗龙乡', '清真乡','岗龙乡', '清真乡','岗龙乡', '清真乡'],
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
                name:ownerCount,
                type:'bar',
                barWidth: '60%',
                data:breedData['numInfo']
                    // [10, 52, 200, 334, 390, 330, 220,50]
            }
        ]
    };
    myChart6.setOption(option);
}