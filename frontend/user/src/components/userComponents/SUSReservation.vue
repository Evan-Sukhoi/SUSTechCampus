<template>
  <!--TODO-->
  <div id="background">
    <div>
      <el-carousel :interval="5000" arrow="always">
        <el-carousel-item v-for="building in buildingType" :key="building.id">
          <img :src="building.img" style="width: 100%;">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div v-for="building in buildingType" :key="building.id" class="box">
      <div id="left-box">
        <h1 class="text">{{building.name}}</h1>
      </div>
      <div id="right-box">
        <div v-for="reservation in Infos" :key="reservation.id" class="column">
          <router-link :to="`/user/reservation/${reservation.id}`">
            <vs-card>
              <template #title>
                <h3>{{ reservation.name }}</h3>
              </template>
              <template #img>
                <img :src=reservation.img alt="">
              </template>
              <template #text>
                <p>
                  {{ reservation.describe }}
                </p>
              </template>
              <template #interactions>
                <vs-button danger icon>
                  <i class='bx bx-heart'></i>
                </vs-button>
                <vs-button class="btn-chat" shadow primary>
                  <i class='bx bx-chat' ></i>
                  <span class="span">
          54
        </span>
                </vs-button>
              </template>
            </vs-card>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SUSAppointment",
  data(){
    return{
      buildingType:[
        {id:1, name: this.$t('lang.canteen'), img: require('../../assets/pad(canDelete)/building/img1.png')},
        {id:2, name: this.$t('lang.library'), img: require('../../assets/pad(canDelete)/building/img2.png')},
        {id:3, name: this.$t('lang.playGround'), img: require('../../assets/pad(canDelete)/building/img3.png')},
        {id:4, name: this.$t('lang.teachingBuilding'), img: require('../../assets/pad(canDelete)/building/img4.png')}
      ],
      Infos:[
        {id: 1, img: "https://www.dmoe.cc/random.php", name: "building 1", describe: "haha"},
        {id: 2, img: "https://cdn.seovx.com/d/?mom=302", name: "building 2", describe: "lala"},
        {id: 3, img: "http://www.98qy.com/sjbz/api.php", name: "building 3", describe: "guagua"}
      ]
    }
  }
}
</script>

<style scoped>
.column{
  float: left;
  width: 50%;
  margin-top: 10px;
}
.box{
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 20px;
  margin-top: 100px;
  margin-right: 10%;
  margin-left: 10%;
  height: 618px;
}
.text{
  clear: both;
  word-break: break-all;
}
#left-box{
  float: left;
  width: 12%;
}
#right-box{
  float: right;
  width: 88%;
}
#background{
  background-image: url("../../assets/pad(canDelete)/background/预约背景.png");
  background-repeat: no-repeat;
  background-size: cover;
}
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
  }
  .box{
    background-color: #99a9bf;
    border-radius: 20px;
    margin: 10%;
    height: 934px;
  }
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

</style>