"use strict";
Object.defineProperty(exports, Symbol.toStringTag, { value: "Module" });
const common_vendor = require("./common/vendor.js");
if (!Math) {
  "./pages/user/welcome/step1.js";
  "./pages/user/welcome/step2.js";
  "./pages/user/welcome/step3.js";
  "./pages/user/welcome/step4.js";
  "./pages/user/init/home.js";
  "./pages/start/stepOne.js";
  "./pages/start/stepTwo.js";
  "./pages/stock/add.js";
  "./pages/stock/addPlan.js";
}
const _sfc_main = {
  __name: "App",
  setup(__props) {
    common_vendor.onLaunch(() => {
      const buttonBound = common_vendor.index.getStorageInfoSync("buttonBound");
      if (!buttonBound) {
        const res = common_vendor.index.getMenuButtonBoundingClientRect();
        console.log(res);
        common_vendor.index.setStorageSync("buttonBound", res);
      }
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
