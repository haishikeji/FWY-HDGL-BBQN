<template>
  <div class="table_div">
    <div class="add_header">
      <div class="add_header_ipt">
        <el-input
          v-model="page.nameOrPhone"
          placeholder="输入姓名电话搜索"
          @input="handleSearch"
        />
         <el-button class="add_but" type="primary" @click="linkUrlAddData()">添加助梦人</el-button>
      </div>
     
    </div>
    <el-table
      height="65vh"
      stripe
      border
      :data="
        tableData.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
    >
      <el-table-column label="头像" prop="nickname" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" alt="" class="avatar" />
        </template>
      </el-table-column>
      <el-table-column label="姓名" prop="nickname" align="center">
      </el-table-column>
      <el-table-column label="电话" prop="userPhone" align="center">
      </el-table-column>
      <el-table-column label="职位" prop="profession" align="center">
        <!--  <template slot-scope="scope">
               <span v-for="(item,index) in scope.row.identityIds" :key="index">{{identityIds(item)}}</span>
              </template>
 -->
      </el-table-column>
      <el-table-column label="所在企业" prop="enterprise" align="center"> </el-table-column>

      <el-table-column align="center" label="操作" width="300px">
        <template slot-scope="scope">
          <div class="tab_a_box">
            <a @click="handleEdit(scope.row)">查看编辑</a>
            <a @click="removeEdit(scope.row)">取消助梦人</a>
            <a
              @click="topEdit(scope.row, true)"
              v-if="!scope.row.dreamerRecommend"
              >置顶</a
            >
            <a @click="topEdit(scope.row, false)" v-else>取消置顶</a>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        hide-on-single-page
        @size-change="sizeChange"
        @current-change="currentChange"
        :page-sizes="[10, 20, 30, 40]"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      dialogTableVisible: false,
      tableData: [],
      gridData: "",
      totalB: "",
      search: "",
      props: {
        label: "name",
        children: "zones",
      },
      count: 1,
      options: [
        {
          value: 1,
          label: "正常",
        },
        {
          value: 2,
          label: "即将到期",
        },
        {
          value: 3,
          label: "已过期",
        },
      ],
      value: "",
      value2: "",
      page: {
        current: 1,
        size: 10,
        cycleId: "",
        identityId: "",
  
        nameOrPhone: "",
        dreamer: true,
      },
      vipCode: [],
      vipCycle: [],
      total: 0,
    };
  },
  created() {
    this.getCode();
    this.getUserMemberPage();
    this.getVipCycle();
  },
  methods: {
    handleSearch() {
      this.page.current = 1;
      this.getUserMemberPage();
    },
    /* 用户身份数据渲染 */
    identityIds(id) {
      let a = vipCode
        .filter((item) => {
          if (item.id == id) {
            return item.name;
          }
        })
        .join(",");
      console.log(a, "aaaaaaaaaaa");
    },
    handleEdit(val) {
      this.$router.push(`dreamer/addDreamer?id=${val.id}&&look=look`);
    },
    /* 取消逐梦人 */
    async removeEdit(val) {
      const { data } = await this.$api.userApp.updateMemberOrDreamer({
        type: 2,
        released: false,
        id: val.id,
      });
      if (data.code == 200) {
        this.$message.success("成功");
        this.getUserMemberPage();
      }
      console.log(data);
    },
    async topEdit(val, released) {
      const { data } = await this.$api.userApp.recommendData({
        id: val.id,
        released,
        type: 2,
      });
      if (data.code == 200) {
        this.$message.success("成功");
        this.getUserMemberPage();
      }
    },
    /* 分条 */
    sizeChange(val) {
      console.log(val);
      this.page.size = val;
      this.getUserMemberPage();
    },
    /* 分页 */
    currentChange(val) {
      this.page.current = val;
      console.log(val);
      this.getUserMemberPage();
    },

    /* 获取字典 */
    getCode() {
      const dict = JSON.parse(sessionStorage.dict);
      console.log(dict);
      dict.forEach((item) => {
        if (item.name === "会员身份") {
          this.vipCode = item.voList;
          console.log(item.voList);
        }
      });
    },
    /* 获取会员期数 */
    async getVipCycle() {
      const { data } = await this.$api.vipCycle.getUserCyclePage();
      this.vipCycle = data.data;
      console.log(data, "会员期数");
    },
    /* 获取会员列表 */
    async getUserMemberPage() {
      const { data } = await this.$api.vip.getUserMemberPage(this.page);
      console.log(data, "逐梦人");
      if (data.code == 200) {
        this.total = data.data.total;
        this.tableData = data.data.records;
        console.log(data, "筛选之后的数据");
      }
    },
    /*设为会员  */
    setVip(val) {
      console.log(val);
      this.$router.push(`vip/vipEdit?id=${val.id}`);
    },
    /* 添加助梦人 */
    linkUrlAddData(){
      this.$router.push(`dreamer/addDreamer`);
    }
  },
};
</script>

<style scoped lang='scss'>
.avatar {
  width: 50px;
  height: 50px;
}
.add_but{
  margin-left: 20px;
}
.add_header {
  height: 80px;
  background: #fff;
  padding-left: 10px;
  display: flex;
  justify-content: space-between;
  padding-right: 60px;
  align-items: center;
  .el-input {
    width: 200px;
  }
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
  a:nth-child(1) {
    color: #169bd5;
  }
  a:nth-child(2) {
    color: red;
  }
}
</style>