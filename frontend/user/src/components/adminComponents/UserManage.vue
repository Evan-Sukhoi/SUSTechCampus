<template>
  <div>
    <p>
      this is user manage
    </p>

    <div class="center">
      <vs-table
          v-model="selected"
      >
        <template #header>
          <div class="header-container">
            <vs-input v-model="search" border placeholder="Search"/>
            <div class="button-container">
              <vs-button flat icon relief :disabled="selected.length === 0">
                <i class='bx bxs-face-mask'></i>
              </vs-button>
              <vs-button danger icon relief :disabled="selected.length === 0">
                <i class='bx bx-trash'></i>
              </vs-button>
              <vs-button flat icon relief @click="registerBatch">
                <i class='bx bx-add-to-queue'></i>
              </vs-button>
            </div>
          </div>
        </template>

        <template #thead>
          <vs-tr>
            <vs-th>
              <vs-checkbox
                  :indeterminate="selected.length === users.length" v-model="allCheck"
                  @change="selected = $vs.checkAll(selected, users)"
              />
            </vs-th>
            <vs-th sort @click="users = $vs.sortData($event ,users, 'name')">
              Name
            </vs-th>
            <vs-th sort @click="users = $vs.sortData($event ,users, 'email')">
              Email
            </vs-th>
            <vs-th sort @click="users = $vs.sortData($event ,users, 'id')">
              Id
            </vs-th>
            <vs-th>
              operation
            </vs-th>
          </vs-tr>
        </template>
        <template #tbody>
          <vs-tr
              :key="i"
              v-for="(tr, i) in $vs.getPage($vs.getSearch(users, search), page, max)"
              :data="tr"
              :is-selected="!!selected.includes(tr)"
              not-click-selected
              open-expand-only-td
          >
            <vs-td checkbox>
              <vs-checkbox :val="tr" v-model="selected"/>
            </vs-td>
            <vs-td>
              {{ tr.name }}
            </vs-td>
            <vs-td>
              {{ tr.email }}
            </vs-td>
            <vs-td>
              {{ tr.userId }}
            </vs-td>
            <vs-td>
              <div style="display: flex; align-items: center;">
                <vs-button flat icon>
                  <i class='bx bxs-face-mask'></i>
                </vs-button>
                <vs-button @click="editUser(tr)">
                  Edit
                </vs-button>
                <vs-button danger icon @click="deleteUserClick(tr)">
                  <i class='bx bx-trash'></i>
                </vs-button>
              </div>
            </vs-td>
          </vs-tr>
        </template>
        <template #footer>
          <vs-pagination v-model="page" :length="$vs.getLength($vs.getSearch(users, search), max)"/>
        </template>
      </vs-table>


    </div>

    <div class="center">
      <vs-dialog prevent-close v-model="editActive">
        <template #header>
          <h4 class="not-margin">
            编辑用户信息
          </h4>
        </template>


        <div class="con-form">
          <vs-input v-model="thisUser.name" placeholder="Name">
            <template #icon>
              <i class='bx bxs-user'></i>
            </template>
          </vs-input>
          <vs-input v-model="thisUser.email" placeholder="Email">
            <template #icon>
              <i class='bx bxs-envelope'></i>
            </template>
          </vs-input>
          <vs-input v-model="thisUser.phone" placeholder="Phone">
            <template #icon>
              <i class='bx bxs-phone'></i>
            </template>
          </vs-input>
          <vs-input type="password" v-model="thisUser.password" placeholder="Password">
            <template #icon>
              <i class='bx bxs-lock'></i>
            </template>
          </vs-input>
<!--          <div class="flex">-->
<!--            <vs-checkbox v-model="checkbox1">Remember me</vs-checkbox>-->
<!--            <a href="#">Forgot Password?</a>-->
<!--          </div>-->
        </div>

        <template #footer>
          <div class="footer-dialog">
            <vs-button block @click="updateUser">
              Save
            </vs-button>

            <div class="new">
              New Here? <a href="#">Create New Account</a>
            </div>
          </div>
        </template>
      </vs-dialog>

      <vs-dialog v-model="deleteActive">
        <template #header>
          <h4 class="not-margin">
            Are you kidding me?
          </h4>
        </template>
        <div style="display: flex; justify-content: center; align-items: center;">
          <vs-button danger @click="deleteUser">
            Delete
          </vs-button>
          <vs-button>
            Cancel
          </vs-button>
        </div>
      </vs-dialog>
      <vs-dialog v-model="registerActive" prevent-close scroll overflow-hidden>
        <div style="display: flex; justify-content: space-between"  v-for="(account,i) in registerList" :key="i">
          <p>{{account}}</p>
          <vs-button border danger @click="removeAccount(i)">
            <i class="bx bx-trash">Delete</i>
          </vs-button>
        </div>
        <div style="display: flex; justify-content: space-between">
          <vs-input v-model="inputAccount"></vs-input>
          <vs-button border @click="addAccount">
            <i class="bx bx-book-add">add user</i>
          </vs-button>
        </div>
        <div style="display: flex; justify-content: center">
          <vs-button border :disabled="registerList.length === 0" @click="registerUpload">
            <i class="bx bxl-ok-ru">Submit</i>
          </vs-button>
        </div>
      </vs-dialog>
    </div>
  </div>

</template>


<script>
import axios from 'axios';
import FormData from "form-data";

export default {
  created() {
    this.fetchUsers();
  },
  methods: {
    fetchUsers() {
      // 使用axios或fetch发送GET请求获取后端数据
      axios.get('http://localhost:8081/admin/user/all') // 根据你的后端API端点进行设置
          .then(response => {
            this.users = response.data; // 将后端数据赋值给前端users数据
            console.log(response);
          })
          .catch(error => {
            console.error('Failed to fetch data from the backend', error);
          });
    },
    infoCopy(tr){
      this.thisUser.email = tr.email
      this.thisUser.imageId = tr.img
      this.thisUser.name = tr.name
      this.thisUser.password = tr.password
      this.thisUser.phone = tr.phone
      this.thisUser.userId = tr.userId
    },
    editUser(tr) {
      this.infoCopy(tr)
      console.log("selected: ", this.thisUser);
      this.editActive = true;
    },
    updateUser() {
      this.editActive = false;
      console.log("update user: ", this.thisUser);
      let url = 'http://localhost:8081/admin/user/update?name=' + this.thisUser.name +
          '&email=' + this.thisUser.email +
          '&phone=' + this.thisUser.phone +
          '&password=' + this.thisUser.password +
          '&userId=' + this.thisUser.userId;
      axios.post(url).then(response => {
        if (response.data.code === 200) {
          console.log("update success");
          this.fetchUsers()
        } else {
          console.log("update failed");
          console.log(response);
        }
      }).catch(error => {
        console.error('Failed to update user', error);
      });
    },
    deleteUserClick(tr){
      this.infoCopy(tr)
      console.log("selected: ", this.thisUser);
      this.deleteActive = true;
    },
    deleteUser(){
      this.showExpandContent = false; // 关闭扩展内容
      console.log("delete user: ", this.thisUser);
      let url = 'http://localhost:8081/admin/user/delete?userId=' + this.thisUser.userId;
      axios.post(url).then(response => {
        if (response.data.code === 200) {
          console.log("delete success");
          this.deleteActive = false;
          this.fetchUsers()
        } else {
          console.log("delete failed");
          console.log(response);
        }
      }).catch(error => {
        console.error('Failed to delete user', error);
      });

    },
    registerBatch(){
      this.registerActive = true
    },
    addAccount(){
      if (this.inputAccount === ''){
        this.$vs.notification({
          color:'danger',
          position: 'top-center',
          title: 'there is no account',
          text: '',
        })
        return
      }
      this.registerList.push(this.inputAccount)
      this.inputAccount = ''
    },
    removeAccount(index){
      this.registerList.splice(index, 1)
    },
    registerUpload(){
      const set = new Set();
      for (let k of this.registerList) {
        if (set.has(k)){
          this.$vs.notification({
            color:'danger',
            position: 'top-center',
            title: 'there exist the same account',
            text: '',
          })
          return
        }
        set.add(k)
      }
      for (var i=0; i < this.registerList.length; i++){
        var data = {
          username:this.registerList[i],
          password:'123456',
          email:this.registerList[i] + '@mail.sustech.edu.cn',
          phoneNumber:'00000000000',
        }
        this.$http.post('/public/register', data).then(resp => {
          console.log(resp);
          if (resp.status===200){
            this.registerList = []
            this.registerActive = true
            this.$vs.notification({
              color:'success',
              position: 'top-center',
              title: 'register sucess',
              text: '',
            })
          }else {
            this.$vs.notification({
              color:'primary',
              position: 'top-center',
              title: resp.status,
              text: '',
            })
          }
        }).catch(err=>{
          this.$vs.notification({
            color:'primary',
            position: 'top-center',
            title: 'error',
            text: err,
          })
        })
      }
    }
  },
  name: 'UserManage',

  data: () => ({
    editActive: false,
    deleteActive: false,
    showExpandContent: true,
    edit: null,
    editProp: {},
    search: '',
    allCheck: false,
    page: 1,
    max: 5,
    active: true,
    selected: [],
    thisUser: {
      email:'',
      imageId:'',
      name:'',
      password:'',
      phone:'',
      userId:'',
    },
    user_name: '',
    user_email: '',
    user_phone: '',
    user_password: '',
    users: [
      {
        "user_id": 1,
        "name": "Leanne Graham",
        "email": "Sincere@april.biz",
        "phone": "17707368031",
        "password": "123456",
      },
    ],
    inputAccount:'',
    registerList:[],
    registerActive: false,
  })
}

</script>


<style scoped>
.center {
  width: 70%;
  margin: 0;
}

.header-container {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 将内容分散对齐，将两个按钮推到最右边 */
}

.button-container {
  display: flex;
  align-items: center;
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






