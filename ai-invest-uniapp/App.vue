<script setup>
	// 适用vue3 组合式api语法
	import {onLaunch} from '@dcloudio/uni-app'
	onLaunch(()=>{
		// 获取[小程序]胶囊按钮的位置
		const buttonBound = uni.getStorageInfoSync('buttonBound');
		if(!buttonBound){
			const res = uni.getMenuButtonBoundingClientRect();
			console.log(res);
			uni.setStorageSync('buttonBound', res);
		}
	
	})
	
</script>

<style>
	/*每个页面公共css */
</style>