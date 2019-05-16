// components/searchBox/other/dogInfo.js
const dateFormat = require("../../../../apis/dateFormat.js")
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },
  attached() {
    this.setData({ L: wx.T })
    const oldDate = new Date();
    oldDate.setMonth(oldDate.getMonth() - 1)

    this.setData({
      selectDate: {
        start: dateFormat(oldDate, 'yyyy-MM-dd'),
        end: dateFormat(new Date(), 'yyyy-MM-dd')
      }
    })
  },
  /**
   * 组件的初始数据
   */
  data: {
    selectDate: {
      start: '',
      end: ""
    },
    ageRange: {
      start: 0,
      end: 10
    },
    sexRange:{
      array:['全部','男','女'],
      index:0
    }
  },
  /**
   * 组件的方法列表
   */
  methods: {
    dateChange_start(e) {
      const {
        start,
        end
      } = this.data.selectDate
      const temp = e.detail.value
      if (this.checkdDataPicker(temp, end)) {
        this.setData({
          selectDate: {
            start: temp,
            end
          }
        })
      }

    },
    dateChange_end(e) {
      let {
        start,
        end
      } = this.data.selectDate
      const temp = e.detail.value;
      if (this.checkdDataPicker(start, temp)) {
        this.setData({
          selectDate: {
            start,
            end: temp
          }
        })
      }
    },
    //日期范围检验
    checkdDataPicker(startData, endData) {
      const stSplits = startData.split("-");
      startData = new Date(stSplits[0], stSplits[1], stSplits[2]);
      const enSplits = endData.split("-");
      endData = new Date(enSplits[0], enSplits[1], enSplits[2]);
      const reuelt = startData.getTime() < endData.getTime();
      if (!reuelt) {
        wx.showToast({
          'title': '开始起始不能小于结束日期',
          'icon': 'none'
        })
      }
      return reuelt;
    },
    startInputChange(e) {
      let {
        start,
        end
      } = this.data.ageRange;
      let temp = e.detail.value;
      if (!this.checkdAgePicker(temp, end)) {
        temp = start;
      }
      this.setData({
        ageRange: {
          start: temp,
          end
        }
      })
    },
    endInputChange(e) {
      let {
        start,
        end
      } = this.data.ageRange;
      let temp = e.detail.value;
      if (!this.checkdAgePicker(start, temp)) {
        temp = end;
      }
      this.setData({
        ageRange: {
          start,
          end: temp
        }
      })
    },
    //输入年龄检验
    checkdAgePicker(start, end) {
      const reuelt = start < end;
      if (!reuelt) {
        wx.showToast({
          'title': '起始年龄不能小于结束年龄',
          'icon': 'none'
        })
      }
      return reuelt;
    },
    checkdSexHandel(e) {
      this.data.sexRange.index = e.currentTarget.dataset.index;
      this.setData({
        sexRange: this.data.sexRange
      })
    }
  }
})