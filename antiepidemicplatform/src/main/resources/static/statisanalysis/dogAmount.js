$(function () {
    //统计犬只数量趋势
    countDogTrend();

    laydate.render({
        elem: '#startEndTime', //指定元素
        type:"month",
        range:true
    });
})

var vm = new Vue({
    el: '#app',
    data: {
        dogAmountList: []
    },
    mounted: function() {
        this.getDogAmount();
    },
    methods: {
        getDogAmount: function () {
            $.ajax({
                type: "get",
                url: "/biz/dogAmount/getDogNum",
                async: false,
                data: {
                    'startEndTime': $('#startEndTime').val()
                },
                success: (result)=> {
                    // alert(JSON.stringify(result));
                    this.dogAmountList = result;
                }
            })
        },
        reload: function() {
            this.getDogAmount(),
                countDogTrend()
        }
    }
})

function countDogTrend() {
    $.ajax({
        type: "get",
        url: "/biz/dogAmount/getDogTrend",
        async: false,
        data: {
            'startEndTime': $('#startEndTime').val()
        },
        success: function (result) {
            if(result) {
                // console.log(result);
                var breedData = result;
                initEchart(breedData);
            }
        }
    })
}

// initEchart();

//犬只数量趋势
function initEchart(breedData) {
    var myChart1 = echarts.init(document.getElementById('main1'));
    var colors = ['#00ff2a', '#1d2088', '#4d88e5','#FF00FF'];


    option = {
        color: colors,

        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data: breedData['townList']
                // ['柯曲镇', '上贡麻乡', '下贡麻乡','中贡麻乡']

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
                            return dogCount + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: breedData['monthList']
                    // ["2017-9", "2017-10", "2017-11", "2017-12", "2018-1", "2018-2", "2018-3", "2018-4", "2018-5", "2018-6", "2018-7", "2018-8"]
            }
            ,
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
                            return dogCount + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                // data: ["2015-9", "2015-10", "2015-11", "2015-12", "2016-1", "2016-2", "2016-3", "2016-4", "2016-5", "2016-6", "2016-7", "2016-8"]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series:functionName(breedData['townList'],breedData['numList'])
    };
    myChart1.setOption(option);
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

