<template>
  <div>
    <div class="center">
      <vs-table
          v-model="selected"
      >
        <template #header>
          <div class="header-container">
            <vs-input v-model="search" border placeholder="Search"/>
          </div>
        </template>

        <template #thead>
          <vs-tr>
            <vs-th>
              <vs-checkbox
                  :indeterminate="selected.length === logs.length" v-model="allCheck"
                  @change="selected = $vs.checkAll(selected, logs)"
              />
            </vs-th>
            <vs-th sort @click="logs = $vs.sortData($event ,logs, 'id')">
              userId
            </vs-th>
            <vs-th sort @click="users = $vs.sortData($event ,logs, 'email')">
              userName
            </vs-th>
            <vs-th sort @click="users = $vs.sortData($event ,logs, 'id')">
              loginTime
            </vs-th>
            <vs-th>
              ipAddress
            </vs-th>
            <vs-th>
              port
            </vs-th>
            <vs-th>
              isBlocked
            </vs-th>
          </vs-tr>
        </template>
        <template #tbody>
          <vs-tr
              :key="i"
              v-for="(tr, i) in $vs.getPage($vs.getSearch(logs, search), page, max)"
              :data="tr"
              :is-selected="!!selected.includes(tr)"
              not-click-selected
              open-expand-only-td
          >
            <vs-td checkbox>
              <vs-checkbox :val="tr" v-model="selected"/>
            </vs-td>
            <vs-td>
              {{ tr.id }}
            </vs-td>
            <vs-td>
              {{ tr.email }}
            </vs-td>
            <vs-td>
              {{ tr.userId }}
            </vs-td>
            <vs-td>
              {{tr.isBlocked}}
            </vs-td>
            <vs-td>
              {{tr.isBlocked}}
            </vs-td>
            <vs-td>
              {{tr.isBlocked}}
            </vs-td>
            <vs-td>
              <div style="display: flex; align-items: center;">
                <vs-button flat icon @click="block(tr.userId)" v-if="!tr.isBlocked">
                  <i class='bx bxs-face-mask'></i>
                </vs-button>
                <vs-button flat icon @click="unBlock(tr.userId)" v-else danger>
                  <i class='bx bxs-face-mask'></i>
                </vs-button>
              </div>
            </vs-td>
          </vs-tr>
        </template>
        <template #footer>
          <vs-pagination v-model="page" :length="$vs.getLength($vs.getSearch(logs, search), max)"/>
        </template>
      </vs-table>


    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'LogLog',
  data: () => ({
    showExpandContent: true,
    search: '',
    allCheck: false,
    page: 1,
    max: 5,
    active: true,
    selected: [],
    logs: [
      {

      },
    ],
  }),
  methods: {
    fetchLogs() {
      // 使用axios或fetch发送GET请求获取后端数据
      this.$http.get('/admin/user/all') // 根据你的后端API端点进行设置
          .then(response => {
            this.logs = response.data; // 将后端数据赋值给前端users数据
            console.log(response);
          })
          .catch(error => {
            console.error('Failed to fetch data from the backend', error);
          });
    },
    block(userId){
      this.$http.post('/admin/user/block?userId='+userId).then(resp => {
        if (resp.status === 200){
          this.$vs.notification({
            color:'success',
            position: 'top-center',
            title: 'Block successfully',
            text: '',
          })
          this.fetchUsers()
        }
      }).catch(err=>{console.log(err)})
    },
    unBlock(userId) {
      this.$http.post('/admin/user/unblock?userId=' + userId).then(resp => {
        if (resp.status === 200) {
          this.$vs.notification({
            color: 'success',
            position: 'top-center',
            title: 'unBlock successfully',
            text: '',
          })
          this.fetchUsers()
        }
      })
    }
  }
}
</script>

<style scoped>
</style>