"use strict";
const common_vendor = require("../../common/vendor.js");
const config_env = require("../../config/env.js");
const url = `${config_env.env.INVEST_URL}/api/stock/leftBuy`;
console.log("url", url);
const _sfc_main = {
  data() {
    return {
      account: {},
      position: {},
      holdings: []
    };
  },
  onLoad(options) {
    console.log("options", JSON.stringify(options));
    this.leftBuy(options.tsCode);
  },
  methods: {
    toPercentage(value) {
      if (!value)
        return "0%";
      return (value * 100).toFixed(2) + "%";
    },
    // 定义一个包装函数，返回 Promise
    requestWrapper(options) {
      return new Promise((resolve, reject) => {
        common_vendor.index.request({
          ...options,
          success: (res) => resolve([null, res]),
          fail: (err) => resolve([err, null])
        });
      });
    },
    async leftBuy(tsCode) {
      var _a, _b;
      try {
        const [err, res] = await this.requestWrapper({
          url,
          method: "POST",
          header: {
            "content-type": "application/json"
          },
          data: {
            "tsCode": tsCode
          },
          complete: (res2) => {
            console.log("请求完成:", res2);
          }
        });
        if (err) {
          console.error("请求失败:", err);
          throw new Error("请求失败");
        }
        if (!res.data || !res.data.success) {
          console.error("API 调用不成功:", ((_a = res.data) == null ? void 0 : _a.msg) || "未知错误");
          throw new Error(((_b = res.data) == null ? void 0 : _b.msg) || "未知错误");
        }
        this.holdings = res.data.data.buyPlanUnits;
        this.accout = res.data.data.account;
        this.position = res.data.data.position;
      } catch (error) {
        console.error("请求失败:", error.message);
        throw error;
      }
    },
    addStock() {
      this.navigateToNextPage();
    },
    navigateToNextPage() {
      common_vendor.index.navigateTo({
        url: "/pages/stock/add"
        // 替换为目标页面的路径
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.position.planAmount),
    b: common_vendor.t($data.position.availableAmount),
    c: common_vendor.f($data.holdings, (holding, index, i0) => {
      return {
        a: common_vendor.t($options.toPercentage(holding.fallRate)),
        b: common_vendor.t(holding.currentPrice),
        c: common_vendor.t(holding.buyAvgPrice),
        d: common_vendor.t(holding.totalLoss),
        e: common_vendor.t(holding.pe),
        f: common_vendor.t(holding.currentTotalMv),
        g: index
      };
    })
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-f787fe35"]]);
wx.createPage(MiniProgramPage);
