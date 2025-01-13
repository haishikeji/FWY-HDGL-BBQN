<template>
  <div class="table_div">
    <div class="add_but" @click="addDialogVisible()">
      <el-button type="primary">添加青年介绍</el-button>
    </div>
    <el-dialog
      title="青年介绍"
      :visible.sync="dialogVisible"
      width="60%"
      top="5vh"
    >
      <div>
        <el-select v-model="description.type" placeholder="请选择类型">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <div>
        <el-input
          v-model="description.title"
          placeholder="请输入标题"
          class="title"
        ></el-input>
      </div>

      <editor-bar
        :catchData="catchData"
        :content="description.text"
      ></editor-bar>
      <div class="editor_but">
        <el-button type="primary" @click="addCommonDesc" class="" v-if="!textId"
          >保存/发布</el-button
        >
        <el-button type="primary" @click="updateCommonDesc" class="" v-else
          >更新</el-button
        >
      </div>
    </el-dialog>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="标题" prop="title"> </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import EditorBar from "@/components/editoritem.vue";
export default {
  components: { EditorBar },
  data() {
    return {
      description: {
        title: "",
        type: 1,
        text: "",
      },
      dialogVisible: false,
      editorContent: "",
      textData: "",
      textId: "",
        options: [{
          value: 1,
          label:'青年介绍',
        }, {
          value: 2,
          label: '助梦人计划'
        }],
        tableData: [
        ]
       
    };
  },
  created() {
    this.getDescInfo();
  },
  methods: {
    // 监听富文本的输入
    catchData(e) {
      console.log("e=====?>", e);
      this.description.text = e;
    },

    addDialogVisible(){
      this.textId=''
  this.dialogVisible=true
  this.description={
        title: "",
        type: 1,
        text: "",
      }
    },
    /* 添加青年介绍 */
    async addCommonDesc() {
      if (!this.description.title) {
        return this.$message.error("请填写标题");
      }
      if (!this.description.text) {
        return this.$message.error("请填写内容");
      }
      
      console.log( this.description);
      const { data } = await this.$api.introduce.addCommonDesc(
        this.description
      );
        console.log(data);
      if (data.code == 200) {
        this.$message.success("添加成功");
        this.dialogVisible=false
        this.getDescInfo();
      }
    },
    /* 获取青年介绍 */
    async getDescInfo() {
      const { data } = await this.$api.introduce.getAppDescInfo();
      console.log(data,'青年介绍');
      if (data.code==200) {
        this.tableData=data.data
      }
    },
    /* 更新青年介绍 */
    async updateCommonDesc() {
      const { data } = await this.$api.introduce.updateCommonDesc({
        ...this.description,
        id: this.textId,
      });
      console.log(data);
      if (data.code == 200) {
         this.$message.success("更新成功");
         this.dialogVisible=false
        this.getDescInfo();
      }
    },
    /* 打开编辑 */
    handleEdit(val){
      console.log(val);
      this.dialogVisible=true
      this.textId=val.id
      this.description.title=val.title
       this.description.text=val.text
        this.description.type=val.type
    }
  },
};
</script>

<style scoped lang='scss'>
.editor_but {
  height: 100px;
  text-align: center;
  line-height: 100px;
}
.add_but {
  height: 60px;
  line-height: 60px;
}
.title {
  width: 92.9%;
  margin: 20px 0;

  margin-bottom: 10px;
}
.table_div {
  padding: 10px 30px;
}
.title_text {
  margin: 18px 0;
}
.title_textb {
  margin: 15px 0;
}
</style>