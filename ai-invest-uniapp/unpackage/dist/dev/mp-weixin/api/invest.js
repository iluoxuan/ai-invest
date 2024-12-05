"use strict";
const common_vendor = require("../common/vendor.js");
const config_env = require("../config/env.js");
async function initAccount(data) {
  const url = `${config_env.env.INVEST_URL}/api/account/init`;
  try {
    console.log("发起请求:", url);
    console.log("请求数据:", data);
    const response = await common_vendor.index.request({
      url,
      method: "POST",
      header: {
        "content-type": "application/json"
      },
      data,
      complete: (res2) => {
        console.log("请求完成:", res2);
      }
    });
    if (!Array.isArray(response)) {
      console.error("uni.request 返回的不是一个数组:", response);
      throw new Error("uni.request 返回的不是一个数组");
    }
    const [err, res] = response;
    if (err) {
      console.error("投资账户初始化失败:", err);
      throw new Error("投资账户初始化失败");
    }
    if (!res || !res.data || typeof res.data !== "object") {
      console.error("API 响应数据无效:", res);
      throw new Error("API 响应数据无效");
    }
    console.log("API 响应:", res);
    console.log("API 响应数据:", res.data);
    if (res.data.success !== true) {
      console.error("API 调用不成功:", res.data.msg || "未知错误");
      throw new Error(res.data.msg || "未知错误");
    }
    return res.data;
  } catch (error) {
    console.error("投资账户初始化发生错误:", error.message);
    throw error;
  }
}
exports.initAccount = initAccount;
