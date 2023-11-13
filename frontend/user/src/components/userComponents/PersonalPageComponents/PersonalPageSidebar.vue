
<template>
  <div class="hidden">
    <div>
      <vs-sidebar
          absolute
          v-model="active"
          open
          square
      >
        <template #logo>
          <!-- ...img logo -->
        </template>
        <vs-sidebar-item id="home">
          <template #icon>
            <i class='bx bx-home'></i>
          </template>
          {{$t('lang.reservationRecord')}}
        </vs-sidebar-item>
        <template #footer>
          <vs-row justify="space-between">
            <vs-avatar>
              <img src="/avatars/avatar-5.png" alt="">
            </vs-avatar>

            <vs-avatar badge-color="danger" badge-position="top-right">
              <i class='bx bx-bell' ></i>

              <template #badge>
                28
              </template>
            </vs-avatar>
          </vs-row>
        </template>
      </vs-sidebar>
    </div>
    <div>

    </div>
  </div>
</template>
<script>
export default {
  name:"PersonalPage",
  data:() => ({
    active: 'home',
  })
}
</script>

        