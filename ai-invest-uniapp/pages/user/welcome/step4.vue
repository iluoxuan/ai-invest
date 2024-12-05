<template>
	<view class="page">

		<view class="tip">

			<view class="tip-text">
				你家庭总资产约 10万元
				每月支出约 1万元

			</view>
			<view class="tip-text">
				作为长线投资建议您配置80%以上的股权
			</view>
			<view class="tip-text">
				我们会提供给低风险长久的加仓计划
			</view>
			<view class="tip-text">
			</view>
			<view class="tip-text">
				请您输入计划投资股票的总金额
			</view>

		</view>
		<view class="uni-form-item">
			<input class="uni-input" type="number" placeholder="" @blur="handleClick('step4')" />
		</view>

	</view>
</template>

<script>
	import env from '@/config/env';
	const url = `${env.INVEST_URL}/api/account/init`;
	console.log("url", url)

	const reqData = {
		planAmount: "100000",
		totalAmount: "100000",
		monthlyExpenses: "10000",
		monthlyIncome: "10000"
	};
	export default {
		data() {
			return {

			}
		},
		methods: {

			async handleClick(item) {

				try {

					// 调用 uni.request 并等待其完成
					const response = await uni.request({
						url: url,
						method: 'POST',
						header: {
							'content-type': 'application/json'
						},
						data: reqData,
						complete: (res) => {
							console.log('请求完成:', res); // 打印请求完成的信息
							if (res.data.success) {
								uni.navigateTo({
									url: '/pages/user/init/home'
								});
								return;
							}
							throw new Error(res.data.msg);
						}
					});

				} catch (error) {
					console.error('投资账户初始化发生错误:', error.message);
					throw error; // 重新抛出错误以便调用方处理
				}

			}

		}
	}
</script>

<style lang="scss" scoped>
	.page {
		// 布局 flex
		display: flex;
		// 按列排
		flex-direction: column;
		align-items: center;
		height: 100vh;
		background-color: #fcfcfc;
		/* 设置背景颜色 */
	}

	.tip {
		/* 段落之间间距 */
		text-align: left;
		margin-top: 200rpx;
		margin-bottom: 50rpx;
	}

	.tip-text {
		font-size: 30rpx;
		color: #dca476;
		/* 设置文字颜色 */
		padding: 10rpx;

	}

	.uni-form-item {
		margin-top: 100rpx;
		width: 50%;
		height: 150rpx;
		background-color: #fcebe3;
		/* 参考图片中的背景色 */
		border-radius: 10rpx;
		overflow: hidden;
		display: flex;
		align-items: center;
		/* 垂直居中 */
		justify-content: center;

	}

	.uni-input {
		height: 48px;
		font-size: 15px;
		padding: 0px;
		flex: 1;
		color: #dca476;
	}

	.uni-icon {
		width: 24px;
		height: 24px;
	}
</style>