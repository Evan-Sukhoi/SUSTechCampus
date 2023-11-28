<template>
  <div class="map_content">
    <div class="busline">
      <div>
        <label for="startPoint">起始点：</label>
        <select v-model="startPoint" id="startPoint">
          <!-- 这里添加起始点的选项 -->
          <option value="a">A</option>
          <option value="b">B</option>
          <!-- 添加更多选项 -->
        </select>
      </div>

      <div>
        <label for="endPoint">目标点：</label>
        <select v-model="endPoint" id="endPoint">
          <!-- 这里添加目标点的选项 -->
          <option value="c">C</option>
          <option value="d">D</option>
          <!-- 添加更多选项 -->
        </select>
      </div>

      <button v-if="startPoint && endPoint" @click="startNavigation">开始导航</button>

    </div>

    <div class="walk">

    </div>

  </div>
</template>

<script>
// import router from "@/router";
export default {
  data() {
    return {
      startPoint: "", endPoint: "",
    }
  },
  methods: {
    startNavigation() {
      // 在这里执行开始导航的操作，可以使用 this.startPoint 和 this.endPoint 的值
      console.log("开始导航：", this.startPoint, "到", this.endPoint);
      // 你可以根据需要进行导航或其他操作
      // 下面是一个示例将用户导航到 /user/building/{startPoint}/to/{endPoint} 的路由
      // router.push(`/user/building/${this.startPoint}/to/${this.endPoint}`).catch(err => err);
    },
  },
};
</script>

<style scoped>

</style>