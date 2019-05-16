// components/searchBox/other/antiepidemicOther.js
const dateFormat = require("../../../../apis/dateFormat.js")
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
    typeRange: {
      array: ['全部', '春防', '秋防', '月月投药'],
      index: 0
    },
    selectDate: {
      start: '',
      end: ""
    },
  },

  attached() {
    this.initDate();
  },
  /**
   * 组件的方法列表
   */
  methods: {
    initDate() {
      const oldDate = new Date();
      oldDate.setMonth(oldDate.getMonth() - 1)

      const default_start = dateFormat(oldDate, 'yyyy-MM');
      const default_end = dateFormat(new Date(), 'yyyy-MM')

      let { start ,end } = this.setData;
      if (!start) {
        start = default_start;
      }
      if (!end){
        end = default_end;
      }
      
      this.setData({
        selectDate: {
          start,
          end
        }
      })
    },
    dateChange_start(e) {
      const {
        start,
        end
      } = this.data.selectDate
      const temp = e.detail.value;
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
      startData = new Date(stSplits[0], stSplits[1]);
      const enSplits = endData.split("-");
      endData = new Date(enSplits[0], enSplits[1]);
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
    checkdTypeHandel(e) {
      this.data.typeRange.index = e.currentTarget.dataset.index;
      this.setData({
        typeRange: this.data.typeRange
      })
    },
    reset(e) {
      this.setData({
        typeRange: {
          array: ['全部', '春防', '秋防', '月月投药'],
          index: 0
        },
        selectDate: {
          start: '',
          end: ""
        }
      })
      this.initDate();
    },
    confirm(e){
      this.triggerEvent('conditionChanged', {
        "selectDate": this.data.selectDate,
        "typeRange": this.data.typeRange
      });
    }
  }
})