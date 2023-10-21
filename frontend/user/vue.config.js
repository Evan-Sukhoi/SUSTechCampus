const { defineConfig } = require('@vue/cli-service')
const {join} = require("path");
module.exports = defineConfig({
  transpileDependencies: true
})
//
// // 配置stylus
// const stylusOptions = {
//   import: [
//     join(__dirname, "../src/assets/style/public.styl")
//   ]
// }
//
// module.exports = {
//   css: {
//     loaderOptions: {
//       stylus: {
//         // 这里可以添加Stylus选项
//       }
//     }
//   }
// };
//
