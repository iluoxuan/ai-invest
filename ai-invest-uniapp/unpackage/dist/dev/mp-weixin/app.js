"use strict";
Object.defineProperty(exports, Symbol.toStringTag, { value: "Module" });
const common_vendor = require("./common/vendor.js");
const config_env = require("./config/env.js");
if (!Math) {
  "./pages/user/welcome/step1.js";
  "./pages/user/welcome/step2.js";
  "./pages/user/welcome/step3.js";
  "./pages/user/welcome/step4.js";
  "./pages/user/init/home.js";
  "./pages/start/stepOne.js";
  "./pages/start/stepTwo.js";
  "./pages/stock/searchAdd.js";
  "./pages/stock/addPlan.js";
}
const _sfc_main = {
  __name: "App",
  setup(__props) {
    const userInfoUrl = `${config_env.env.INVEST_URL}/api/user/info`;
    function storeButtonBound() {
      try {
        const buttonBound = common_vendor.index.getStorageSync("buttonBound");
        if (!buttonBound) {
          const res = common_vendor.index.getMenuButtonBoundingClientRect();
          console.log(res);
          common_vendor.index.setStorageSync("buttonBound", JSON.stringify(res));
        }
      } catch (error) {
        console.error("获取胶囊按钮位置失败:", error);
      }
    }
    async function checkUserAndNavigate() {
      try {
        const [error, res] = await common_vendor.index.request({
          url: userInfoUrl,
          method: "POST"
        });
        if (error || res.statusCode !== 200 || !res.data.success) {
          console.error("获取用户信息失败:", res || error);
          common_vendor.index.reLaunch({
            url: "/pages/user/init/home"
          });
          return;
        }
        const userInfo = res.data;
        common_vendor.index.setStorageSync("userInfo", JSON.stringify(userInfo));
        const shouldShowGuide = userInfo.hadInitAccount;
        const targetPage = shouldShowGuide ? "/pages/user/init/home" : "/pages/user/welcome/step1";
        common_vendor.index.reLaunch({
          url: targetPage
        });
      } catch (error) {
        console.error("请求失败:", error);
        common_vendor.index.reLaunch({
          url: "/pages/user/init/home"
        });
      }
    }
    common_vendor.onLaunch(async () => {
      storeButtonBound();
      await checkUserAndNavigate();
    });
    return () => {
    };
  }
};
function createApp() {
  const app = common_vendor.createSSRApp(_sfc_main);
  return {
    app
  };
}
createApp().app.mount("#app");
exports.createApp = createApp;
