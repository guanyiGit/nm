  //二维码扫描
const http = require("./request.js")
import i18n from "../utils/i18n.js"

const scan = function (isShowToast = false) {
    return new Promise((resolve, reject) => {
      const split = "&&";
      wx.scanCode({
        onlyFromCamera: true,
        success: function(res) {
          let code = res.result;
          const findIndex = code.indexOf(split);
          if (findIndex > 0) { //新设备
            code = code.substring(0, findIndex);
          }
          resolve(code);
        },
        fail(res) {
          if (isShowToast) {
            wx.showToast({
              // "title": "未能识别二维码",
              "duration": 3000,
              "title": i18n.get("weinengshibieerweima"),
              "icon": "none"
            });
          }
          reject(res);
        }
      });
    })
  }


  //检查deviceNo是否绑定有犬只
  const check = function(basicUrl, token, imei) {
    return new Promise((resolve, reject) => {
      const url = basicUrl + '/dogInfo/checkDeviceIsUse';
      const params = {
        deviceNo: imei
      }

      http.httpGet(url, token, params, function(res) {
        res.deviceNo = imei;
        if (res.code == 9000 || res.code == 9002 || res.code == 9003 || res.code == 9004) {
          resolve(res);
        } else {
          resolve(res);
        }
      })

    })
  }

  /**
   * 扫码
   * param1 是否显示
   * param2 basicUrl
   * param3 token
   * return 
   *        return R.error(9000,"该设备不存在");
   *        return R.error(9002,"该设备已被绑定");
   *        return R.error(9003,"该设备已丢失");
   *        return R.error(9004,"该设备已损坏");
   */
  const scan_code = function(isShowToast = false, basicUrl, token) {
    return scan(isShowToast)
      .then(imei => {
        return check(basicUrl, token, imei, isShowToast);
      }).catch(res => {
        return new Promise((resolve, reject) => { reject()});
      })
  }

  module.exports = {
    scan: scan,
    check: check,
    scan_code: scan_code
  }