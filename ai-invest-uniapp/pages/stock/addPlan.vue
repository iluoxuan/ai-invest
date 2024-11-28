<template>
	<view class="container">

		<view class="account">
			<view class="tip">您当前账户剩余资金10W</view>
			<view class="tip">AI综合推荐计划加仓金额8W</view>
			<view class="value">
				<input type="text" placeholder="输入计划加仓金额" />
			</view>

		</view>

		<view class="stock">

			<view class="title">阿里巴巴(1688)</view>
			<view class="card">

				<view class="header">
					<text class="column-title">市盈率</text>
					<text class="column-title">现价</text>
					<text class="column-title">市值</text>
					<text class="column-title">股息率</text>
				</view>
				<view class="row">
					<text class="column-value">16</text>
					<text class="column-value">80</text>
					<text class="column-value">1.5万亿</text>
					<text class="column-value">3.5%</text>
				</view>

			</view>
			<view class="card">

				<view class="header">
					<text class="column-title">52周最低</text>
					<text class="column-title">历史最低</text>
					<text class="column-title">历史最高</text>
					<text class="column-title">当前热度</text>
				</view>
				<view class="row">
					<text class="column-value">16</text>
					<text class="column-value">80</text>
					<text class="column-value">1.5万亿</text>
					<text class="column-value">3.5%</text>
				</view>

			</view>


		</view>



		<!-- 持仓分时 -->
		<view class="holdings">
			<view class="header">
				<text class="column-title">跌幅</text>
				<text class="column-title">总亏损</text>
				<text class="column-title">股价</text>
				<text class="column-title">PE</text>
				<text class="column-title">市值</text>
				<text class="column-title">总加金额</text>
			</view>
			<view class="row" v-for="(holding, index) in holdings" :key="index">
				<text class="column-value">-1%</text>
				<text class="column-value">{{ holding.profitLoss }}</text>
				<text class="column-value">{{ holding.profitLoss }}</text>
				<text class="column-value">{{ holding.holdings }}</text>
				<text class="column-value">{{ holding.costPrice }}</text>
				<text class="column-value">{{ holding.holdings }}</text>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				totalAssets: "1,075,244.75",
				totalProfitLoss: "-553.51",
				dailyReferencePL: "653.00",
				marketValue: "1,032,932.65",
				availableBalance: "42,262.10",
				withdrawableBalance: "42,262.10",
				holdings: [{
						marketValue: "1,000,000.00",
						profitLoss: "0.00%",
						holdings: "10000",
						available: "0",
						costPrice: "100.000",
						currentPrice: "100.000"
					},
					{
						marketValue: "23,814.45",
						profitLoss: "2.140%",
						holdings: "300",
						available: "300",
						costPrice: "HK$83.216",
						currentPrice: "HK$85.000"
					},
					{
						marketValue: "6,379.20",
						profitLoss: "0.250%",
						holdings: "1600",
						available: "1600",
						costPrice: "3.977",
						currentPrice: "3.987"
					},
					{
						marketValue: "2,739.00",
						profitLoss: "1.410%",
						holdings: "100",
						available: "100",
						costPrice: "27.010",
						currentPrice: "27.390"
					}
				]
			};
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