import env from '@/config/env'; // 默认导入

export function initAccount(data) {
	const url = `${env.INVEST_URL}/api/account/init`; // 替换为实际的API URL

	uni.request({
		url: url,
		method: 'POST',
		header: {
			'content-type': 'application/json' // 默认值
		},
		data: JSON.stringify(data), // 将数据转换为JSON字符串
		success: (res) => {
			console.log('投资账户初始化成功:', res.data);
			return res;
		},
		fail: (err) => {
			console.error('投资账户初始化失败:', err);
			throw new Error('投资账户初始化失败');
		}
	});
}