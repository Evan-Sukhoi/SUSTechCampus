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
              operationTime
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
              {{ tr.userId }}
            </vs-td>
            <vs-td>
              {{ tr.username }}
            </vs-td>
            <vs-td>
              {{ tr.operationTime }}
            </vs-td>
            <vs-td>
              {{tr.ipAddress}}
            </vs-td>
            <vs-td>
              {{tr.port}}
            </vs-td>
            <vs-td>
              {{tr.blocked}}
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
export default {
  name: 'InformationSending',
  data: () => ({
    // showExpandContent: true,
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
  created() {
    this.fetchLogs()
  },
  methods: {
    fetchLogs() {

    }
  }
}
</script>

<style scoped>
</style>