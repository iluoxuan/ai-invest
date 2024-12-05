"use strict";
const common_vendor = require("../../../common/vendor.js");
const config_env = require("../../../config/env.js");
const url = `${config_env.env.INVEST_URL}/api/account/info`;
console.log("url", url);
const _sfc_main = {
  data() {
    return {
      account: {},
      totalAssets: "1,075,244.75",
      totalProfitLoss: "-553.51",
      dailyReferencePL: "653.00",
      marketValue: "1,032,932.65",
      availableBalance: "42,262.10",
      withdrawableBalance: "42,262.10",
      holdings: [
        {
          marketValue: "1,000,000.00",
          profitLoss: "0.00%",
          holdings: "10000",
          available: "0",
          costPrice: "100.000",
          currentPrice: "100.000"
        },
        {
          marketValue: "23,814.45",
          profitLoss: "2.140%",
          holdings: "300",
          available: "300",
          costPrice: "HK$83.216",
          currentPrice: "HK$85.000"
        },
        {
          marketValue: "6,379.20",
          profitLoss: "0.250%",
          holdings: "1600",
          available: "1600",
          costPrice: "3.977",
          currentPrice: "3.987"
        },
        {
          marketValue: "2,739.00",
          profitLoss: "1.410%",
          holdings: "100",
          available: "100",
          costPrice: "27.010",
          currentPrice: "27.390"
        }
      ]
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
        url: "/pages/stock/add"
        // 替换为目标页面的路径
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.account.totalAmount),
    b: common_vendor.t($data.account.usedAmount),
    c: common_vendor.t($data.account.planAmount),
    d: common_vendor.t($data.totalAssets),
    e: common_vendor.t($data.totalProfitLoss),
    f: common_vendor.t($data.dailyReferencePL),
    g: common_vendor.f($data.holdings, (holding, index, i0) => {
      return {
        a: common_vendor.t(holding.marketValue),
        b: common_vendor.t(holding.profitLoss),
        c: common_vendor.t(holding.holdings),
        d: common_vendor.t(holding.costPrice),
        e: common_vendor.t(holding.currentPrice),
        f: index
      };
    }),
    h: common_vendor.o(($event) => $options.addStock("加仓")),
    i: common_vendor.o(($event) => _ctx.market("市场")),
    j: common_vendor.o(($event) => _ctx.subStock("市场"))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-8f6ab2a6"]]);
wx.createPage(MiniProgramPage);
