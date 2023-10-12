<template>
  <div id="detail">
    <div class="sidebar">
      <vs-sidebar
          relative
          hover-expand
          reduce
          v-model="active"
          open
      >
        <vs-sidebar-item id="intro" @click.native="handleLink('intro')">
          <template #icon>
            <i class='bx bx-drink'></i>
          </template>
          Introduction
        </vs-sidebar-item>

        <vs-sidebar-item id="blog" @click.native="handleLink('blog')">
          <template #icon>
            <i class='bx bx-group'></i>
          </template>
          Blog
        </vs-sidebar-item>

        <vs-sidebar-item id="room" @click.native="handleLink('room')">
          <template #icon>
            <i class='bx bx-grid-alt'></i>
          </template>
          Room
        </vs-sidebar-item>

        <vs-sidebar-item id="mass" @click.native="handleLink('mass')">
          <template #icon>
            <i class='bx bx-chat'></i>
          </template>
          Massage Wall
        </vs-sidebar-item>

        <vs-sidebar-item id="return" @click.native="handleLink('building')">
          <template #icon>
            <i class='bx bx-home'></i>
          </template>
          Return
        </vs-sidebar-item>
      </vs-sidebar>
    </div>
  </div>
</template>

<script>
export default {
  data:() => ({
    active: 'intro',
  }),
  methods: {
    handleLink(link){
      console.log(link)
      this.$router.push({name:link, params:{}}).catch(err=>err)
    },
  }
}
</script>

<style scoped>


</style>