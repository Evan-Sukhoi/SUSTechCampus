<template>
  <div class="container">


    <div class="detail">
      <div id="sidebar">
        <sideBar></sideBar>
      </div>

      <div id="route">
        <transition name="fade" mode="out-in">
          <router-view></router-view>
        </transition>
      </div>

    </div>


  </div>

</template>

<script>
import sideBar from "@/components/userComponents/BuildingComponents/BuildingSideBar.vue";
export default {
  name: "buildingDetails",
  components: {
    sideBar,
  },
}
</script>

<style scoped>


.detail {
  display: flex;
  flex-direction: row;
}

#sidebar {
  width: 200px;
  position: fixed;
  z-index: 1000;
  top: 40%;
}


#route {
  position: relative;
  left: 5%;
  width: 95%;
}


</style>