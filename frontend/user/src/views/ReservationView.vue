<template>
  <div class="background">
    <transition :name="transitionName" mode="out-in">
      <router-view @beforeRouteUpdate="handleRouteChange" />
    </transition>
  </div>
</template>

<script>

export default {
  name: "ReservationView",
  components:{

  },
  data() {
    return {
      transitionName: 'slide-left',
    };
  },
  watch: {
    $route(to, from) {
      // 根据路由的变化方向设置动画
      this.transitionName = to.path.startsWith(from.path) ? 'slide-left' : 'slide-right';
    },
  },
  methods: {
    handleRouteChange(to, from, next) {
      // 在路由更新前可能需要做的处理
      next();
    },
  },
}
</script>

<style scoped>
.slide-left-enter-active, .slide-right-enter-active,
.slide-left-leave-active, .slide-right-leave-active {
  position: absolute;
  width: 100%;
  transition: transform 0.3s ease-in-out;
}

.slide-left-enter, .slide-right-leave-to {
  transform: translateX(100%);
}

.slide-right-enter, .slide-left-leave-to {
  transform: translateX(-100%);
}

/* 让新旧组件绝对定位在同一位置 */
.router-view-enter-active, .router-view-leave-active {
  position: absolute;
  width: 100%;
}
.background{
  background-image: url("../assets/pad(canDelete)/background/keli.png");
  background-size: cover;
  width: 100%;
  height: 100%;
  position: relative;
}
</style>