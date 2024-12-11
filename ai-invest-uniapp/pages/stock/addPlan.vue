<template>
	<view class="container">

		<view class="account">
			<view class="tip">您当前账户剩余资金{{ position.planAmount}}</view>
			<view class="tip">AI推荐加仓金额{{ position.availableAmount }}</view>
			<view class="value">
				<input type="text" placeholder="输入计划加仓金额" v-model="planAmount" @blur="leftBuy" />
			</view>

		</view>

		<view class="stock">

			<view class="title">{{ stockInfo.cnName }}( {{ stockInfo.symbol }})</view>
			<view class="card">

				<view class="header">
					<text class="column-title">市盈率</text>
					<text class="column-title">现价</text>
					<text class="column-title">市值</text>
					<text class="column-title">股息率</text>
				</view>
				<view class="row">
					<text class="column-value">{{ stockInfo.pe }}</text>
					<text class="column-value">{{ stockInfo.price }}</text>
					<text class="column-value">{{ stockInfo.totalMv }}</text>
					<text class="column-value">{{ }}</text>
				</view>

			</view>
			<view class="card">

				<view class="header">
					<text class="column-title">52周最低</text>
					<text class="column-title">历史最低</text>
					<text class="column-title">历史最高</text>
					<text class="column-title">热度</text>
				</view>
				<view class="row">
					<text class="column-value">{{ stockInfo.low1y }}</text>
					<text class="column-value">{{ stockInfo.low10y }}</text>
					<text class="column-value">100</text>
					<text class="column-value">3.5%</text>
				</view>

			</view>


		</view>



		<!-- 持仓分时 -->
		<view class="holdings">
			<view class="header">
				<text class="column-title">跌幅</text>
				<text class="column-title">股价</text>
				<text class="column-title">均价</text>
				<text class="column-title">总金额/亏损</text>
				<text class="column-title">总买入</text>
				<text class="column-title">PE</text>
			</view>
			<view class="row" v-for="(holding, index) in holdings" :key="index">
				<text class="column-value">-{{ toPercentage(holding.fallRate) }}</text>
				<text class="column-value">{{ holding.currentPrice }}</text>
				<text class="column-value">{{ holding.buyAvgPrice }}</text>
				<text class="column-value">{{ holding.totalLoss }}</text>
				<text class="column-value">{{ holding.totalBuyAmount }}</text>
				<text class="column-value">{{ holding.pe }}</text>
			</view>
		</view>

	</view>
</template>

<script>
	import env from '@/config/env';
	const url = `${env.INVEST_URL}/api/stock/leftBuy`;
	console.log("url", url);

	export default {
		data() {
			return {
				tsCode: '',
				planAmount: '',
				account: {},
				position: {},
				stockInfo: {},
				holdings: []
			};
		},

		onLoad(options) {
			console.log("options", JSON.stringify(options));
			this.tsCode = options.tsCode;
			this.leftBuy();
		},

		methods: {

			toPercentage(value) {
				if (!value) return '0%';
				return (value * 100).toFixed(2) + '%';
			},

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

			async leftBuy() {
				// 调用搜索接口
				try {

					const [err, res] = await this.requestWrapper({
						url: url,
						method: 'POST',
						header: {
							'content-type': 'application/json'
						},
						data: {
							'tsCode': this.tsCode,
							'planAmount': this.planAmount
						},
						complete: (res) => {
							console.log('请求完成:', res); // 打印请求完成的信息
						}
					});

					// 如果有错误，抛出异常
					if (err) {
						console.error('请求失败:', err);
						throw new Error('请求失败');
					}
					// 检查 API 响应是否成功
					if (!res.data || !res.data.success) {
						console.error('API 调用不成功:', res.data?.msg || '未知错误');
						throw new Error(res.data?.msg || '未知错误');
					}
					this.holdings = res.data.data.buyPlanUnits;
					this.accout = res.data.data.account;
					this.position = res.data.data.position;
					this.stockInfo = res.data.data.stockInfo;

				} catch (error) {
					console.error('请求失败:', error.message);
					throw error; // 重新抛出错误以便调用方处理
				}

			},

			addStock() {
				this.navigateToNextPage();

			},
			navigateToNextPage() {
				// 使用uni-app的页面跳转方法
				uni.navigateTo({
					url: '/pages/stock/add' // 替换为目标页面的路径
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

		.account {
			background-color: #f9f9f9;
			border-radius: 10rpx;
			padding: 20rpx;
			margin-bottom: 20rpx;

			.tip {
				font-size: 28rpx;
				color: #333;
				margin-top: 10rpx;
				margin-bottom: 10rpx;
				margin-left: 10rpx;
			}

			.value {
				display: flex;
				align-items: center;
				border-radius: 1rpx;
				margin-top: 10rpx;
				margin-left: 10rpx;
				margin-bottom: 10rpx;
				border-bottom: 1px solid #fcebe3;
				height: 65rpx;
			}

			.value input {
				flex: 1;
				border: none;
				outline: none;
				font-size: 28rpx;
				color: rgba(0, 0, 0, 0.6);
			}
		}

		.stock {
			padding: 20rpx;

			.title {
				font-size: 32rpx;
				font-weight: bold;
				margin-bottom: 20rpx;
			}

			.card {
				background-color: #fff;
				border-radius: 10rpx;
				box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
				margin-bottom: 20rpx;
				padding: 20rpx;

				.header {
					display: flex;
					justify-content: space-between;
					margin-bottom: 10rpx;

					.column-title {
						font-size: 28rpx;
						color: #333;
					}
				}

				.row {
					display: flex;
					justify-content: space-between;

					.column-value {
						font-size: 24rpx;
						color: #666;
					}
				}
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