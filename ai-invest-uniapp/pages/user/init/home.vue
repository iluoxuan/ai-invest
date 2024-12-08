<template>
	<view class="container">
		<!-- 总资产、总盈亏、当日参考盈亏 -->
		<view class="account">
			<view class="summary-row">
				<view class="item">
					<text class="title">总现金</text>
					<text class="amount">{{ account.totalAmount }}</text>
				</view>
				<view class="item">
					<text class="title">月支出</text>
					<text class="amount">{{ account.monthlyExpenses }}</text>
				</view>
				<view class="item">
					<text class="title">月收入</text>
					<text class="amount">{{ account.monthlyIncome }}</text>
				</view>
			</view>

			<view class="summary-row">
				<view class="item">
					<text class="title">计划资金</text>
					<text class="amount">{{ account.planAmount }}</text>
				</view>
				<view class="item">
					<text class="title">计划可用</text>
					<text class="amount">{{ account.availableAmount }}</text>
				</view>
				<view class="item">
					<text class="title">投资时长</text>
					<text class="amount">{{ dailyReferencePL }}</text>
				</view>
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
			<view class="row" v-for="(holding, index) in holdings" :key="index">
				<text class="column-value"> {{ holding.stockCnName }}</text>
				<text class="column-value">{{ holding.pe }}</text>
				<text class="column-value">{{ holding.totalMv }}</text>
				<text class="column-value">{{ holding.holdings }}</text>
				<text class="column-value">{{ holding.price }}</text>
			</view>
		</view>

		<view class="action">

			<view class="action-add" @click="addStock('加仓')">
				<view class="action-add-text">
					加仓
				</view>
			</view>

			<view class="action-add" @click="market('市场')">
				<view class="action-add-text">
					市场
				</view>
			</view>

			<view class="action-add" @click="subStock('市场')">
				<view class="action-add-text">
					减仓
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	import env from '@/config/env';	
	const url = `${env.INVEST_URL}/api/account/info`;
	console.log("url", url)
	export default {
		data() {
			return {
				account: {},
				holdings: []
			};
		},

		onLoad() {
			this.getAccountInfo();
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

			async getAccountInfo() {

				try {
					// 调用 uni.request 并等待其完成
					const [err, res] = await this.requestWrapper({
						url: url,
						method: 'POST',
						header: {
							'content-type': 'application/json'
						},
						data: {},
						complete: (res) => {
							console.log('请求完成:', res); // 打印请求完成的信息
						}
					});

					// 如果有错误，抛出异常
					if (err) {
						console.error('获取账户信息失败:', err);
						throw new Error('获取账户信息失败');
					}
					// 检查 API 响应是否成功
					if (!res.data || !res.data.success) {
						console.error('API 调用不成功:', res.data?.msg || '未知错误');
						throw new Error(res.data?.msg || '未知错误');
					}
					this.account = res.data.data.account;
					this.holdings = res.data.data.stockList;

				} catch (error) {
					console.error('投资账户初始化发生错误:', error.message);
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
		
		.account{
			background-color: #ffffff;
		}

		.summary-row {
			display: flex;
			justify-content: space-around;
			margin-top: 50rpx;
			margin-bottom: 20rpx;


			.item {
				display: flex;
				flex-direction: column;
				align-items: center;
				text-align: center;

				.title {
					color: rgba(0, 0, 0, 0.6);
					font-size: 28rpx;
				}

				.amount {
					font-size: 30rpx;
					color: #ea3538;
				}
			}
		}

		.holdings {
			margin-top: 20rpx;
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

		.action {

			display: flex;
			flex-direction: row;
			margin: 25rpx;
			margin-top: 50rpx;

			.action-add {
				margin-left: 50rpx;
				width: 24%;
				height: 150rpx;
				background-color: #fcebe3;
				/* 参考图片中的背景色 */
				border-radius: 15rpx;
				overflow: hidden;
				display: flex;
				align-items: center;
				/* 垂直居中 */
				justify-content: center;

			}

			.action-add-text {
				/* 水平居中 */
				text-align: center;
				box-sizing: border-box;
				color: #dca476;
				/* 参考图片中的文字颜色 */
				line-height: 1.2;
				/* 控制行高 */
				font-weight: bold;
				/* 字体加粗 */
				font-size: 45rpx;
			}

		}
	}
</style>