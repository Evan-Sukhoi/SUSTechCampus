<template>
  <div class="back">
    <div class="sidebar">
      <PersonalPageSidebar></PersonalPageSidebar>
    </div>

    <div class="route">
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </div>
  </div>
</template>

<script>
import PersonalPageSidebar from "@/components/userComponents/PersonalPageComponents/PersonalPageSidebar";
export default {
  name: "PersonalPageView",
  components:{
    PersonalPageSidebar
  }
}
</script>

<style scoped>
.back{
  display: flex;
  height: 100%;
}
.sidebar{
  width: 20%;
}
.route{
  width: 80%;
}
</style>