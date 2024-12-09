"use strict";
const common_vendor = require("../../common/vendor.js");
const config_env = require("../../config/env.js");
const url = `${config_env.env.INVEST_URL}/api/stock/search`;
console.log("url", url);
const _sfc_main = {
  data() {
    return {
      searchQuery: "",
      // 用于存储输入框的内容
      holdings: []
    };
  },
  methods: {
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
    async searchBuy() {
      var _a, _b;
      try {
        let query = this.searchQuery;
        console.log("用户输入的查询:", query);
        const [err, res] = await this.requestWrapper({
          url,
          method: "POST",
          header: {
            "content-type": "application/json"
          },
          data: {
            "keyWord": query
          },
          complete: (res2) => {
            console.log("请求完成:", res2);
          }
        });
        if (err) {
          console.error("搜索加仓:", err);
          throw new Error("搜索股票失败");
        }
        if (!res.data || !res.data.success) {
          console.error("API 调用不成功:", ((_a = res.data) == null ? void 0 : _a.msg) || "未知错误");
          throw new Error(((_b = res.data) == null ? void 0 : _b.msg) || "未知错误");
        }
        this.holdings = res.data.data;
      } catch (error) {
        console.error("搜索股票失败:", error.message);
        throw error;
      }
    },
    addPlan(holding, index) {
      console.log("holding", JSON.stringify(holding));
      this.navigateToNextPage(holding);
    },
    navigateToNextPage(holding) {
      common_vendor.index.navigateTo({
        url: "/pages/stock/addPlan?tsCode=" + holding.tsCode
        // 替换为目标页面的路径
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o((...args) => $options.searchBuy && $options.searchBuy(...args)),
    b: $data.searchQuery,
    c: common_vendor.o(($event) => $data.searchQuery = $event.detail.value),
    d: common_vendor.f($data.holdings, (holding, index, i0) => {
      return {
        a: common_vendor.t(holding.stockCnName),
        b: common_vendor.t(holding.pe),
        c: common_vendor.t(holding.totalMv),
        d: common_vendor.t(holding.holdings),
        e: common_vendor.t(holding.price),
        f: index,
        g: common_vendor.o(($event) => $options.addPlan(holding, index), index)
      };
    })
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-2db44495"]]);
wx.createPage(MiniProgramPage);
