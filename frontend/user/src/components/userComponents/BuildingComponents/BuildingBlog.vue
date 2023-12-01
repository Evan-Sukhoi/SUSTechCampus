<template>

  <div class="blog">
        <div class="post">
          <div id="comment">
            <div v-for="comment in comments" :key="comment.id" class="comment">
              <div class="comment-text">{{ comment.text }}</div>
              <img v-if="comment.image" :src="comment.image" alt="Comment Image">
            </div>
          </div>
        </div>

    <form @submit.prevent="submitComment">
      <label>
        评论内容:
        <textarea v-model="newComment.text"></textarea>
      </label>

      <label>
        上传照片:
        <input type="file" multiple @change="handleFileChange" />
      </label>

      <div v-if="this.photos.length > 0">
        <h3>已选择的照片</h3>
        <div v-for="(photo, index) in this.photos" :key="index" class="selected-photo">
          <img :src="getPhotoUrl(photo)" alt="Selected Photo" width="200px"/>
          <button @click="removePhoto(index)">删除</button>
        </div>
      </div>

      <button type="submit">提交评论</button>
    </form>


    <!-- 左侧部分，显示评论 -->
  </div>

</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      newComment: {},
      comments: [],
      photos: [],
    }
  },
  created() {
    this.buildingId = this.$route.params.id;
    this.fetchCommentData(this.buildingId);
  },
  methods: {
    formatDate(date) {
      const options = {year: 'numeric', month: 'long', day: 'numeric'};
      return new Date(date).toLocaleDateString(undefined, options);
    },

    submitComment() {
      // 发送评论数据到服务器
      // 使用 this.comment.text 和 this.comment.photos
      // 使用 axios 或其他 HTTP 库发送请求
    },
    handleFileChange(event) {
      const files = event.target.files;
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        // 可以进行一些文件验证，例如文件类型、大小等
        // 简单示例，直接将文件加入数组
        this.photos.push(file);
      }
    },
    removePhoto(index) {
      // 从数组中删除选中的照片
      this.photos.splice(index, 1);
    },
    getPhotoUrl(photo) {
      // 根据文件对象创建一个临时的 URL，用于预览图片
      return URL.createObjectURL(photo);
    },




    fetchCommentData(id) {
      axios.get(`http://localhost:8081/public/comment/get/approved?buildingId=${id}`, )
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

  },
};
</script>

<style scoped>
.blog {
  display: flex;
  flex-direction: row;
}

.post {
  border: cadetblue 1px solid;
  width: 40%;
}





</style>
