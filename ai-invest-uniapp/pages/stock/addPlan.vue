<template>
	<view class="container">

		<view class="account">

			<view class="info-box">
				<view class="info-item">
					<text class="info-label">账户剩余资金： </text>
					<text class="info-value">{{ position.availableAmount }}</text>
				</view>
				<view class="info-item">
					<text class="info-label">推荐加仓金额： </text>
					<text class="info-value">{{ position.planAmount }}</text>
				</view>
			</view>
			<view class="value">
				<input type="text" placeholder="调整加仓金额" v-model="planAmount" @blur="leftBuy" />
			</view>

		</view>

		<view class="stock">

			<view class="title">{{ stockInfo.cnName }}({{ stockInfo.symbol }})</view>
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
				<text class="column-title">总跌幅</text>
				<text class="column-title">当前股价 \n 买入均价</text>
				<text class="column-title">总买入\n总亏损</text>
				<text class="column-title">PE\n市值</text>
			</view>
			<view class="row" v-for="(holding, index) in holdings" :key="index">
				<text class="column-value total-loss-container">
					<text class="total-loss-green">-{{ toPercentage(holding.fallRate) }}</text>
				</text>
				<text class="column-value">{{ holding.currentPrice }} \n {{ holding.buyAvgPrice }}</text>
				<text class="column-value total-loss-container">
					<text>{{ holding.totalBuyAmount }}</text><br />
					<text class="total-loss-green">{{ holding.totalLoss }}</text>
				</text>
				<text class="column-value">{{ holding.pe }} \n {{ holding.currentTotalMv }}</text>
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
				return (value * 100).toFixed(0) + '%';
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
		background-color: #ffffff;
		height: 100vh;

		.account {
			background-color: #ffffff;
			border-radius: 10rpx;
			padding: 10rpx;
			margin-bottom: 20rpx;

			.info-box {
			    padding: 10rpx;
			    background-color: #fcebe3;
			  }
			
			  .info-item {
			    display: flex;
			    justify-content: flex-start;
			    align-items: center;
			    padding: 10rpx 0;
			  }
			  .info-label {
			    font-size: 28rpx;
			    color: #333;
			  }
			
			  .info-value {
			    font-size: 28rpx;
			    color: #ee2e0e;
			    font-weight: bold;
			  }

			.value {
				display: flex;
				align-items: center;
				border-radius: 1rpx;
				margin-top: 20rpx;
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
			padding: 10rpx;

			.title {
				font-size: 32rpx;
				font-weight: bold;
				margin-bottom: 20rpx;
				font-family: 'Microsoft YaHei', sans-serif;
			}

			.card {
				background-color: #fff;
				border-radius: 5rpx;
				box-shadow: 0 1rpx 4rpx rgba(0, 0, 0, 0.1);
				margin-bottom: 1rpx;
				padding: 20rpx;

				.header {
					display: flex;
					justify-content: space-between;
					text-align: center;
					align-items: center;
					margin-bottom: 10rpx;

					.column-title {
						flex: 1;
						/* 让每个标题占据相等的空间 */
						font-size: 26rpx;
						color: #8c8488;
						min-width: 80rpx;
						/* 设置最小宽度，防止内容过短时占位不足 */
					}
				}

				.row {
					display: flex;
					justify-content: space-between;
					text-align: center;
					align-items: center;

					.column-value {
						flex: 1;
						/* 让每个值占据相等的空间 */
						font-size: 26rpx;
						color: #181928;
						min-width: 80rpx;
						/* 设置最小宽度，防止内容过短时占位不足 */
					}
				}
			}
		}

		.holdings {
			margin: 10rpx;
			margin-bottom: 25rpx;
			background-color: #ffffff;

			.header {
				display: flex;
				justify-content: space-between;
				background-color: #fcebe3;
				padding: 10rpx 2rpx;

				.column-title {
					flex: 1;
					text-align: center;
					font-size: 26rpx;
					color: #8c8488;
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
					color: #181928;
					font-family: 'Microsoft YaHei', sans-serif;
				}

				.total-loss-container {
					text-align: center;
					font-size: 28rpx;
				}

				.total-loss-green {
					color: green;
					display: block;
					/* 确保换行生效 */
				}



				&:last-child {
					box-shadow: none;
					/* 隐藏最后一个列表项的细线 */
				}
			}
		}

	}
</style>