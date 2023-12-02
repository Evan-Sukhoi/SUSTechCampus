<template>
  <div class="post">
    <el-scrollbar style="height: 100%" class="scrollbar-for" wrap-style="overflow-x:hidden;">
      <div id="comment">
        <div v-for="comment in comments" :key="comment.id" class="comment">
          <div>{{ comment.username }}</div>
          <el-image :src="comment.userImageUrl" alt="User Image"/>
          <div>{{ comment.time }}</div>
          <div class="comment-text">{{ comment.text }}</div>
          <div>{{comment.adminId}}</div>
        </div>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'CommentManage',
  data() {
    return {
      comments: [],
    }
  },
  created() {
    this.fetchCommentData();
  },
  methods: {
    fetchCommentData() {
      axios.get(`http://localhost:8081/admin/comment/all`,)
          .then(response => {
            this.comments = response.data.data;
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    },
  }
}
</script>

<style scoped>
.post {
  width: 40%;
  height: 100%;
}
</style>