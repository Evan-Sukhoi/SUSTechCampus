<template>
  <div class="center">
    <vs-dialog v-model="active" not-close prevent-close>
      <template #header>
        <h4 class="not-margin">
          Welcome to <b>Admin System</b>
        </h4>
      </template>


      <div class="con-form">
        <vs-input v-model="username" placeholder="Username">
          <template #icon>
            @
          </template>
        </vs-input>
        <vs-input type="password" v-model="password" placeholder="Password">
          <template #icon>
            <i class='bx bxs-lock'></i>
          </template>
        </vs-input>
      </div>

      <template #footer>
        <div class="footer-dialog">
          <vs-button block @click="sign">
            Sign In
          </vs-button>
          <div class="new">
            New Here? Please call our master JIABAO LIU!
          </div>
        </div>
      </template>
    </vs-dialog>
  </div>
</template>
<script>
import JSEncrypt from "jsencrypt";

const encrypt = new JSEncrypt();
export default {
  data:() => ({
    active: true,
    username: '',
    password: '',
  }),
  methods:{
    getPublicKey() {
      this.$http.get('http://localhost:8081/public/get-key').then(res => {
        let publicKey = res.data.publicKey;
        encrypt.setPublicKey(publicKey);
      }).catch(err => {
        console.error('Failed to fetch public key:', err);
      });
    },
    sign(){
      console.log(`/admin/login?username=${this.username}&password=${encrypt.encrypt(this.password)}`)
      this.$http.post(`/admin/login?username=${this.username}&password=${encrypt.encrypt(this.password)}`).then(resp=>{
        console.log(resp)
        if (resp.status===200){
          localStorage.setItem('admin', 'True')
          this.$router.push('/admin/appointmentManage')
        }
      })

    }
  },
  beforeMount() {
    this.getPublicKey()
  }
}
</script>
<style lang="stylus" scoped>
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
        