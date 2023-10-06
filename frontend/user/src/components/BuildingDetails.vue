<template>
  <div>
    <div id="buildingName">
      <h3 style="text-align: center">
        hello world
      </h3>
    </div>

    <div>
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 text="2xl" justify="center">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>

    <BuildingBlog></BuildingBlog>

  </div>

</template>

<script>
import BuildingBlog from './BuildingBlog.vue'
export default {
  name: "buildingDetails",
  mounted() {
    const buildingName = this.$route.params.id;
    console.log(buildingName);
  },
  components: {
    BuildingBlog,
  }
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
</style>