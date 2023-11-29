<template>
  <div class="map_content">
    <p>{{buildingId}}</p>
    <button @click="handleLink(buildingId)">see more details</button>
  </div>
</template>

<script>
import router from "@/router";
export default {
  props: {
    buildingId: String,
  },
  methods: {
    handleLink(e) {
      console.log(e)
      router.push(
          '/user/building/' + e +'/intro'
      ).catch(err => err)
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