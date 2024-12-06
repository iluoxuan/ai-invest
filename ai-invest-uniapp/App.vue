<script setup>
import { onLaunch } from '@dcloudio/uni-app';
import env from '@/config/env';

const userInfoUrl = `${env.INVEST_URL}/api/user/info`;

// 获取小程序胶囊按钮的位置并存储
function storeButtonBound() {
  try {
    const buttonBound = uni.getStorageSync('buttonBound');
    if (!buttonBound) {
      const res = uni.getMenuButtonBoundingClientRect();
      console.log(res);
      uni.setStorageSync('buttonBound', JSON.stringify(res));
    }
  } catch (error) {
    console.error('获取胶囊按钮位置失败:', error);
  }
}

// 检查用户信息并决定跳转到哪个页面
async function checkUserAndNavigate() {
  try {
    const [error, res] = await uni.request({
      url: userInfoUrl,
      method: 'POST'
    });

    if (error || res.statusCode !== 200 || !res.data.success) {
      console.error('获取用户信息失败:', res || error);
      // 错误处理逻辑，默认跳转到首页
      uni.reLaunch({
        url: '/pages/user/init/home'
      });
      return;
    }

    const userInfo = res.data;
    // 存储用户信息到本地存储
    uni.setStorageSync('userInfo', JSON.stringify(userInfo));

    // 根据用户信息中的 hadInitAccount 值决定跳转到哪个页面
    const shouldShowGuide = userInfo.hadInitAccount;
    const targetPage = shouldShowGuide ? '/pages/user/init/home' : '/pages/user/welcome/step1';

    // 使用 reLaunch 跳转到目标页面
    uni.reLaunch({
      url: targetPage
    });
  } catch (error) {
    console.error('请求失败:', error);
    // 请求失败时的处理，默认跳转到首页
    uni.reLaunch({
      url: '/pages/user/init/home'
    });
  }
}

// 应用启动时的初始化逻辑
onLaunch(async () => {
  storeButtonBound();
  await checkUserAndNavigate();
});
</script>

<style>
/* 每个页面公共 css */
</style>