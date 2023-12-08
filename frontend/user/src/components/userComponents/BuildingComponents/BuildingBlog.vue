<template>
  <div class="blog">
    <div class="post">
      <el-scrollbar style="height: 100%" class="scrollbar-for" wrap-style="overflow-x:hidden;">
        <div id="comment">
          <div v-for="comment in comments" :key="comment.id" class="comment">
            <div class="comment-header">
              <div class="username">{{ comment.username }}</div>
              <img :src="comment.userImageUrl" alt="User Image" class="user-image">
              <div class="time">{{ comment.time }}</div>
            </div>
            <div class="comment-text">{{ comment.text }}</div>
            <div class="comment-images">
              <img v-for="img in comment.imageUrl" :src="img" alt="comment image" class="comment-image" width="100px">
            </div>

            {{comment.score}}
            <el-button @click="toggleLike(comment.commentId)"  icon="el-icon-star-off">
            </el-button>
          </div>
        </div>
      </el-scrollbar>

    </div>
    <div class="upload" v-if="show">
      <el-form :model="newComment" label-position="top" @submit.prevent="submitComment" class="form">
        <el-form-item :label="$t('lang.commentContent')">
          <el-input v-model="newComment.text" type="textarea"/>
        </el-form-item>

        <el-form-item :label="$t('lang.uploadPhoto')">
          <el-upload
              class="upload-demo"
              :show-file-list="false"
              action=""
              :before-upload="handleFileChange"
          >
            <el-button>{{$t('lang.selectFile')}}</el-button>
          </el-upload>
        </el-form-item>

        <div v-if="photos.length > 0" class="image">
          <h3>{{$t('lang.fileSelected')}}</h3>
          <div v-for="(photo, index) in photos" :key="index" class="selected-photo">
            <el-image :src="getPhotoUrl(photo)" alt="Selected Photo" :width="500"/>
            <el-button @click="removePhoto(index)">{{$t('lang.deleteFile')}}</el-button>
          </div>
        </div>

        <el-form-item>
          <el-button type="primary" native-type="submit" @click="submitComment">{{$t('lang.submitComment')}}</el-button>
        </el-form-item>
      </el-form>


    </div>

      <div v-else class="info">
        <div class="permission">
          登录后可评论
        </div>
      </div>


  </div>



</template>


<script>
import FormData from "form-data";

export default {
  data() {
    return {
      newComment: {},
      comments: [],
      photos: [],
      show: false,
    }
  },
  created() {
    if (localStorage.getItem('isLogin') && localStorage.getItem('isBlocked')) {
      this.show = true
    }
    this.buildingId = this.$route.params.id;
    this.fetchCommentData(this.buildingId);
  },
  methods: {
    // formatDate(date) {
    //   const options = {year: 'numeric', month: 'long', day: 'numeric'};
    //   return new Date(date).toLocaleDateString(undefined, options);
    // },

    submitComment() {
      const formData = new FormData();
      for (var i=0; i< this.photos.length; i++) {
        formData.append('files', this.photos[i]);
      }
      const data = {
        userId: localStorage.getItem("userID"),
        text: this.newComment.text,
        time: new Date(),
        buildingId: this.buildingId,
      }
      formData.append('commentParam', new Blob([JSON.stringify(data)], {type: "application/json"}))
      console.log(formData)
      this.$http.post('/user/comment/upload',  formData, {headers: {'Content-Type': 'multipart/form-data'}}).then(resp => {
        if (resp.status === 200) {
          this.$vs.notification({
            color:'success',
            position: 'top-center',
            title: 'Edit successfully',
            text: '',
          })
        }});
      this.newComment.text = ''
      this.photos = []

    },

    toggleLike(id) {
      const userId = localStorage.getItem("userID")
      this.$http.get(`/user/comment/like?userId=${userId}&commentId=${id}`).then(res=>{
        console.log(res)
      })
    },

    handleFileChange(file) {
      this.photos.push(file)
    },
    removePhoto(index) {
      // 从数组中删除选中的照片
      this.photos.splice(index, 1);
    },
    getPhotoUrl(photo) {
      // 根据文件对象创建一个临时的 URL，用于预览图片
      return URL.createObjectURL(photo);
    },


    fetchCommentData(id) {
      this.$http.get(`/public/comment/get/approved?buildingId=${id}`,)
          .then(response => {
            this.comments = response.data.data;
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          })
          .finally(function () {
          });
    },

  },
};
</script>

<style scoped>
.blog {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;
  height: 99%;
  //background-image: url("../../../assets/pad(canDelete)/background/keli.png");
  //background-size: cover;
}

.post {
  width: 40%;
  height: 100%;
}

.comment {
  height: 400px;
  margin-top: 50px;
  width: 100%;
  background-color: white;
  border-radius: 20px;
}

.liked {
  transform: translateY(-5px);
}

.comment-text {
  margin: 30px;
}

.upload {
  margin-top: 50px;
  width: 40%;
  height: 80%;
  background-color: white;
  border-radius: 20px;
  overflow: auto;
  margin-left: 50px;
}
.info{
  width: 50%;
  text-align: center;
}
.permission {
  margin-left: 40px;
  width: 50%;
  background-color: white;
  border-radius: 20px;
  font-size: 30px;
}

.form {
  margin-left: 30px;
  margin-right: 30px;
}

.image {
  max-width: 200px;
}

@media screen and (max-width: 768px) {
  .post {
    width: 80%;
    margin: 10%;
    height: 80%;
  }
  .info{
    width: 80%;
  }

  .permission{
    width: 80%;
    background-color: white;
    border-radius: 20px;
    font-size: 30px;
  }

  .upload {
    width: 80%;
  }

  .blog {
    display: block;
    align-content: center;
    overflow-y: hidden;
  }
}



.comment-header {
  display: flex;
  align-items: center;
}

.username {
  font-weight: bold;
  margin-right: 8px;
}

.user-image {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  margin-right: 8px;
}

.comment-images {
  margin-top: 8px;
}

.comment-image {
  margin-right: 8px;
}

</style>
