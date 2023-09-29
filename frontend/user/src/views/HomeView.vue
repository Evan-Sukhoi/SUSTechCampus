<template>
  <div class="home">
    <TopBar></TopBar>
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
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
<style>
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>