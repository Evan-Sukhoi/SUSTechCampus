<template>
  <div id="post">
    <!-- 左侧部分，显示评论 -->
    <div id="comment">
      <div v-for="comment in comments" :key="comment.id" class="comment">
        <div class="user">{{ comment.user }}</div>
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
          <label for="username">用户名:</label>
          <input type="text" v-model="newComment.user" required>

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
        <div class="user">{{ newComment.user }}</div>
        <div class="comment-text">{{ newComment.text }}</div>
        <div id="image">
          <img v-if="newComment.image" :src="newComment.image" alt="Comment Image" width="300px">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // comments: [
      //   {
      //     user: "jjjj",
      //     date: "2023/09/11",
      //     text: "ni hao kdifjsidfnisdnfdnfidf ldkfodfk diofjidfji dfsjdfoj",
      //     id: 1,
      //     image: require("@/assets/pad(canDelete)/background/keli.png")
      //   },
      //   {
      //     user: "jjjj",
      //     date: "2023/09/11",
      //     text: "ni hao dfsd  dfe df gdg",
      //     id: 2,
      //     image: require("@/assets/pad(canDelete)/background/keli.png")
      //   },
      // ],
      newComment: {user: '', text: ''},
      key: 0,
    }
  },
  methods: {
    formatDate(date) {
      const options = {year: 'numeric', month: 'long', day: 'numeric'};
      return new Date(date).toLocaleDateString(undefined, options);
    },

    addComment() {
      if (this.newComment.user && this.newComment.text) {
        const newComment = {
          user: this.newComment.user,
          text: this.newComment.text,
          image: this.newComment.image,
          date: this.formatDate(new Date()),
          id: this.comments.length + 1
        };

        this.comments.push(newComment);

        this.newComment.user = '';
        this.newComment.text = '';
        this.newComment.image = null;
        this.key += 1;
      }
    },

    handleImageUpload(event) {
      const file = event.target.files[0];
      this.newComment.image = URL.createObjectURL(file);
      this.key += 1;
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
