$(function () {
    //
    getAntiTimeTrend();
    getToptenUsage();


    laydate.render({
        elem: '#startEndTime', //指定元素
        type:"month",
        range:true
    });
   /* laydate.render({
        elem: '#inp2', //指定元素
        type:"month",
        format:"yyyy-MM",
        value:new Date()
    });*/

})



var vm =new Vue({
    el: '#app',
    data: {
        drugUsageInfo:[[]]
    },
    mounted: function(){
        this.getDrugInfo();
    },
    methods: {
        getDrugInfo: function () {
            $.ajax({
                type: "get",
                url: "/biz/antiepidemicCount/getDrugUsage",
                async: false,
                data: {
                    'startEndTime': $('#startEndTime').val()
                },
                success: (result)=> {
                    // alert(JSON.stringify(result));
                    this.drugUsageInfo = result;
                    for (var i=0;i<this.drugUsageInfo.length;i++) {
                        for (var j=0;j<this.drugUsageInfo[i].length;j++) {
                            if (this.drugUsageInfo[i][j]=="时间") {
                                this.drugUsageInfo[i][j]=time
                            }
                            if (this.drugUsageInfo[i][j]=="吡喹酮咀嚼片") {
                                this.drugUsageInfo[i][j]=praziquantel
                            }
                        }
                    }
                    // alert(JSON.stringify(this.drugUsageInfo));

                }
            })
        },
        reload: function () {
            getAntiTimeTrend();
            getToptenUsage();
            this.getDrugInfo();
        }
    }
})

function getAntiTimeTrend() {
    $.ajax({
        type: "get",
        url: "/biz/antiepidemicCount/getAntipicdemicTrend",
        async: false,
        data: {
            'startEndTime': $('#startEndTime').val(),
            // 'endTime': $('#inp2').val()
        },
        success: function (result) {
            if(result) {
                var antiDate = result;
                initEchart1(antiDate);
            }
        }
    })
}

function getToptenUsage() {
    $.ajax({
        type: "get",
        url: "/biz/antiepidemicCount/getTopTenDrug",
        async: false,
        data: {
            'startEndTime': $('#startEndTime').val(),
            // 'endTime': $('#inp2').val()
        },
        success: function (result) {
            if(result) {
                var breedData = result;
                initEchart2(breedData);
            }
        }
    })
}

// initEchart1();
// initEchart2();

//防疫次数趋势
function initEchart1(antiDate) {
    var myChart1 = echarts.init(document.getElementById('main1'));
    var colors = ['#00ff2a', '#1d2088', '#4d88e5'];


    option = {
        color: colors,

        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data: antiDate['townList']
                // ['柯曲镇', '上贡麻乡', '下贡麻乡']
        },
        grid: {
            top: 70,
            bottom: 50
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[2]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return antiepidemicCount + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: antiDate['monthList']
                    // ["2017-9", "2017-10", "2017-11", "2017-12", "2018-1", "2018-2", "2018-3", "2018-4", "2018-5", "2018-6", "2018-7", "2018-8"]
            },
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return antiepidemicCount + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                }
                // data: ["2015-9", "2015-10", "2015-11", "2015-12", "2016-1", "2016-2", "2016-3", "2016-4", "2016-5", "2016-6", "2016-7", "2016-8"]
            },
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return drugDosage + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                }
                // data: ["2013-9", "2013-10", "2013-11", "2013-12", "2014-1", "2014-2", "2014-3", "2014-4", "2014-5", "2014-6", "2014-7", "2014-8"]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: functionName(antiDate['townList'],antiDate['numList'])
        //     [
        //     {
        //         name:'柯曲镇',
        //         type:'line',
        //         xAxisIndex: 1,
        //         smooth: true,
        //         data: [26, 59, 90, 26.4, 128.7, 70.7, 175.6, 182.2, 148.7, 181.8, 6.0, 23]
        //     },
        //     {
        //         name:'上贡麻乡',
        //         type:'line',
        //         smooth: true,
        //         data: [39, 59, 111, 118.7, 48.3, 99.2, 231.6, 46.6, 55.4, 138.4, 110.3, 0.7]
        //     },
        //     {
        //         name:'下贡麻乡',
        //         type:'line',
        //         xAxisIndex: 1,
        //         smooth: true,
        //         data: [56, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 168.8, 86.0, 43]
        //     },
        // ]
    };
    myChart1.setOption(option);

}
//使用前十的药品
function initEchart2(breedData) {
    var myChart2 = echarts.init(document.getElementById('main2'));
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
                data : breedData['drugNameList'],
                    // ['比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮', '比硅酮'],
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
                name:useTheNumber,
                type:'bar',
                barWidth: '60%',
                data: breedData['drugNumList']
                    //[400, 360, 300, 334, 390, 330, 220, 300, 334, 390, 330, 220]
            }
        ]
    };

    myChart2.setOption(option);
}


function functionName(townList,numList){

    var serie = [];
    for(var i = 0; i < numList.length; i++){
        var item = {
            name:townList[i],
            type: 'line',
            // itemStyle: {
            //     normal: {
            //         color: data[i].backgroundColor
            //     }
            // },
            xAxisIndex: 1,
            smooth: false,
            data: numList[i]
        }
        serie.push(item );
    };
    return serie;
}
