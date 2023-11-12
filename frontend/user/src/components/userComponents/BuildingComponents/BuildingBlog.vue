<template>
  <div id="post">
   <div id="comment">
     <div v-for="comment in comments" :key="comment.id" class="comment">
       <div class="user">{{ comment.user }}</div>
       <div class="comment-text">{{ comment.text }}</div>
       <img v-if="comment.image" :src="comment.image" alt="Comment Image">
     </div>
   </div>

    <div id="addcomment">
      <h2>添加评论</h2>
      <form @submit.prevent="addComment">
        <label for="username">用户名:</label>
        <input type="text" v-model="newComment.user" required>

        <label for="comment">评论:</label>
        <textarea v-model="newComment.text" required></textarea>

        <label for="image">上传照片:</label>
        <input type="file" @change="handleImageUpload">

        <!-- 预览区域 -->
        <div class="preview">
          <h3>评论预览</h3>
          <div class="user">{{ newComment.user }}</div>
          <div class="comment-text">{{ newComment.text }}</div>
          <img v-if="newComment.image" :src="newComment.image" alt="Comment Image">
        </div>

        <button type="submit">发表评论</button>
      </form>
    </div>
  </div>


</template>

<script>
export default {
  data() {
    return {
      comments: [
        {
          user: "jjjj",
          date: "2023/09/11",
          text: "ni hao kdifjsidfnisdnfdnfidf ldkfodfk diofjidfji dfsjdfoj",
          id: 1,
          image: require("@/assets/pad(canDelete)/background/keli.png")
        },
        {
          user: "jjjj",
          date: "2023/09/11",
          text: "ni hao dfsd  dfe df gdg",
          id: 2,
          image: require("@/assets/pad(canDelete)/background/keli.png")
        },

      ],
      newComment: { user: '', text: '' },
    }
  },
  methods: {
    formatDate(date) {
      const options = {year: 'numeric', month: 'long', day: 'numeric'};
      return new Date(date).toLocaleDateString(undefined, options);
    },

    addComment() {
      // 上传图片到服务器（示例中使用了虚拟路径，实际应该使用真实路径）
      if (this.newComment.user && this.newComment.text) {
        const formData = new FormData();
        formData.append('user', this.newComment.user);
        formData.append('text', this.newComment.text);
        formData.append('image', this.newComment.image);

        // 发送formData到服务器，这里使用了虚拟路径
        // 请根据实际情况修改为真实的后端接口
        // fetch('http://localhost:3000/comments', {
        //   method: 'POST',
        //   body: formData,
        // })
        //     .then(response => response.json())
        //     .then(data => {
        //       this.comments.push(data);
        //       // 清空输入框
        //       this.newComment.user = '';
        //       this.newComment.text = '';
        //       this.newComment.image = null;
        //     })
        //     .catch(error => console.error('Error:', error));
      }
    },
    handleImageUpload(event) {
      const file = event.target.files[0];
      this.newComment.image = URL.createObjectURL(file);
    },

  },
};
</script>

<style scoped>
#post {
  display: grid;
  grid-template-columns: 1fr 1fr; /* 使用两列，每列占据相同的宽度 */
  height: 100vh; /* 使 #post 高度占满整个视口 */
}

#comment {
  overflow-y: auto; /* 如果内容过多，启用纵向滚动条 */
}

#addpost {
  padding: 20px; /* 添加一些内边距，使内容看起来更舒适 */
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

.preview {
  margin-top: 20px;
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 8px;
}
</style>