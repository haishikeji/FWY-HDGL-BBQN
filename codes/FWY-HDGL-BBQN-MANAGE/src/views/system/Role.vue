<template>
  <div class="table_div">
    <div class="add_header">
      <el-button size="medium " type="primary" @click="handleDelete()"
        >新增</el-button
      >
    </div>
    <el-table
      stripe
      border
      :data="
        tableData.filter(
          (data) =>
            !search ||
            data.roleName.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
    >
      <el-table-column
        v-for="item in tableList"
        :key="item.label"
        :label="item.label"
        :prop="item.props"
      >
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.state"
            active-color="#45afdd"
            inactive-color="#ff4949"
            active-text="启用"
            inactive-text="禁用"
            @change="switchChange(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column align="center">
        <template slot="header">
          <el-input
            v-model="search"
            size="medium "
            placeholder="输入角色名称搜索"
          />
        </template>
        <template slot-scope="scope">
          <el-button
            size="small "
            icon="el-icon-share"
            type="primary"
            @click="handleEdit(scope.row)"
            >菜单权限</el-button
          >
          <el-button
            size="small"
            icon="el-icon-edit"
            type="success"
            @click="setUserNameData(scope.row)"
            >基本信息</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <!--  hide-on-single-page -->
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="sizeChange"
        @current-change="currentChange"
        :page-sizes="[10, 30, 50, 100]"
        hide-on-single-page
      >
      </el-pagination>
    </div>
    <el-drawer
      title="关联菜单"
      :visible.sync="drawer"
      :direction="direction"
      :before-close="handleClose"
      :wrapperClosable="false"
    >
      <div class="tree">
        <el-tree
          :data="menuList"
          :props="props"
          node-key="id"
          show-checkbox
          @check-change="handleCheckChange"
          @check="handleNodeClick"
          @getCurrentKey="getCurrentKey"
          ref="tree"
        >
        </el-tree>
      </div>
      <div class="but">
        <el-button size="small" type="primary" @click="addRoleMenuData"
          >确认</el-button
        >
        <el-button size="small" @click="drawer = false">取消</el-button>

        
      </div>
    </el-drawer>
    <!-- 修改基本信息 -->
    <!-- 新增编辑 -->
    <el-dialog
      :close-on-click-modal="false"
      :title="titles"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <div class="dialogSpan">
        <el-form label-width="200px" label-position="right">
          <el-form-item label="角色名称">
            <el-input v-model="editForm.roleName" />
          </el-form-item>
          <el-form-item label="角色描述">
            <el-input type="" v-model="editForm.remark" />
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="getCommit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const { mapActions } = createNamespacedHelpers("menu");
export default {
  components: {},
  data() {
    return {
      editForm: {
        roleName: "",
        remark: "",
        id: "",
        state: 1,
      },
      dialogVisible: false,
      drawer: false,
      direction: "rtl",
      titles: "新增角色",
      tableList: [
        { label: "角色描述", props: "remark" },
        { label: "角色名称", props: "roleName" },
      ],
      tableData: [],
      search: "",
      props: {
        label: "menuName",
        children: "vos",
      },
      count: 1,
      page: {
        current: 1,
        size: 10,
      },
      total: 0,
      menuList: "",
      roleMenu: [],
      roleId: "",
    };
  },
  created() {
    this.getRoleList();
    this.getMenuListData();
  },
  methods: {
       ...mapActions(['getMenuData']),
    handleEdit(row) {
      this.drawer = true;
      this.roleId = row.id;
      this.getMenuListByRoleIdData(row.id);
    },
    /* 修改角色信息 */
    setUserNameData(row) {
      this.editForm = { ...row };
      this.titles = "编辑角色";
      this.dialogVisible = true;

      console.log(row);
    },
    /* 添加角色 */
    async handleDelete() {
      this.titles = "新增角色";
      this.editForm = {
        roleName: "",
        remark: "",
        id: "",
        state: 1,
      };
      this.dialogVisible = true;
    },
    /* 打开编辑或者新增模态框 */
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
          this.editForm = {
            roleName: "",
            remark: "",
            id: "",
            state: 1,
          };
        })
        .catch((_) => {});
    },
    /*树形控件方法 */
    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate, "2222222222222222");
      /* 获取选中所有节点方法 */
  
    },
    handleNodeClick(data) {
      console.log(data, "111111111111111111");
      console.log(this.$refs.tree.getCheckedKeys(), "3333333333333333");
    },
    /* 获取当前被选中的菜单 */
    getCurrentKey(val) {
      console.log(this.$refs.tree.getCheckedKeys(), "3333333333333333");
    },
    /* 获取角色列表 */
    async getRoleList() {
      const { data } = await this.$api.role.getRoleList(this.page);
      console.log(data);
      this.total = data.data.total;
      this.tableData = data.data.records;
      console.log(data.data.records);
    },
    /* 新增或者编辑角色 */
    async getCommit() {
      if (this.titles === "新增角色") {
        const { data } = await this.$api.role.addRole(this.editForm);
        console.log(data);
        if (data.code == 200) {
          this.dialogVisible = false;
          this.editForm = {
            roleName: "",
            remark: "",
            id: "",
            state: 1,
          };
          this.getRoleList();
        }
      } else if (this.titles === "编辑角色") {
        const { data } = await this.$api.role.updatedRole(this.editForm);
        console.log(data);
        if (data.code == 200) {
          this.dialogVisible = false;
          this.editForm = {
            roleName: "",
            remark: "",
            id: "",
            state: 1,
          };
          this.getRoleList();
        }
      }
    },
    /* 启用禁用 */
    async switchChange(val) {
      console.log(val);
      const { data } = await this.$api.role.updateRoleStatus({
        id: val.id,
        state: val.state,
      });
      console.log(data);
    },
    /* 分条 */
    sizeChange(val) {
      console.log(val);
      this.page.size = val;
      this.getRoleList();
    },
    /* 分页 */
    currentChange(val) {
      this.page.current = val;
      console.log(val);
      this.getRoleList();
    },

    /* 获取菜单列表 */
    async getMenuListData() {
      const { data } = await this.$api.menu.getMenuList();
      console.log(data, "菜单列表");
      this.menuList = data.data;
    },
    /* 获取角色关联的菜单 */
    async getMenuListByRoleIdData(id) {
      const { data } = await this.$api.role.getMenuListByRoleId({
        roleId: id,
      });

 let roleMenu=[]
     for (let index = 0; index < data.data.length; index++) {
       if (data.data[index].vos.length) {
          for (let i = 0; i < data.data[index].vos.length; i++) {
            roleMenu.push(data.data[index].vos[i].id)
          }
        }else{
          roleMenu.push(data.data[index].id)
        }
      } 
      console.log(roleMenu,'器');
       this.$refs.tree.setCheckedKeys(roleMenu,false);
           /*   console.log(data,'角色关联的菜单'); */
       /*   this.$refs.tree.setCheckedNodes(data.data) */
    },
    /* 关联角色菜单 */
    async addRoleMenuData() {
      let arr=[]
      /* 获取所有的关联节点 包含父节点 */
      let menuArr= this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys()) 
      for (let i = 0; i < menuArr.length; i++) {
        arr.push(
          {
            roleId:this.roleId,
            menuId:menuArr[i]
          }
        )
        
      }
      const { data } = await this.$api.role.addRoleMenu(arr); 
      if (data.code == 200) {
        this.getMenuData()
        this.$message.success("菜单关联成功");
        this.drawer = false;
      }
      console.log(data);
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
.tree {
  padding-left: 20px;
}
</style>