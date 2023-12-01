<template>
  <div class="intro">

    <div id="buildingName">
      <h3 style="text-align: center">
        {{this.building.name}}
      </h3>
    </div>

    <div id="ppt">
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 text="2xl" justify="center">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>

    <div class="video">
      <div class="video-container">
        <video ref="videoPlayer" controls class="custom-video">
          <source :src="videoUrl" type="video/mp4">
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
import axios from "axios";

export default {
  name: "BuildingIntro",
  data() {
    return {
      buildingId: '',
      building: {},
      videoUrl: 'https://upos-hz-mirrorakam.akamaized.net/upgcxcode/23/59/2035923/2035923_da3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1701344339&gen=playurlv2&os=akam&oi=804486655&trid=cfa3051a95bd4e5c87549b96d3ce978bh&mid=0&platform=html5&upsig=9c12818de46b8bc815f663b0eaf53e26&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&hdnts=exp=1701344339~hmac=84775ae312bf0196ee05be4a85eb92655240159287d125f8bb4f17cabfdb7f7b&bvc=vod&nettype=0&f=h_0_0&bw=50757&logo=80000000'
    }
  },

  created() {
    this.buildingId = this.$route.params.id;
    this.fetchBuildingData(this.buildingId);
  },
  methods: {
    fetchBuildingData(id) {
      axios.get(`http://localhost:8081/public/building/get/details?buildingId=${id}`)
          .then(response => {
            this.building = response.data.data;
            console.log(response.data);
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

.video {
}

.content {
  left: 20px;
}

.video-container {
  max-width: 500px;
  float: left;
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