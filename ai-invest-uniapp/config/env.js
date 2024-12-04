// config/env.js
const env = process.env.NODE_ENV || 'dev'; // 获取当前环境，默认为 development
console.log('env=', env)
let INVEST_URL;

switch (env) {
	case 'dev':
		INVEST_URL = 'http://localhost:8081';
		break;
	case 'test':
		INVEST_URL = 'https://api.test.yourdomain.com';
		break;
	case 'prod':
		INVEST_URL = 'https://api.yourdomain.com';
		break;
	default:
		INVEST_URL = 'http://localhost:8081';
}

export default {
	INVEST_URL
};