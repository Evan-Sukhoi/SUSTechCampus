<template>
  <div id="post">
    <!-- 左侧部分，显示评论 -->
    <div id="comment">
      <div v-for="comment in comments" :key="comment.id" class="comment">
        <div class="comment-text">{{ comment.text }}</div>
        <img v-if="comment.image" :src="comment.image" alt="Comment Image">
      </div>
    </div>

    <!-- 右侧部分，上下分布 -->
    <div id="right-column">
      <!-- 上部分，添加评论 -->
      <div id="addcomment">
        <h2>添加评论</h2>
        <form @submit.prevent="addComment">

          <label for="comment">评论:</label>
          <textarea v-model="newComment.text" required></textarea>

          <!-- 修改这行 -->
          <input type="file" :key="key" @change="handleImageUpload">

          <button type="submit">发表评论</button>
        </form>
      </div>

      <!-- 下部分，评论预览 -->
      <div class="preview">
        <h3>评论预览</h3>
        <div class="comment-text">{{ newComment.text }}</div>
        <div id="image">
          <img v-if="newComment.image" :src="URL.createObjectURL(newComment.image)" alt="Comment Image" width="300px">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import qs from 'qs';

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
      axios.get(`http://localhost:8081/user/comment/getbybuildingid?buildingId=${id}`)
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
#post {
  display: grid;
  grid-template-columns: 1fr 1fr;
  height: 100vh;
}

#comment {
  overflow-y: auto;
}

.comment {
  margin-bottom: 20px;
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 8px;
}

.user {
  font-weight: bold;
}

.comment-text {
  margin-top: 10px;
}

#right-column {
  display: grid;
  grid-template-rows: 1fr 1fr; /* 上下两行，平均分布 */
}

#addcomment {
  grid-row: 1; /* 显示在第一行 */
}

.preview {
  grid-row: 2; /* 显示在第二行 */
  margin-top: 20px;
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 8px;
}

</style>
