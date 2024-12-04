"use strict";
const common_vendor = require("../common/vendor.js");
const config_env = require("../config/env.js");
function initAccount(data) {
  const url = `${config_env.env.INVEST_URL}/api/account/init`;
  return common_vendor.index.request({
    url,
    method: "POST",
    header: {
      "content-type": "application/json"
      // 默认值
    },
    data: JSON.stringify(data),
    // 将数据转换为JSON字符串
    success: (res) => {
      console.log("投资账户初始化成功:", res.data);
      return res;
    },
    fail: (err) => {
      console.error("投资账户初始化失败:", err);
      throw new Error("投资账户初始化失败");
    }
  });
}
exports.initAccount = initAccount;
