<template>
	<view class="container">

		<!-- 搜索框 -->
		<view class="search-box">
			<input type="text" placeholder="搜索股票" />
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
				<text class="column-title">市盈率</text>
				<text class="column-title">现价</text>
				<text class="column-title">市值</text>
			</view>
			<view class="row" v-for="(holding, index) in holdings" :key="index">
				<text class="column-value">阿里巴巴{{ holding.marketValue }}</text>
				<text class="column-value">{{ holding.profitLoss }}</text>
				<text class="column-value">{{ holding.holdings }}</text>
				<text class="column-value">{{ holding.costPrice }} / {{ holding.currentPrice }}</text>
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

		.search-box {
			display: flex;
			align-items: center;
			border-radius: 1rpx;
			padding: 10rpx 20rpx;
			margin-top: 10rpx;
			margin-bottom: 10rpx;
			border-bottom: 1px solid #fcebe3;
			height: 65rpx;
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