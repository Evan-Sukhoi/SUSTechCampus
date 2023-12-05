<template>
  <div class="container" :style="{ backgroundImage: `${this.img}`}">


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
  data() {
    return {
      img: '',
    }
  },
  components: {
    sideBar,
  },
  beforeMount() {
    this.fetchBuildingData(this.$route.params.id)
  },
  methods: {
    fetchBuildingData(id) {
      this.$http.get(`/public/building/get/simpleThroughId?buildingId=${id}`)
          .then(response => {
            this.img = response.data.data.coverUrl;
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    }
  },
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  overflow: auto;
  overflow-x: hidden;
  //background-image: ;
  background-repeat: no-repeat;
  background-size: cover;
}

.detail {
  display: flex;
  flex-direction: row;
  height: 100%;
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
  height: 100%;
}

.container{
  height: 100%;
}


</style>