"use strict";
const common_vendor = require("../../../common/vendor.js");
const config_env = require("../../../config/env.js");
const url = `${config_env.env.INVEST_URL}/api/account/info`;
console.log("url", url);
const _sfc_main = {
  data() {
    return {
      account: {},
      holdings: []
    };
  },
  onLoad() {
    this.getAccountInfo();
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
    async getAccountInfo() {
      var _a, _b;
      try {
        const [err, res] = await this.requestWrapper({
          url,
          method: "POST",
          header: {
            "content-type": "application/json"
          },
          data: {},
          complete: (res2) => {
            console.log("请求完成:", res2);
          }
        });
        if (err) {
          console.error("获取账户信息失败:", err);
          throw new Error("获取账户信息失败");
        }
        if (!res.data || !res.data.success) {
          console.error("API 调用不成功:", ((_a = res.data) == null ? void 0 : _a.msg) || "未知错误");
          throw new Error(((_b = res.data) == null ? void 0 : _b.msg) || "未知错误");
        }
        this.account = res.data.data.account;
        this.holdings = res.data.data.stockList;
      } catch (error) {
        console.error("投资账户初始化发生错误:", error.message);
        throw error;
      }
    },
    addStock() {
      this.navigateToNextPage();
    },
    navigateToNextPage() {
      common_vendor.index.navigateTo({
        url: "/pages/stock/searchAdd"
        // 替换为目标页面的路径
      });
    },
    buyPlan(holding, index) {
      console.log("buyPlan", JSON.stringify(holding), index);
      common_vendor.index.navigateTo({
        url: "/pages/stock/addPlan"
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.account.totalAmount),
    b: common_vendor.t($data.account.monthlyExpenses),
    c: common_vendor.t($data.account.monthlyIncome),
    d: common_vendor.t($data.account.planAmount),
    e: common_vendor.t($data.account.availableAmount),
    f: common_vendor.t($data.account.inverstDay),
    g: common_vendor.f($data.holdings, (holding, index, i0) => {
      return {
        a: common_vendor.t(holding.stockCnName),
        b: common_vendor.t(holding.pe),
        c: common_vendor.t(holding.totalMv),
        d: common_vendor.t(holding.holdings),
        e: common_vendor.t(holding.price),
        f: index,
        g: common_vendor.o(($event) => $options.buyPlan(holding, index), index)
      };
    }),
    h: common_vendor.o(($event) => $options.addStock("加仓")),
    i: common_vendor.o(($event) => _ctx.market("市场")),
    j: common_vendor.o(($event) => _ctx.subStock("市场"))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-8f6ab2a6"]]);
wx.createPage(MiniProgramPage);
