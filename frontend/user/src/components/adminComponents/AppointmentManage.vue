<template>
  <div>
    this is appointment manage
  </div>
</template>

<script>
export default {
  name: 'AppointmentManage',
  mounted() {
    this.fetchBuildingSta()
    this.fetchRoomSta()
  },
  methods: {
    fetchBuildingSta() {
      this.$http.get('/admin/building/statistics').then(res => {
        console.log(res.data)
      })
    },

    fetchRoomSta() {
      this.$http.get('/admin/room/statistics').then(res => {
        console.log(res.data)
      })
    },
  }
}
</script>