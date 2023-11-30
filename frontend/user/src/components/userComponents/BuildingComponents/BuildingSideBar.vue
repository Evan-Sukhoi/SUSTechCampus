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
          I{{$t('lang.introduction')}}
        </vs-sidebar-item>

        <vs-sidebar-item id="blog" @click.native="handleLink('blog')">
          <template #icon>
            <i class='bx bx-group'></i>
          </template>
          {{$t('lang.blog')}}
        </vs-sidebar-item>

        <vs-sidebar-item id="room" @click.native="handleLink('room')">
          <template #icon>
            <i class='bx bx-grid-alt'></i>
          </template>
          {{$t('lang.room')}}
        </vs-sidebar-item>

        <vs-sidebar-item id="return" @click.native="handleLink('building')">
          <template #icon>
            <i class='bx bx-home'></i>
          </template>
          {{$t('lang.return')}}
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
  beforeMount() {
    const currentRoute = this.$route.path;
    var path = currentRoute
    path = path.split("/")
    this.active = path[path.length - 1]
  },
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