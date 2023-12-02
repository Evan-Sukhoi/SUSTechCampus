<template>
  <div class="blog">
    <div class="post">
      <el-scrollbar style="height: 100%" class="scrollbar-for" wrap-style="overflow-x:hidden;">
        <div id="comment">
          <div v-for="comment in comments" :key="comment.id" class="comment">
            <div class="comment-text">{{ comment.text }}</div>
            <el-image :src="comment.image" alt="Comment Image" />
          </div>
        </div>
      </el-scrollbar>

    </div>
    <div class="upload">
      <el-form :model="newComment" label-position="top" @submit.prevent="submitComment" class="form">
        <el-form-item label="评论内容">
          <el-input v-model="newComment.text" type="textarea" />
        </el-form-item>

        <el-form-item label="上传照片">
          <el-upload
              class="upload-demo"
              :show-file-list="false"
              action=""
              :before-upload="handleFileChange"
          >
            <el-button>选取文件</el-button>
          </el-upload>
        </el-form-item>

        <div v-if="photos.length > 0" class="image">
          <h3>已选择的照片</h3>
          <div v-for="(photo, index) in photos" :key="index" class="selected-photo">
            <el-image :src="getPhotoUrl(photo)" alt="Selected Photo" :width="500"/>
            <el-button @click="removePhoto(index)">删除</el-button>
          </div>
        </div>

        <el-form-item>
          <el-button type="primary" native-type="submit">提交评论</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      newComment: {},
      comments: [
        {id:1, image:require('../../../assets/pad(canDelete)/background/狐狸.jpg'), text:'hhh'},
        {id:2, image:require('../../../assets/pad(canDelete)/background/狐狸.jpg'), text:'hhh'}
      ],
      photos: [],
    }
  },
  created() {
    this.buildingId = this.$route.params.id;
    // this.fetchCommentData(this.buildingId);
  },
  methods: {
    // formatDate(date) {
    //   const options = {year: 'numeric', month: 'long', day: 'numeric'};
    //   return new Date(date).toLocaleDateString(undefined, options);
    // },

    submitComment() {
      // 发送评论数据到服务器
      // 使用 this.comment.text 和 this.comment.photos
      // 使用 axios 或其他 HTTP 库发送请求
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


    // fetchCommentData(id) {
    //   axios.get(`http://localhost:8081/public/comment/get/approved?buildingId=${id}`, )
    //       .then(response => {
    //         this.comments = response.data.data;
    //         console.log(response.data);
    //       })
    //       .catch(function (error) {
    //         console.log(error);
    //       })
    //       .finally(function () {
    //       });
    // },

  },
};
</script>

<style scoped>
.blog {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;
  height: 99%;
  background-image: url("../../../assets/pad(canDelete)/background/keli.png");
  background-size: cover;
}

.post {
  width: 40%;
  height: 100%;
}

.comment{
  height: 400px;
  margin-top: 50px;
  width: 100%;
  background-color: white;
  border-radius: 20px;
}

.comment-text{
  margin: 30px;
}

.upload{
  margin-top: 50px;
  margin-left: 10%;
  width: 40%;
  height: 80%;
  background-color: white;
  border-radius: 20px;
}

.form{
  margin-left: 30px;
  margin-right: 30px;
}

.image {
  max-width: 200px;
}

@media screen and (max-width: 900px) {
  .post{
    width: 80%;
    margin: 10%;
    float: left;
  }
  .upload{
    float: left;
  }
  .blog{
    display: block;
  }
}

</style>
