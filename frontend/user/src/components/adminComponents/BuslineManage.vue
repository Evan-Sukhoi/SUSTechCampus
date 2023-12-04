
<template>
  <div class="center">
    <el-scrollbar style="height: 100%" class="scrollbar-for" wrap-style="overflow-x:hidden;">
      <vs-table
          v-model="selected"
      >
        <template #header>
          <div class="con-form">
            <vs-input v-model="search" border placeholder="Search" />
<!--            <vs-button style="float: right" @click="addBusLine">-->
<!--              <i class="bx bx-image-add">-->
<!--                add busLine-->
<!--              </i>-->
<!--            </vs-button>-->
          </div>
        </template>
        <template #thead>
          <vs-tr>
            <vs-th>
              Name
            </vs-th>
            <vs-th>
              Path
            </vs-th>
            <vs-th>
              Operation
            </vs-th>
          </vs-tr>
        </template>
        <template #tbody>
          <vs-tr
              :key="i"
              v-for="(tr, i) in $vs.getPage($vs.getSearch(busLineInfo, search), page, max)"
              :data="tr"
              :is-selected="!!selected.includes(tr)"
              not-click-selected
              open-expand-only-td
          >
            <vs-td edit @click="edit = tr, editProp = 'name', editActive = true">
              {{ tr.name }}
            </vs-td>
            <vs-td>
              <vs-tr>
                <vs-th>
                  station
                </vs-th>
                <vs-th>
                  corner
                </vs-th>
              </vs-tr>
              <div v-for="(point, j) in tr.list" :key="j">
                <vs-td >
                  {{point.two}}
                </vs-td>
                <vs-td>
                  {{point.point}}
                </vs-td>
              </div>
            </vs-td>
            <vs-td>
              <vs-button @click="editBusLine(tr,i)">Edit</vs-button>
            </vs-td>
          </vs-tr>
        </template>
        <template #footer>
          <vs-pagination v-model="page" :length="$vs.getLength($vs.getSearch(busLineInfo, search), max)" />
        </template>
      </vs-table>
    </el-scrollbar>

    <vs-dialog v-model="active" prevent-close scroll overflow-hidden>
      <h3>{{title}}</h3>
      <vs-input label-placeholder="name" v-model="editForm.name"></vs-input>
      <div v-for="(point, i) in editForm.list" :key="i">
        <h5>Station and longitude</h5>
        <div class="con-form">
          <vs-input label-placeholder="from" v-model="point.two[0]"></vs-input>
          <vs-input label-placeholder="to" v-model="point.two[1]"></vs-input>
          <vs-button border danger @click="deleteStation(editForm.list, i)">delete station</vs-button>
        </div>
        <div v-for="(latitude, j) in point.point" :key="j" class="con-form">
          <vs-input label-placeholder="x" v-model="latitude[0]"></vs-input>
          <vs-input label-placeholder="y" v-model="latitude[1]"></vs-input>
          <vs-button danger @click="deleteLatitude(point.point, j)">delete latitude</vs-button>
        </div>
        <vs-button @click="addLatitude(point.point)"> add latitude</vs-button>
      </div>
      <vs-button success @click="addStation(editForm.list)">add station </vs-button>
      <div class="button">
        <vs-button @click="submit">Submit</vs-button>
      </div>
    </vs-dialog>
  </div>
</template>
<script>
export default {
  data:() => ({
    title:'',
    editActive: false,
    edit: null,
    editProp: {},
    search: '',
    allCheck: false,
    page: 1,
    max: 5,
    active: false,
    selected: [],
    editForm: {
      name:'',
      list:[]
    },
    editID:'',
    busLineInfo: [
      {
        "id": 1,
        "name": "Leanne Graham",
        "username": "Bret",
        "email": "Sincere@april.biz",
        "website": "hildegard.org",
      },
    ]
  }),
  beforeMount() {
    this.getBusLine()
  },
  methods:{
    getBusLine(){
      this.$http.get('/admin/busline/all').then(resp=>{
        if (resp.status === 200){
          console.log(resp)
          this.busLineInfo = resp.data
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    editBusLine(singleBusLine, i){
      this.editID = i
      this.title = 'Edit'
      this.active = true
      this.editForm.name = singleBusLine.name
      this.editForm.list = singleBusLine.list
    },
    addBusLine(){
      this.busLineInfo.push({
        "name":'',
        "list":[
          {"two":['', ''], "point":[[0,0], [0,0]]}
        ]
      })
    },
    submit(){
      this.busLineInfo[this.editID] = this.editForm
      this.$http.post('/admin/busline/update', this.busLineInfo)
      .then(resp=>{
        console.log(resp)
        if (resp.status === 200){
            console.log('ok');
            this.active = false
            this.editForm =  {
              name:'',
              list:[]
            }
            this.editID = ''
          this.getBusLine()
        }else {
          this.$vs.notification({
            color:'danger',
            position: 'top-center',
            title: resp,
            text: '',
          })
          return
        }
      }).catch(err=>{
        console.log(err)
      })
      // fs.writeFile('../../assets/location/busline.json', busLine, function (err) {
      //   if (err) {
      //     console.error(err)
      //     return
      //   }
      //   console.log('ok');
      //   this.active = false
      //   this.editForm =  {
      //     name:'',
      //     list:[]
      //   }
      //   this.editID = ''
      // });
    },
    deleteStation(list, index){
      list.splice(index, 1)
    },
    deleteLatitude(point, index){
      point.splice(index, 1)
    },
    addLatitude(point){
      point.push(['', ''])
    },
    addStation(list){
      list.push({"two":['', ''], "point":[['', ''], ['', '']]})
    }
  }
}
</script>

<style scoped>
.con-form{
  display: flex;
  justify-content: center;
}
.center{
  overflow-y: auto;
  height: 100%;
}
.con-form{
  display: flex;
  justify-content: space-between;
}
.button{
  display: flex;
  justify-content: center;
}
</style>