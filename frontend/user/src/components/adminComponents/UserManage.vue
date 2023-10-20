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
          <vs-input v-model="search" border placeholder="Search"/>
        </template>
        <template #thead>
          <vs-tr>
            <vs-th>
              <vs-checkbox
                  :indeterminate="selected.length == users.length" v-model="allCheck"
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
            <vs-td edit @click="edit = tr, editProp = 'name', editActive = true">
              {{ tr.name }}
            </vs-td>
            <vs-td>
              {{ tr.email }}
            </vs-td>
            <vs-td>
              {{ tr.id }}
            </vs-td>

            <template #expand>
              <div class="con-content" style="display: flex; justify-content: space-between; align-items: center;">

                <div style="display: flex; align-items: center;">
                  <vs-avatar>
                    <img :src="`/avatars/avatar-${i + 1}.png`" alt="">
                  </vs-avatar>
                  <div style="margin-left: 10px;"> <!-- 通过 margin-left 添加间距 -->
                    <p>
                      {{ tr.name }}
                    </p>
                  </div>
                </div>

                <div style="display: flex; align-items: center;">
                  <vs-button flat icon>
                    <i class='bx bxs-face-mask'></i>
                  </vs-button>
                  <vs-button flat icon>
                    Edit
                  </vs-button>
                  <vs-button border danger>
                    Remove User
                  </vs-button>
                </div>

              </div>
            </template>

          </vs-tr>
        </template>
        <template #footer>
          <vs-pagination v-model="page" :length="$vs.getLength($vs.getSearch(users, search), max)"/>
        </template>
      </vs-table>

      <vs-dialog v-model="editActive">
        <template #header>
          Change Prop {{ editProp }}
        </template>
        <vs-input @keypress.enter="editActive = false" v-if="editProp == 'email'" v-model="edit[editProp]"/>
        <vs-select @change="editActive = false" block v-if="editProp == 'name'" placeholder="Select"
                   v-model="edit[editProp]">
          <vs-option label="Vuesax" value="Vuesax">
            Vuesax
          </vs-option>
          <vs-option label="Vue" value="Vuejs">
            Vue
          </vs-option>
          <vs-option label="Javascript" value="Javascript">
            Javascript
          </vs-option>
          <vs-option disabled label="Sass" value="Sass">
            Sass
          </vs-option>
          <vs-option label="Typescript" value="Typescript">
            Typescript
          </vs-option>
          <vs-option label="Webpack" value="Webpack">
            Webpack
          </vs-option>
          <vs-option label="Nodejs" value="Nodejs">
            Nodejs
          </vs-option>
        </vs-select>
      </vs-dialog>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

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
          })
          .catch(error => {
            console.error('Failed to fetch data from the backend', error);
          });
    },
  },
  name: 'UserManage',
  data: () => ({
    editActive: false,
    edit: null,
    editProp: {},
    search: '',
    allCheck: false,
    page: 1,
    max: 5,
    active: 0,
    selected: [],
    users: [
      {
        "id": 1,
        "name": "Leanne Graham",
        "username": "Bret",
        "email": "Sincere@april.biz",
        "website": "hildegard.org",
      },
      {
        "id": 2,
        "name": "Ervin Howell",
        "username": "Antonette",
        "email": "Shanna@melissa.tv",
        "website": "anastasia.net",
      },
      {
        "id": 3,
        "name": "Clementine Bauch",
        "username": "Samantha",
        "email": "Nathan@yesenia.net",
        "website": "ramiro.info",
      },
      {
        "id": 4,
        "name": "Patricia Lebsack",
        "username": "Karianne",
        "email": "Julianne.OConner@kory.org",
        "website": "kale.biz",
      },
      {
        "id": 5,
        "name": "Chelsey Dietrich",
        "username": "Kamren",
        "email": "Lucio_Hettinger@annie.ca",
        "website": "demarco.info",
      },
      {
        "id": 6,
        "name": "Mrs. Dennis Schulist",
        "username": "Leopoldo_Corkery",
        "email": "Karley_Dach@jasper.info",
        "website": "ola.org",
      },
      {
        "id": 7,
        "name": "Kurtis Weissnat",
        "username": "Elwyn.Skiles",
        "email": "Telly.Hoeger@billy.biz",
        "website": "elvis.io",
      },
      {
        "id": 8,
        "name": "Nicholas Runolfsdottir V",
        "username": "Maxime_Nienow",
        "email": "Sherwood@rosamond.me",
        "website": "jacynthe.com",
      },
      {
        "id": 9,
        "name": "Glenna Reichert",
        "username": "Delphine",
        "email": "Chaim_McDermott@dana.io",
        "website": "conrad.com",
      },
      {
        "id": 10,
        "name": "Clementina DuBuque",
        "username": "Moriah.Stanton",
        "email": "Rey.Padberg@karina.biz",
        "website": "ambrose.net",
      }
    ]
  })
}

</script>


<style scoped>
.center {
  width: 70%; /* 这里可以设置你想要的宽度 */
  margin: 0;
}
</style>
