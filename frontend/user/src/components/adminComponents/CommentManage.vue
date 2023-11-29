<template>
  <div>
    <div class="post">
      <div id="comment">
        <div v-for="comment in comments" :key="comment.id" class="comment">
          <div class="comment-text">{{ comment.text }}</div>
          <div class="comment-user">{{ comment.user }}</div>
          <img v-if="comment.image" :src="comment.image" alt="Comment Image">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CommentManage',
  data() {
    return {
      comments: [],
    }
  },
}
</script>

<style scoped>
</style>