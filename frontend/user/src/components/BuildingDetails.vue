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

    <div id="detail">
      <div class="sidebar">
        <vs-sidebar
            relative
            hover-expand
            reduce
            v-model="active"
            open
        >
          <vs-sidebar-item id="home">
            <template #icon>
              <i class='bx bx-home'></i>
            </template>
            Home
          </vs-sidebar-item>

          <vs-sidebar-item id="market">
            <template #icon>
              <i class='bx bx-grid-alt'></i>
            </template>
            Market Overview
          </vs-sidebar-item>

          <vs-sidebar-item id="Music">
            <template #icon>
              <i class='bx bxs-music'></i>
            </template>
            Music
          </vs-sidebar-item>

          <vs-sidebar-item id="donate">
            <template #icon>
              <i class='bx bxs-donate-heart' ></i>
            </template>
            Donate
          </vs-sidebar-item>

          <vs-sidebar-item id="drink">
            <template #icon>
              <i class='bx bx-drink'></i>
            </template>
            Drink
          </vs-sidebar-item>

          <vs-sidebar-item id="shopping">
            <template #icon>
              <i class='bx bxs-shopping-bags'></i>
            </template>
            Shopping
          </vs-sidebar-item>
        </vs-sidebar>
      </div>
    </div>

  </div>

</template>

<script>
export default {
  name: "buildingDetails",
  components: {
  },
  data:() => ({
    active: 'home',
  })
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

.container {
  display: flex;
  flex-direction: column;
}






</style>