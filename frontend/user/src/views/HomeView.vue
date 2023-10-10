<template>
  <div class="home">
    <div class="topbar">
      <TopBar></TopBar>
    </div>

    <div class="route">
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </div>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";

export default {
  name: 'HomeView',
  components: {
    TopBar
  }
}
</script>
<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

.route {
  position: absolute;
  width: 100%;
  top: 10%;
}

.topbar {
  position: fixed;
  z-index: 1000;
  width: 100%;
  top: 0;
}
</style>