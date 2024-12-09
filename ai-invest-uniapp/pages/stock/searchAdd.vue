<template>
	<view class="container">

		<!-- 搜索框 -->
		<view class="search-box">
			<input type="text" placeholder="搜索" v-model="searchQuery" @blur="searchBuy" />
		</view>

		<view class="stock-add-tip">
			<view class="stock-add-tip-text">
				AI会综合各种指标推荐适合中长线的股票
			</view>

		</view>

		<!-- 持仓分时 -->
		<view class="holdings">
			<view class="header">
				<text class="column-title">股票</text>
				<text class="column-title">PE</text>
				<text class="column-title">市值</text>
				<text class="column-title">涨幅</text>
				<text class="column-title">现价</text>
			</view>
			<view class="row" v-for="(holding, index) in holdings" :key="index" @click="addPlan(holding, index)">
				<text class="column-value"> {{ holding.stockCnName }}</text>
				<text class="column-value">{{ holding.pe }}</text>
				<text class="column-value">{{ holding.totalMv }}</text>
				<text class="column-value">{{ holding.holdings }}</text>
				<text class="column-value">{{ holding.price }}</text>
			</view>
		</view>

	</view>
</template>

<script>
	import env from '@/config/env';
	const url = `${env.INVEST_URL}/api/stock/search`;
	console.log("url", url);

	export default {
		data() {
			return {
				searchQuery: '', // 用于存储输入框的内容
				holdings: []
			};
		},
		methods: {

			// 定义一个包装函数，返回 Promise
			requestWrapper(options) {
				return new Promise((resolve, reject) => {
					uni.request({
						...options,
						success: (res) => resolve([null, res]),
						fail: (err) => resolve([err, null])
					});
				});
			},
			async searchBuy() {

				// 调用搜索接口
				try {
					// 调用 uni.request 并等待其完成
					let query = this.searchQuery;
					console.log('用户输入的查询:', query);
					const [err, res] = await this.requestWrapper({
						url: url,
						method: 'POST',
						header: {
							'content-type': 'application/json'
						},
						data: {
							'keyWord': query
						},
						complete: (res) => {
							console.log('请求完成:', res); // 打印请求完成的信息
						}
					});

					// 如果有错误，抛出异常
					if (err) {
						console.error('搜索加仓:', err);
						throw new Error('搜索股票失败');
					}
					// 检查 API 响应是否成功
					if (!res.data || !res.data.success) {
						console.error('API 调用不成功:', res.data?.msg || '未知错误');
						throw new Error(res.data?.msg || '未知错误');
					}
					this.holdings = res.data.data;

				} catch (error) {
					console.error('搜索股票失败:', error.message);
					throw error; // 重新抛出错误以便调用方处理
				}

			},

			addPlan(holding, index) {
				// 去加仓计划页面
				console.log("holding", JSON.stringify(holding))
				this.navigateToNextPage(holding);
			},
			navigateToNextPage(holding) {
				// 使用uni-app的页面跳转方法
				uni.navigateTo({
					url: '/pages/stock/addPlan?tsCode=' + holding.tsCode // 替换为目标页面的路径
				});
			}
		}
	};
</script>

<style lang="scss" scoped>
	.container {
		display: flex;
		flex-direction: column;
		background-color: #fcfcfc;
		height: 100vh;

		.search-box {
			display: flex;
			align-items: center;
			border-radius: 1rpx;
			padding: 10rpx 20rpx;
			margin-top: 10rpx;
			margin-bottom: 10rpx;
			border-bottom: 1px solid #fcebe3;
			height: 80rpx;
			/* 只在下方添加一条线 */
		}

		.search-box input {
			flex: 1;
			border: none;
			outline: none;
			font-size: 28rpx;
			color: rgba(0, 0, 0, 0.6);

		}

		.stock-add-tip {
			display: flex;
			flex-direction: row;
			margin-top: 50rpx;
			margin-bottom: 5rpx;

			.stock-add-tip-text {
				display: flex;
				flex-direction: column;
				align-items: center;
				text-align: center;
				color: #dca476;
				font-size: 25rpx;

			}
		}

		.holdings {
			margin-top: 10rpx;
			margin-bottom: 25rpx;
			background-color: #ffffff;

			.header {
				display: flex;
				justify-content: space-between;
				background-color: #fcebe3;
				padding: 10rpx 0;

				.column-title {
					flex: 1;
					text-align: center;
					font-size: 28rpx;
					color: rgba(0, 0, 0, 0.6);
				}
			}

			.row {
				display: flex;
				justify-content: space-between;
				align-items: center;
				padding: 20rpx 0;
				box-shadow: 0 1px 0 #fcebe3;
				/* 使用 box-shadow 模拟细线 */
				/* 添加底部边框 */

				.column-value {
					flex: 1;
					text-align: center;
					font-size: 28rpx;
					color: #ea3538;
				}

				&:last-child {
					box-shadow: none;
					/* 隐藏最后一个列表项的细线 */
				}
			}
		}

	}
</style>