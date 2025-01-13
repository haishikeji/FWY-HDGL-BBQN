<template>
  <div class="table_div">
    <div class="add_header">
      <el-button size="medium " type="primary" @click="setUserNameData"
        >添加会员礼包</el-button
      >
    </div>
    <el-table stripe border :data="tableData" style="width: 100%">
      <el-table-column label="礼包名称" prop="name" width="300rpx"> </el-table-column>
      <el-table-column label="价格">
        <template slot-scope="scope">
          <div>
            {{((scope.row.price-0)/100).toFixed(2)}}元
          </div>
        </template>
         </el-table-column>
      <el-table-column label="礼包内容" prop="detail"> </el-table-column>
      <el-table-column label="图片">
        <template slot-scope="scope">
          <div>
            <img
              :src="item"
              alt=""
              v-for="item in scope.row.images.split(',')"
              :key="item"
              class="tableImg"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.released"
            active-text="上架"
            inactive-text="下架"
            @change="releaseCommodity(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <div class="tab_a_box">
           
            <el-button icon="el-icon-edit" type="primary" size="small" @click="handleEdit(scope.row)" >编辑</el-button>
           
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="sizeChange"
        @current-change="currentChange"
        :page-sizes="[10, 20, 30, 40]"
        hide-on-single-page
      >
      </el-pagination>
    </div>
    <!-- 新增编辑 -->
    <el-dialog
      :title="titles"
      :visible.sync="dialogVisible"
      width="50%"
      top="5vh"
    >
      <div class="dialogSpan">
        <el-form
          label-width="80px"
          label-position="right"
          ref="form"
          :model="editForm"
          :rules="rules"
        >
          <el-form-item label="礼包名称" prop="name">
            <el-input
              type=""
              v-model="editForm.name"
              placeholder="礼包名称"
              style="width: 300px"
            />
          </el-form-item>
          <el-form-item label="礼包类型">
            <el-select v-model="editForm.type" placeholder="请选择礼包类型">
              <el-option
                v-for="item in dict"
                :key="item.code"
                 :value="item.code"
                :label="item.name"
               
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="礼包内容" prop="detail">
            <el-input
              type=""
              v-model="editForm.detail"
              placeholder="礼包内容"
              style="width: 300px"
            />
          </el-form-item>
          <el-form-item label="礼包价格" prop="price">
            <el-input
              type=""
              v-model="editForm.price"
              placeholder="礼包价格"
              style="width: 300px"
            /><label for="">￥/元</label>
          </el-form-item>
        </el-form>
        <div>
          <el-upload
            class="upload-demo"
            action="/api/oss/open/v1/getUploadFileUrl/?x-oss-process=image/resize,p_80"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :headers="token"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :file-list="fileList"
            :on-success="change"
          >
            <el-button size="small" icon="el-icon-plus" type="primary"
              >添加礼包图片</el-button
            >
            <span slot="tip" class="el-upload__tip">
              （支持png,jpg,jpeg格式图片，图片尺寸188x220）
            </span>
          </el-upload>
          <div v-if="imgUrl.length">
            <img
              :src="item"
              alt=""
              v-for="item in imgUrl"
              :key="item"
              class="imgUrl"
            />
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('form')">确认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      fileList: [],
      editForm: {
        name: "",
        price: "",
        images: "",
        type: "1",
        detail: "",
      },
      dialogVisible: false,
      total: 0,
      titles: "添加会员礼包",
      tableData: [],
      value: "",
      page: {
        current: 1,
        size: 10,
      },
      dict:'',
      token: {
        authorization: "",
      },
      imgUrl: [],
      rules: {
        name: [{ required: true, message: "请输入礼包名称", trigger: "blur" }],
        detail: [
          { required: true, message: "请填写礼包内容", trigger: "blur" },
        ],
        type: [{ required: true, message: "请选择礼包类型", trigger: "blur" }],
        price: [{ required: true, message: "请填写礼包价格", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getCommodityPageData();
    this.getToken();
  },
  mounted() {
    this.getDict();
  },
  methods: {
    /* 分条 */
    sizeChange(val) {
      console.log(val);
      this.page.size = val;
      this.getCommodityPageData();
    },
    /* 分页 */
    currentChange(val) {
      this.page.current = val;
      console.log(val);
      this.getCommodityPageData();
    },
    /* 获取token */
    getToken() {
      this.token.authorization = sessionStorage.getItem("token");
      console.log(this.token.authorization, "token");
    },
    /* 获取字典code */
    getDict() {
      let dict = JSON.parse(sessionStorage.dict);
      console.log(dict, "zd");
      this.dict = dict.filter((item) => item.code == "7")[0].voList;
      console.log(this.dict,'this.dict');
    },
    /* 表单验证 */
    submitForm(formName) {
      if (!this.imgUrl.length) {
        return this.$message.error("请上传商品图片");
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.titles == "添加会员礼包") {
            this.addCommodity();
          } else {
            this.updateCommodity();
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleEdit(row) {
      this.titles = "编辑会员礼包";
      this.dialogVisible = true;
      console.log(row,'222222222222222222222');
      this.shopId = row.id;
      this.imgUrl = row.images.split(",");
      let arr = [];
      arr = row.images.split(",");
      this.fileList = arr.map((item) => {
        return {
          name: "商品图片",
          url: item,
        };
      });
      /* this.fileList */
      this.editForm.type = row.type.toString();
      this.editForm.name = row.name;
      this.editForm.price = (row.price-0)/100;
      this.editForm.detail=row.detail
    },
    /* 添加礼包 */
    setUserNameData(index, row) {
      this.titles = "添加会员礼包";
      this.dialogVisible = true;
        this.editForm = {
          name: "",
          price: "",
          images: "",
          type:'',
          detail: "",
        };
        this.imgUrl = [];
        this.fileList = [];
      console.log(index, row);
    },
    /* 添加商品的方法 */
    async addCommodity() {
      this.editForm.images = this.imgUrl.join(',');
      this.editForm.price=(this.editForm.price-0)*100;
      const { data } = await this.$api.vipGiftBag.addCommodity(this.editForm);
      if (data.code===200) {
 this.dialogVisible = false;
        this.getCommodityPageData()
      }
      console.log(data, "添加的数据");
    },
    handleDelete(index, row) {
      console.log(index, row);
    },

    /* 删除图片 */
    handleRemove(file, fileList) {
      console.log(file, fileList, "2222222222222222");
      if (file.name === "商品图片") {
        let i = this.imgUrl.indexOf(file.url);
        this.imgUrl.splice(i, 1);
      } else {
        let i = this.imgUrl.indexOf(file.response.data);
        this.imgUrl.splice(i, 1);
      }
    },
    /* 图片上传的方法 */
    change(response, file, fileList) {
      /*   */
      console.log(response);
      console.log(file, 11);
      console.log(fileList, 22);
      this.imgUrl.push(response.data);
  
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    /* 获取商品分页列表 */
    async getCommodityPageData() {
      const { data } = await this.$api.vipGiftBag.getCommodityPage(this.page);

      if (data.code == 200) {
         
        this.tableData = data.data.records;
        this.total = data.data.total;
       
      }
    },
    /* 更新商品 */
    async updateCommodity() {
       this.editForm.images = this.imgUrl.join(',');
          this.editForm.price=(this.editForm.price-0)*100;
      const { data } = await this.$api.vipGiftBag.updateCommodity({
        id: this.shopId,
        ...this.editForm,
      });
      if (data.code == 200) {
         this.dialogVisible = false;
        this.editForm = {
          name: "",
          price: "",
          images: "",
          type:'',
          detail: "",
        };
        this.imgUrl = [];
        this.fileList = [];
        this.$message.success(data.msg);
        this.getCommodityPageData();
      }
    },
    /* 上下架商品 */
    async releaseCommodity(val) {
      console.log(val);
      const { data } = await this.$api.vipGiftBag.releaseCommodity({
        id: val.id,
        state: val.released,
      });
      if (data.code == 200) {
        this.$message.success(data.msg);
        this.getCommodityPageData();
      }
    },
  },
};
</script>

<style scoped lang='scss'>
.add_header {
  height: 80px;
  background: #fff;
  line-height: 80px;
  padding-left: 10px;
}
.but {
  position: absolute;
  bottom: 20px;
  right: 100px;
  z-index: 999;
  .el-button {
    margin-right: 10px;
    width: 80px;
    letter-spacing: 1px;
  }
}
.el-tree {
  padding-left: 10px;
}
.tab_a_box {
  a {
    margin-right: 10px;
  }
  a:nth-child(2) {
    color: #169bd5;
  }
  a:nth-child(3) {
    color: red;
  }
  a:nth-child(4) {
    color: #666666;
  }
}
.imgUrl {
  width: 200px;
  height: 200px;
  margin-right: 10px;
}
.tableImg {
  height: 40px;
  width: 40px;
  margin-right: 10px;
}
</style>