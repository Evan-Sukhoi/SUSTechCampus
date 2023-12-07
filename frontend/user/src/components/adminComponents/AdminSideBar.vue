<template>
  <div id="class">
    <div class="sidebar">
      <vs-sidebar
          absolute
          v-model="active"
          open
      >
        <vs-sidebar-item id="visual" @click.native="handleLink('visualization')">
          <template #icon>
            <i class='bx bx-drink'></i>
          </template>
          VisualizationDashboard
        </vs-sidebar-item>

        <vs-sidebar-group>
          <template #header>
            <vs-sidebar-item arrow>
              <template #icon>
                <i class='bx bx-group'></i>
              </template>
              Dynamic Source
            </vs-sidebar-item>
          </template>

          <vs-sidebar-item id="userManage" @click.native="handleLink('userManage')">
            <template #icon>
              <i class='bx bxl-instagram'></i>
            </template>
            Users Manage
          </vs-sidebar-item>
          <vs-sidebar-item id="appointmentManage" @click.native="handleLink('appointmentManage')">
            <template #icon>
              <i class='bx bxl-twitter' ></i>
            </template>
            Appointments Manage
          </vs-sidebar-item>
          <vs-sidebar-item id="commentManage" @click.native="handleLink('commentManage')">
            <template #icon>
              <i class='bx bxl-facebook' ></i>
            </template>
            Comments Manage
          </vs-sidebar-item>
        </vs-sidebar-group>

        <vs-sidebar-group>
          <template #header>
            <vs-sidebar-item arrow>
              <template #icon>
                <i class='bx bx-group'></i>
              </template>
              Static Source
            </vs-sidebar-item>
          </template>

          <vs-sidebar-item id="buildingManage" @click.native="handleLink('buildingManage')">
            <template #icon>
              <i class='bx bxl-instagram'></i>
            </template>
            BuildingsManage
          </vs-sidebar-item>
          <vs-sidebar-item id="busline" @click.native="handleLink('buslineManage')">
            <template #icon>
              <i class='bx bxl-twitter' ></i>
            </template>
            Buslines Manage
          </vs-sidebar-item>
        </vs-sidebar-group>

        <vs-sidebar-item id="loginlog" @click.native="handleLink('informationSending')">
          <template #icon>
            <i class='bx bx-grid-alt'></i>
          </template>
          Login Log
        </vs-sidebar-item>

        <vs-sidebar-item id="illegallog" @click.native="handleLink('log')">
          <template #icon>
            <i class='bx bxl-twitter'></i>
          </template>
          Illegal Log
        </vs-sidebar-item>

        <vs-sidebar-item id="logout" @click.native="logout">
          <template #icon>
            <i class='bx bx-chat'></i>
          </template>
          Logout
        </vs-sidebar-item>
      </vs-sidebar>
    </div>
  </div>
</template>

<script>
export default {
  components: {

  },
  data:() => ({
    active: 'intro',
  }),
  methods: {
    handleLink(link){
      console.log(link)
      this.$router.push({name:link, params:{}}).catch(err=>err)
    },
    logout() {
      localStorage.setItem('admin', 'False')
      this.$router.push('/adminLogin').catch(err=>err)
    }
  }
}
</script>

<style scoped>


</style>