<template>
  <div class="intro">

    <div id="buildingName">
      <h3 style="text-align: center">
        {{this.building.name}}
      </h3>
    </div>

    <div id="ppt">
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="(url, index) in building.imageUrl" :key="index">
          <el-image :src=url alt="Photo" style="width: 100%; height: 100%;"></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>

    <div class="board">
      <div class="video-container" v-if="videoUrl !== ''">
        <video ref="videoPlayer" controls class="custom-video">
          <source :src="this.videoUrl" type="video/mp4">
          Your browser does not support the video tag.
        </video>
      </div>

      <div class="content">
        {{this.building.introduction}}
        {{this.building.openTime}}
        {{this.building.closeTime}}
      </div>
    </div>
  </div>
</template>

<script>
import buildingVideo from "@/components/userComponents/BuildingComponents/BuildingVideo.vue";
export default {
  name: "BuildingIntro",
  data() {
    return {
      buildingId: '',
      building: {},
      videoUrl: '',
   }
  },
  components: {
    buildingVideo,
  },

  created() {
    this.buildingId = this.$route.params.id;
    this.fetchBuildingData(this.buildingId);
  },
  methods: {
    fetchBuildingData(id) {
      this.$http.get(`/public/building/get/details?buildingId=${id}`)
          .then(response => {
            this.building = response.data;
            console.log(response.data);
            this.videoUrl = this.building.videoUrl
            console.log(this.videoUrl)
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    },
  },

}

</script>

<style scoped>
.intro {
  display: block;

}

.content {
  margin-top: 50px;
  margin-left: 10%;
  width: 40%;
  height: 80%;
  background-color: white;
  border-radius: 20px;
  overflow: auto;
}


.content {
  left: 20px;
}

.video-container {
  max-width: 500px;
  float: left;
  margin: 0 auto;
}


.custom-video {
  width: 100%;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.custom-video {
  width: 100%;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

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

.intro {
  position: relative;
  width: 100%;
}
</style>