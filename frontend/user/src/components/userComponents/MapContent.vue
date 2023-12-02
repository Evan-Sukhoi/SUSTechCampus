<template>
  <div class="map_content">
    <p>{{buildingId}}</p>
    {{this.building.name}}
    {{this.building.introduction}}
    <button @click="handleLink(buildingId)">see more details</button>
  </div>
</template>

<script>
import router from "@/router";
export default {
  data() {
    return {
      building: {},
    }
  },
  props: {
    buildingId: Number,

  },
  mounted() {
    this.fetchBuildingData();
  },
  methods: {
    handleLink(e) {
      console.log(e)
      router.push(
          '/user/building/' + e +'/intro'
      ).catch(err => err)
    },

    fetchBuildingData() {
      this.$http.get(`/public/building/get/simpleThroughId?buildingId=${this.buildingId}`)
          .then(response => {
            this.building = response.data.data;
            console.log(response.data.data);
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    }
  }
};
</script>

<style scoped>
.map_content {
  width: 200px;
  height: 200px;
}
</style>