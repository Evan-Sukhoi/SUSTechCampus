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

        <div class="form">
          <form @submit.prevent="addComment">
            <el-input
                type="textarea"
                :rows="7"
                placeholder="请输入内容"
                v-model="newComment.text" required>
            </el-input>
            <div id="image">
              <img v-if="newComment.image" :src="URL.createObjectURL(newComment.image)" alt="Comment Image" width="200px">
            </div>
            <input type="file" :key="key" @change="handleImageUpload">
            <button type="submit">发表评论</button>
          </form>
        </div>


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
      key: 0,
      URL: window.URL || window.webkitURL
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

    addComment() {
      if (this.newComment.text) {
        const newComment = {
          text: this.newComment.text,
          image: this.newComment.image,
          date: this.formatDate(new Date()),
        };

        this.handleCommentUpload(newComment);

        this.newComment.text = '';
        this.newComment.image = null;
        this.key += 1;
      }
    },

    handleImageUpload(event) {
      this.newComment.image = event.target.files[0];
      this.key += 1;
    },

    handleCommentUpload(newComment) {
      // 创建FormData对象
      const formData = new FormData();

      // 添加文件到FormData
      formData.append('image', newComment.image);

      // const image = qs.stringify(formData);

      // 发送POST请求
      axios.post(`http://localhost:8081/fileupload/upload`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data', // 设置请求头，告诉服务器发送的是 FormData 数据
        },
      })
          .then(response => {
            console.log(response.data); // 处理上传成功的响应
            // 其他处理逻辑...
          })
          .catch(error => {
            console.error('上传失败:', error);
          });
    },


    fetchCommentData(id) {
      axios.get(`http://localhost:8081/public/comment/get/approved?buildingId=${id}`)
          .then(response => {
            this.comments = response.data;
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
