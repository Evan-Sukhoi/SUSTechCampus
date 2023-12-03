<template>
  <div class="center">
    <p>
      this is building manage
    </p>
    <vs-table>
      <template #header>
        <div class="header-container">
          <vs-input v-model="search" border placeholder="Search"/>
          <div class="button-container">
            <vs-button @click="add">
              <i class="bx bx-add-to-queue"></i>
            </vs-button>
          </div>
        </div>
      </template>
      <template #thead>
        <vs-tr>
          <vs-th>
            buildingId
          </vs-th>
          <vs-th>
            name
          </vs-th>
          <vs-th>
            openTime
          </vs-th>
          <vs-th>
            closeTime
          </vs-th>
          <vs-th>
            locationName
          </vs-th>
          <vs-th>
            introduction
          </vs-th>
          <vs-th>
            nearestStation
          </vs-th>
          <vs-th>
            videoUrl
          </vs-th>
          <vs-th>
            operation
          </vs-th>
        </vs-tr>
      </template>
      <template #tbody>
        <vs-tr
            :key="i"
            v-for="(tr, i) in $vs.getPage($vs.getSearch(building, search), page, max)"
            :data="tr"
        >
          <vs-td>
            {{ tr.buildingId }}
          </vs-td>
          <vs-td>
            {{ tr.name }}
          </vs-td>
          <vs-td>
            {{ tr.openTime }}
          </vs-td>
          <vs-td>
            {{ tr.closeTime }}
          </vs-td>
          <vs-td>
            {{ tr.locationName }}
          </vs-td>
          <vs-td>
            {{ tr.introduction }}
          </vs-td>
          <vs-td>
            {{ tr.nearestStation }}
          </vs-td>
          <vs-td>
            {{ tr.videoUrl }}
          </vs-td>
          <div>
            <vs-button @click="edit(tr)">
              Edit
            </vs-button>
          </div>
        </vs-tr>
      </template>
      <template #footer>
        <vs-pagination v-model="page" :length="$vs.getLength(building, max)" />
      </template>
    </vs-table>
    <vs-dialog v-model="active">
      <div class="con-form">
        <h3>{{title}}</h3>
        <vs-input v-model="editInfo.name" label-placeholder="name"></vs-input>
        <vs-input v-model="editInfo.openTime" label-placeholder="openTime"></vs-input>
        <vs-input v-model="editInfo.closeTime" label-placeholder="closeTime"></vs-input>
        <vs-input v-model="editInfo.locationName" label-placeholder="locationName"></vs-input>
        <vs-input v-model="editInfo.introduction" label-placeholder="introduction"></vs-input>
        <vs-input v-model="editInfo.nearestStation" label-placeholder="nearestStation"></vs-input>
        <vs-input v-model="editInfo.videoUrl" label-placeholder="videoUrl"></vs-input>
        <div class="footer-dialog">
          <vs-button @click="submit">Submit</vs-button>
        </div>
      </div>
    </vs-dialog>
  </div>
</template>
<script>
export default {
  name: 'BuildingManage',
  data:() => ({
    page: 1,
    max: 7,
    search: '',
    info:'',
    active: false,
    title:'',
    building:[
      {buildingId: 1, name:'林根',openTime:'10:00:00', closeTime:'12:00:00', locationName:'行政路', introduction:'sdasd', nearestStation:'asda', videoUrl:'asd',coverId:'1'}
    ],
    editInfo:{
      buildingId:'',
      name:'',
      openTime:'',
      closeTime:'',
      locationName:'',
      introduction:'',
      nearestStation:'',
      videoUrl:'',
    }
  }),
  beforeMount() {
    this.$http.get('/admin/building/all').then(resp=>{
      this.building = resp.data
      console.log(resp)
    }).catch(err=>{
      console.log(err)
    })
  },
  methods:{
    restore() {
      this.editInfo.buildingId=''
      this.editInfo.name=''
      this.editInfo.introduction=''
      this.editInfo.openTime = ''
      this.editInfo.closeTime = ''
      this.editInfo.locationName = ''
      this.editInfo.nearestStation = ''
      this.editInfo.videoUrl = ''
    },
    edit(tr){
      this.title = 'Edit'
      this.active = true
      this.editInfo.buildingId = tr.buildingId
      this.editInfo.name= tr.name
      this.editInfo.introduction= tr.introduction
      this.editInfo.openTime = tr.openTime
      this.editInfo.closeTime = tr.closeTime
      this.editInfo.locationName = tr.locationName
      this.editInfo.nearestStation = tr.nearestStation
      this.editInfo.videoUrl = tr.videoUrl
    },
    add(){
      this.title='add'
      this.restore()
      this.active = true
    },
    submit(){
      this.$http.post('', this.editInfo).then(resp=>{
        console.log(resp)

      }).catch(err=>{
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
.center{
  width: 70%;
}
.button-container{
  display: flex;

}
.header-container{
  display: flex;
  justify-content: space-between;
}
</style>

<style lang="stylus">
getColor(vsColor, alpha = 1)
unquote("rgba(var(--vs-"+vsColor+"), "+alpha+")")
getVar(var)
unquote("var(--vs-"+var+")")
.not-margin
  margin 0px
  font-weight normal
  padding 10px
.con-form
  width 100%
  .flex
    display flex
    align-items center
    justify-content space-between
    a
      font-size .8rem
      opacity .7
      &:hover
        opacity 1
  .vs-checkbox-label
    font-size .8rem
  .vs-input-content
    margin 10px 0px
    width calc(100%)
    .vs-input
      width 100%
.footer-dialog
  display flex
  align-items center
  justify-content center
  flex-direction column
  width calc(100%)
  .new
    margin 0px
    margin-top 20px
    padding: 0px
    font-size .7rem
    a
      color getColor('primary') !important
      margin-left 6px
      &:hover
        text-decoration underline
  .vs-button
    margin 0px
</style>