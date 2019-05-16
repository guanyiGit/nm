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
    tempFilePaths: [],
    text: ["xxxx", "xxxx", "xxxxx"]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    //预览图片
    previewImage(e){
      //获取当前图片的下标
      var index = e.currentTarget.dataset.index;
      //所有图片
      var tempFilePaths = this.data.tempFilePaths;
      wx.previewImage({
        //当前显示图片
        current: tempFilePaths[index],
        //所有图片
        urls: tempFilePaths
      })
    },

    /**
     * 删除图片
     */
    deleteImage(e){
      console.log(e);
      var that = this;
      var index = e.currentTarget.dataset.index;//获取当前长按图片下标
      var tempFilePaths = that.data.tempFilePaths;
      wx.showModal({
        title: '提示',
        content: '确定要删除此图片吗？',
        success:function(res){
          if (res.confirm){
            tempFilePaths.splice(index,1);
          }else{
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
    chooseImage() {
      // var that =this;
      wx.chooseImage({
        count: config.maxImage,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'],
        success: (res) => {
          console.log(res);
          // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
          if (res.tempFilePaths.length > 3) {
            wx.showToast({
              title: '最多上传三张',
              icon: 'success',
              duration: 1500,
              mask: false,
            })
            return;
          }
          var tempFilePaths_local = this.data.tempFilePaths;
          var imageLength = tempFilePaths_local.length + res.tempFilePaths.length;
          if (imageLength > 3) {
            wx.showToast({
              title: '最多上传三张',
              icon: 'success',
              duration: 1500,
              mask: false,
            })
            return;
          }
          var tempFilePaths = res.tempFilePaths;
          for (var index in tempFilePaths){
            tempFilePaths_local.push(tempFilePaths[index]);
          }
          this.setData({
            tempFilePaths: tempFilePaths_local
          })
        },
      })
    },

    /**
     * 上传图片
     */
    uploadImage(id,type){
      var that = this;
      var tempFilePaths = that.data.tempFilePaths;
      for (var index in tempFilePaths){
        wx.uploadFile({
          url: config.url.BASE_URL + "/manureDisposal/uploadPic",
          filePath: tempFilePaths[index],
          name: 'files',
          header: {
            "Content-Type": "application/x-www-form-urlencoded",
            "token": app.globalData.token
          },
          formData: {
            id: id,
            type: type,
          },
          success: function (res) {
            console.log(res)
          },
        })
      }
    }
  }
})