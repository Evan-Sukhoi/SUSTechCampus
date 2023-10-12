<template>
  <div class="container">
    <div id="buildingName">
      <h3 style="text-align: center">
        hello world
      </h3>
    </div>

    <div id="ppt">
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 text="2xl" justify="center">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>

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
import sideBar from "@/components/SideBar.vue";
export default {
  name: "buildingDetails",
  components: {
    sideBar,
  },
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

.detail {
  display: flex;
  flex-direction: row;
}

#sidebar {
  width: 30%;
}

#route {
  width: 70%;
}


</style>