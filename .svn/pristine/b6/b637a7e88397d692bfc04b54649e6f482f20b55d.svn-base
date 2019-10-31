var config = require("../../apis/config.js");
var icon = require("../../apis/icon.js")
var app = getApp();
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
    tempFilePaths:[],
    ishasVideo:false
  },

  /**
   * 组件的方法列表
   */
  methods: {
    
    attached: function () {
      this.setData({ L: wx.T })
    },
    /**
    * 删除图片
    */
    deleteVideo(e) {
      var that = this;
      var index = e.currentTarget.dataset.index;//获取当前长按图片下标
      var tempFilePaths = that.data.tempFilePaths;
      wx.showModal({
        // title: '提示',
        title: wx.T.get("tishi"),
        // content: '确定要删除此视屏吗？',
        content: wx.T.get('quedinyaoshanchucishipingma') + "?",
        success: function (res) {
          if (res.confirm) {
            tempFilePaths.splice(index, 1);
          } else {
            console.log('点击取消了');
            return false;
          }
          that.setData({
            tempFilePaths: tempFilePaths
          })
        }
      })
    },

    //选择图片
    chooseVideo() {
      wx.chooseVideo({
        sourceType: ['album', 'camera'], //视频选择的来源
        compressed: true,     //是否压缩所选择的视频文件
        maxDuration: 60,   //拍摄视频最长拍摄时间，单位秒
        success: (res) => {
          var tempFilePaths_local = this.data.tempFilePaths;
          var tempFilePath = res.tempFilePath;
          tempFilePaths_local.push(tempFilePath);
          this.setData({
            tempFilePaths: tempFilePaths_local
          })
        },
      })
    },

    /**
  * 上传视屏
  */
    uploadVideo(id, type) {
      var that = this;
      var tempFilePaths = that.data.tempFilePaths;
      console.log(tempFilePaths)
      for (var index in tempFilePaths) {
        wx.uploadFile({
          url: config.url.BASE_URL + "/manureDisposal/uploadVideo",
          filePath: tempFilePaths[index],
          name: 'videos',
          header: {
            "Content-Type": "application/x-www-form-urlencoded",
            "token": app.globalData.token
          },
          formData: {
            id: id,
            type: type,
          },
          success: function (res) {
            console.log("uploadVideo",res);
          },
        })
      }
    }
  }
})
