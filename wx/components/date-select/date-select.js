var dateTimePicker = require("../../apis/dateTimePicker.js")
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    dateTimeArray1: null,
    dateTime1: null,
    startYear: 2000,
    endYear: 2050
  },

  /**
   * 组件的方法列表
   */
  methods: {
    //value值改变时执行
    changeDateTime1(e) {
      var that = this;
      this.setData({ dateTime1: e.detail.value });
      var dateTimeArray1 = that.data.dateTimeArray1;
      var dateTime1 = that.data.dateTime1;
      var date = dateTimeArray1[0][dateTime1[0]] + "-" + dateTimeArray1[1][dateTime1[1]] + "-" + dateTimeArray1[2][dateTime1[2]] + " " + dateTimeArray1[3][dateTime1[3]] + ":" + dateTimeArray1[4][dateTime1[4]] + ":" + dateTimeArray1[5][dateTime1[5]]
      var eventDetail  = {
        val: date
      }
      this.triggerEvent("dateEvent", eventDetail);
    },
    //某一列的值改变时触发
    changeDateTimeColumn1(e) {
      var arr = this.data.dateTime1, dateArr = this.data.dateTimeArray1;
      //获取改变列的值，并复制到arr中
      arr[e.detail.column] = e.detail.value;
      //根据列改变值获取date
      dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);
      this.setData({
        dateTimeArray1: dateArr,
      });
    },
  },

  //生命周期函数
  attached:function(){
    var obj1 = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    this.setData({
      dateTimeArray1: obj1.dateTimeArray,
      dateTime1: obj1.dateTime
    });
  }
})
