<template>
  <!--TODO-->
  <div v-if="loading">
    <el-container v-loading="true"></el-container>
    <el-dialog :visible="visable" :title="$t('lang.isLogin')" @close="closeDialog"></el-dialog>
  </div>
  <div id="background" v-else>
    <div>
      <el-carousel :interval="5000" arrow="always">
        <el-carousel-item v-for="building in buildingType" :key="building.id">
          <img :src="building.img" style="width: 100%;">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="filter">
      <el-scrollbar style="height: 100%" class="scrollbar-for" wrap-style="overflow-x:hidden;">
        <h1>{{ $t('lang.filter') }}</h1>
        <el-form label-position="left" label-width="80px">
          <el-form-item :label="$t('lang.filter_buildingName')" style="width: 400px;">
            <el-input v-model="condition.name"></el-input>
          </el-form-item>
          <el-form-item :label="$t('lang.filter_Reservable')" style="width: 400px;">
            <el-checkbox v-model="condition.viewReservable"></el-checkbox>
          </el-form-item>
        </el-form>
        <vs-button
            warn
            gradient
            @click="filter"
            style="margin: auto"
        >{{ $t('lang.filter_sure') }}
        </vs-button>
      </el-scrollbar>
    </div>
    <div v-for="(value,key) in buildingShow" :key="key" class="box">
      <div id="left-box">
        <h1 class="text">{{ key }}</h1>
      </div>
      <div id="right-box">
        <div v-for="(reservation,j) in value" :key="j" class="column">
          <div
              @click="goto(key, reservation.buildingId, reservation.name, reservation.isReservable)">
            <router-link :to="``">
              <vs-card>
                <template #title>
                  <h3>{{ reservation.name }}</h3>
                </template>
                <template #img>
                  <img :src=reservation.coverUrl alt="" v-if="reservation.isReservable">
                  <img :src="reservation.coverUrl" alt="" v-else
                       style="filter: gray;-webkit-filter: grayscale(1);filter: grayscale(1)">
                </template>
                <template #text>
                  <p>
                    {{ reservation.introduction }}
                  </p>
                </template>
                <template #interactions>
                  <vs-button danger icon>
                    <i class='bx bx-heart'></i>
                  </vs-button>
                  <vs-button class="btn-chat" shadow primary>
                    <i class='bx bx-chat'></i>
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
  </div>
</template>

<script>
export default {
  name: "SUSAppointment",
  data() {
    return {
      loading: false,
      visable: false,
      buildingType: [
        {
          id: 1,
          name: this.$t('lang.canteen'),
          img: require('../../assets/pad(canDelete)/building/img1.png'),
          time: ['8:00', '19:00']
        },
        {
          id: 2,
          name: this.$t('lang.library'),
          img: require('../../assets/pad(canDelete)/building/img2.png'),
          time: ['6:00', '24:00']
        },
        {
          id: 3,
          name: this.$t('lang.playGround'),
          img: require('../../assets/pad(canDelete)/building/img3.png'),
          time: ['10:00', '22:00']
        },
        {
          id: 4,
          name: this.$t('lang.teachingBuilding'),
          img: require('../../assets/pad(canDelete)/building/img4.png'),
          time: ['8:00', '22:00']
        }
      ],
      Infos: [
        {id: 1, img: "https://www.dmoe.cc/random.php", name: "building 1", describe: "haha"},
        {id: 2, img: "https://cdn.seovx.com/d/?mom=302", name: "building 2", describe: "lala"},
        {id: 3, img: "http://www.98qy.com/sjbz/api.php", name: "building 3", describe: "guagua"}
      ],
      condition: {
        name: '',
        open_time: ['00:00', '24:00'],
        viewReservable: false
      },
      buildingShow: ''
    }
  },
  beforeMount() {
    this.loading = true
    if (localStorage.getItem('isLogin')) {
      this.loading = false
    } else {
      this.visable = true
    }
    this.$http({
      method: 'get',
      url: 'public/building/get/simple',
      headers: {
        'content-type': 'application/json'
      },
    }).then(resp => {
      if (resp.status === 200) {
        this.Infos = this.dealData(resp.data.data)
        this.buildingShow = this.Infos
        console.log(this.Infos)
      }
      console.log(resp)
    }).catch(err => err)
  },
  methods: {
    dealData(data) {
      var dict = {}
      for (let i = 0; i < data.length; i++) {
        if (!(data[i].buildingType in dict)) {
          dict[data[i].buildingType] = []
        }
        dict[data[i].buildingType].push(data[i])
      }
      return dict
    },
    goto(buildingType, buildingId, buildingName, isReservable) {
      if (isReservable) {
        this.$router.push(
            {path: "/user/reservation/" + buildingType + "/" + buildingId + buildingName})
      } else {
        this.$vs.notification({
          color: 'danger',
          position: 'top-center',
          title: this.$t('lang.errorReserve'),
          text: '',
        })
      }

    },
    closeDialog() {
      this.visable = false
    },
    filter() {
      var newBuildingType = {}
      for (var i in this.Infos) {
        if (i === this.condition.name || this.condition.name === '') {
          var building = []
          for (let j = 0; j < this.Infos[i].length; j++) {
            if (this.condition.viewReservable) {
              if (this.Infos[i][j].isReservable) {
                building.push(this.Infos[i][j])
              }
            } else {
              building.push(this.Infos[i][j])
            }
          }
          if (building.length !== 0){
            newBuildingType[i] = building
          }
        }
      }
      this.buildingShow = newBuildingType
    }
  }
}
</script>

<style scoped>
.column {
  float: left;
  width: 50%;
  margin-top: 10px;
}

.box {
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 20px;
  margin-top: 100px;
  margin-right: 10%;
  margin-left: 10%;
  height: 618px;
  overflow: auto;
}

.filter {
  background-color: rgba(255, 255, 255, 0.7);
  width: 500px;
  height: 200px;
  position: relative;
  margin: auto;
  text-align: center;
  border-radius: 20px;
}

.text {
  clear: both;
  word-break: break-all;
}

#left-box {
  float: left;
  width: 12%;
}

#right-box {
  float: right;
  width: 88%;
}

#background {
  background-image: url("../../assets/pad(canDelete)/background/预约背景.png");
  background-repeat: no-repeat;
  background-size: cover;
}

@media screen and (max-width: 600px) {
  .column {
    width: 100%;
  }

  .box {
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