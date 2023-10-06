<template>
  <div id="blog">
    <vs-row>
      <vs-col vs-type="flex" vs-justify="center" vs-align="center" w="6">
        <div class="blog-post" v-for="post in posts" :key="post.id">
          <h1 class="post-title">{{ post.title }}</h1>
          <div class="post-meta">
            <span class="post-date">{{ formatDate(post.date) }}</span>
            <span class="post-author">by {{ post.author }}</span>
          </div>
          <div class="post-content">
            <div v-html="post.content"></div>
          </div>
        </div>
      </vs-col>

      <vs-col vs-type="flex" vs-justify="center" vs-align="center" w="6">

      </vs-col>
    </vs-row>


  </div>


</template>

<script>
export default {
  data() {
    return {
      posts: [
        {title: "hello", author: "jjjj", date: "2023/09/11", content: "ni hao", id: 1},
        {title: "hell", author: "jjjj", date: "2023/09/11", content: "ni hao", id: 2},
        {title: "hel", author: "jjjj", date: "2023/09/11", content: "ni hao", id: 3},

      ]
    }
  },
  methods: {
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
  },
};
</script>

<style scoped>
.blog-post {
  background-color: #fff;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.post-title {
  font-size: 24px;
  margin: 0;
  padding: 0;
  color: #333;
}

.post-meta {
  margin-top: 10px;
  font-size: 14px;
  color: #666;
}

.post-content {
  margin-top: 20px;
  font-size: 16px;
  line-height: 1.6;
  color: #333;
}

.post-author {
  margin-left: 20px;
}

.post-date {
  margin-right: 20px;
}

</style>