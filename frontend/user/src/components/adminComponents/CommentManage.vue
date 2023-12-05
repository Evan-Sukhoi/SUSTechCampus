<template>
  <div class="center">
    <vs-table
        v-model="selected"
    >
      <template #header>
        <vs-input v-model="search" border placeholder="Search" />
      </template>
      <template #thead>
        <vs-tr>
          <vs-th>
            <vs-checkbox
                :indeterminate="selected.length === comments.length" v-model="allCheck"
                @change="selected = $vs.checkAll(selected, comments)"
            />
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'commentId')">
            commentId
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'userId')">
            userId
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'username')">
            username
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'buildingId')">
            buildingId
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'time')">
            time
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'text')">
            text
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'score')">
            score
          </vs-th>
          <vs-th>
            photos
          </vs-th>
          <vs-th sort @click="comments = $vs.sortData($event ,comments, 'adminId')">
            adminId
          </vs-th>
          <vs-th>
            operation
          </vs-th>
        </vs-tr>
      </template>
      <template #tbody>
        <vs-tr
            :key="i"
            v-for="(tr, i) in $vs.getPage($vs.getSearch(comments, search), page, max)"
            :data="tr"
            :is-selected="!!selected.includes(tr)"
            not-click-selected
            open-expand-only-td
        >
          <vs-td checkbox>
            <vs-checkbox :val="tr" v-model="selected" />
          </vs-td>
          <vs-td edit @click="edit = tr, editProp = 'name', editActive = true">
            {{ tr.commentId }}
          </vs-td>
          <vs-td>
            {{ tr.userId }}
          </vs-td>
          <vs-td>
            {{ tr.username }}
          </vs-td>
          <vs-td>
            {{ tr.buildingId }}
          </vs-td>
          <vs-td>
            {{ tr.time }}
          </vs-td>
          <vs-td>
            {{tr.text}}
          </vs-td>
          <vs-td>
            {{tr.score}}
          </vs-td>
          <vs-td>
            <div v-for="img in tr.imageUrl">
              <img :src="img" alt="" width="100px">
            </div>
          </vs-td>
          <vs-td>
            {{tr.adminId}}
          </vs-td>
          <vs-td>
            <div class="con-content">
                <vs-button border danger @click="disapproveComment(tr.commentId, tr)" :disabled="tr.adminId === 0" >
                  disapprove
                </vs-button>
                <vs-button border safe @click="approveComment(tr.commentId, tr)" :disabled="tr.adminId > 0">
                  approve
                </vs-button>
            </div>
          </vs-td>
        </vs-tr>
      </template>
      <template #footer>
        <vs-pagination v-model="page" :length="$vs.getLength($vs.getSearch(comments, search), max)" />
      </template>
    </vs-table>
  </div>
</template>

<script>
export default {
  name: 'CommentManage',
  data() {
    return {
      editActive: false,
      edit: null,
      editProp: {},
      search: '',
      allCheck: false,
      page: 1,
      max: 5,
      active: 0,
      selected: [],
      comments: [],
    }
  },
  created() {
    this.fetchCommentData();
  },
  methods: {
    fetchCommentData() {
      this.$http.get(`/admin/comment/all`,)
          .then(response => {
            this.comments = response.data;
            console.log(response.data);
          })
          .catch(function (error) {
          })
          .finally(function () {
          });
    },
    approveComment(id, tr) {
      this.$http.post(`/admin/comment/approve?commentId=${id}&adminId=1`)
          .then(response => {
            if (response.status === 200) {
              this.$vs.notification({
                color:'success',
                position: 'top-center',
                title: 'Approve successfully',
                text: '',
              })
            }
          })
          .catch(function (error) {
          })
          .finally(function () {
          });
      tr.adminId = 1;
    },

    disapproveComment(id, tr) {
      this.$http.post(`/admin/comment/approve?commentId=${id}&adminId=0`)
          .then(response => {
            if (response.status === 200) {
              this.$vs.notification({
                color:'success',
                position: 'top-center',
                title: 'Disapprove successfully',
                text: '',
              })
            }
          })
          .catch(function (error) {
          })
          .finally(function () {
          });
      tr.adminId = 0;
    },
  }
}
</script>

<style scoped>
.center {
  max-width: 1000px;
}
.con-content{
  display: flex;
  justify-content: space-between;
}
</style>