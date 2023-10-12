<template>
  <div class = adminView>
    <div class="sidebar">
      <AdminSideBar></AdminSideBar>
    </div>

    <div class="route">
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </div>
  </div>
</template>


<script>
import AdminSideBar from "@/components/adminComponents/AdminSideBar.vue";

export default {
  name: "AdminView",
  components: {
    AdminSideBar
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
  left: 20%;
}

.adminView {
  display: flex;
  flex-direction: row;
}
</style>