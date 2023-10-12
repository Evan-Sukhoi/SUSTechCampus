<template>
  <div id="blog">
    <div id="blogcards">
      <vs-row v-for="post in posts" :key="post.id">
        <vs-card type="3">
          <template #title>
            <h3>{{post.title}}</h3>
          </template>
          <template #img>
            <img :src="post.src" alt="">
          </template>
          <template #text>
            <p>
              {{post.content}}
            </p>
          </template>
          <template #interactions>
            <vs-button danger icon>
              <i class='bx bx-heart'></i>
            </vs-button>
          </template>
        </vs-card>
      </vs-row>

    </div>




  </div>


</template>

<script>
export default {
  data() {
    return {
      posts: [
        {title: "hello", author: "jjjj", date: "2023/09/11", content: "ni hao kdifjsidfnisdnfdnfidf ldkfodfk diofjidfji dfsjdfoj", id: 1, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hell", author: "jjjj", date: "2023/09/11", content: "ni hao dfsd  dfe df gdg", id: 2, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hel", author: "jjjj", date: "2023/09/11", content: "ni hao", id: 3, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hello", author: "jjjj", date: "2023/09/11", content: "ni hao kdifjsidfnisdnfdnfidf ldkfodfk diofjidfji dfsjdfoj", id: 4, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hell", author: "jjjj", date: "2023/09/11", content: "ni hao dfsd  dfe df gdg", id: 5, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hel", author: "jjjj", date: "2023/09/11", content: "ni hao", id: 6, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hello", author: "jjjj", date: "2023/09/11", content: "ni hao kdifjsidfnisdnfdnfidf ldkfodfk diofjidfji dfsjdfoj", id: 7, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hell", author: "jjjj", date: "2023/09/11", content: "ni hao dfsd  dfe df gdg", id: 8, src: require("@/assets/pad(canDelete)/background/keli.png")},
        {title: "hel", author: "jjjj", date: "2023/09/11", content: "ni hao", id: 9, src: require("@/assets/pad(canDelete)/background/keli.png")},

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
#blog {
  display: flex;
  flex-direction: row;
}

#blogcards {
  width: 50%;
}
</style>