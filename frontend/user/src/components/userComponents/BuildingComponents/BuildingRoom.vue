<template>
  <div class="background">
    <div v-if="loading">
      <el-container v-loading="true"></el-container>
      <el-dialog :visible="visable" :title="$t('lang.isLogin')" @close="closeDialog"></el-dialog>
    </div>

    <div class="roomList">
      <vs-card :type="card_type" v-for="room in roomInfo" :key="room.roomTypeId" class="box-card">
        <template #title>
          <h3>{{ room.roomTypeName }}</h3>+
        </template>
        <template #img>
          <img :src="room.roomImageUrls[0]" style="width: 1000px;">
        </template>
        <template #text>
          <p>
            {{ $t('lang.capacity') + room.capacity }}
          </p>
          <p>{{ room.description }}</p>
        </template>
      </vs-card>

    </div>
  </div>
</template>
<script>
export default {
  name: "BuildingRoom",
  data() {
    return {
      roomInfo: [],
      loading: false,
      visable: false,
      card_type:3,
    }
  },
  beforeMount() {
    this.loading = true
    if (localStorage.getItem('isLogin')) {
      this.loading = false
      this.fetchRoomData(this.$route.params.id);
    } else {
      this.visable = true
    }

  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
  },
  mounted() {
    window.addEventListener('resize', this.handleResize)
    this.handleResize()
  },
  methods: {
    handleResize() {
      const isSmallScreen = window.matchMedia('(max-width: 768px)').matches;
      if (isSmallScreen){
        this.card_type = 2
      }
    },
    fetchRoomData(id) {
      this.$http.get(`user/room/get/building?buildingId=${id}`)
          .then(response => {
            this.roomInfo = response.data;
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    },
    closeDialog() {
      this.visable = false
    },
  }

}
</script>

<style scoped>
.roomList {
  //top: 150px;
  position: relative;
  max-width: 1000px;
  background-color: rgba(244, 209, 204, 0.5);
  border-radius: 20px;
  margin: auto;
}

.background {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  position: relative;
  height: 100%;
  overflow: auto;
  overflow-x: hidden;
}

.box-card {
  margin-bottom: 10px;
  height: 220px;
  width: 500px;
  border-radius: 20px;
}

.card-img {
  margin-left: 10px;
  top: 10px;
}
@media screen and (max-width: 768px) {
  .roomList{
    width: 77.3%;
    display: block;
    justify-content: center;
  }
  .box-card{
    margin-top: 80px;
  }
}
</style>