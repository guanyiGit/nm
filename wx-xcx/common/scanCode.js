//点击 二维码扫描
/**
 * basicUrl 请求url
 * e  源数据
 * isReturn 是否返回 不返回直接跳转  回调函数
 */
module.exports = function scanCode(basicUrl, e, isReturn = false) {
   wx.scanCode({
    scanType: ["qrCode"], //只能扫描二维码
    success(res) {
      if (res.result) { //扫描出来内容了
        let deviceNum = res.result;
        // deviceNum = '8621740319829232'; //测试用
        wx.request({//根据设备编号查看设备信息
          url: basicUrl + '/dogCard/selectDogCardInfoByDevice',
          data: {
            deviceNum
          },
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          success(res) {
            if (res.data.code == '000000') {
              if (isReturn) {//需要回调
                isReturn(res.data.result, deviceNum);//犬只信息 设备编号
                return;
              }
              //扫码成功跳转到详情页
              wx.navigateTo({
                url: '/pages/page/petInfo/petInfo?dogId=' + res.data.result.dogId,
              })
            }else{
              wx.showToast({
                title: '二维码未能识别',
                icon:"none"
              })
            }
          }
        })
      }
    },
    fail(res) {
      wx.showToast({
        "title": "请选择清晰的二维码",
        "icon": "none"
      });
    },
  })
}