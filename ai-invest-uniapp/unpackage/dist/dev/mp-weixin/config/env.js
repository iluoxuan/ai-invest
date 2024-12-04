"use strict";
const env = "development";
console.log("env=", env);
let INVEST_URL;
switch (env) {
  case "dev":
    INVEST_URL = "http://localhost:8081";
    break;
  case "test":
    INVEST_URL = "https://api.test.yourdomain.com";
    break;
  case "prod":
    INVEST_URL = "https://api.yourdomain.com";
    break;
  default:
    INVEST_URL = "http://localhost:8081";
}
const env$1 = {
  INVEST_URL
};
exports.env = env$1;
