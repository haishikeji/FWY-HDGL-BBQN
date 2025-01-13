<template>
	<view>
		<!-- 自定义头部 -->
		<uni-nav-bar leftIcon='left' @clickLeft='buckHome()' color="#000000" fixed :title="barTitle" statusBar
			:border='false' style="size_Title"></uni-nav-bar>
		<template>
			<view>
				<view class="min ">
					<view class="up_img min_box">
						<view class="up_text_box">
							<view class="up_title">
								头像<text class="xingxing">*</text>
							</view>
							<view class="up_text">
								请上传正装照片，将用于您在平台的所有活动！
							</view>
						</view>
						<view class="up_image_box">
							<template>
								<u-upload @afterRead="afterReadB" @delete="deletePicB" name="1" multiple sizeType="['compressed']"
									:maxCount="1" width="126rpx" height="126rpx" class="up_img_active">
									<image class="up_img_active" :src="user.avatar" mode="" v-if="user.avatar">
									</image>
									<image class="up_img_active" src="../../static/upimg/up.png" mode="" v-else></image>
								</u-upload>
							</template>
						</view>
					</view>
					<view class="name_box min_box">
						<view class="name_box_left">
							姓名<text class="xingxing">*</text>
						</view>
						<view class="name_box_right">
							<input type="text" v-model="user.nickname" placeholder="可以填写中文名或英文名" />
						</view>
					</view>
					<view class="name_box min_box">
						<view class="name_box_left">
							邮箱
						</view>
						<view class="name_box_right">
							<input type="text" v-model="user.email" placeholder="请填写邮箱" />
						</view>
					</view>
					<view class="name_box min_box">
						<view class="name_box_left">
							手机<text class="xingxing">*</text>
						</view>
						<view class="name_box_right">
							<input type="number" v-model="user.userPhone" placeholder="请填写手机号" />
						</view>
					</view>
					<view class="name_box min_box">
						<view class="name_box_left">
							您的性别<text class="xingxing">*</text>
						</view>
						<view class="name_box_right name_box_right_radio">

							<radio-group @change="radioChange">
								<view class="radioChange">

									<radio :value="1" color="#28519B" :checked="user.gender=='1'" /><text>男</text>

									<radio :value="2" color="#28519B" :checked="user.gender=='2'" /><text>女</text>

								</view>
							</radio-group>

						</view>
					</view>
					<picker class="name_box_right_radio" mode="date" :value="date" :start="startDate" @change="bindDateChange">
						<view class="name_box min_box">
							<view class="name_box_left">
								生日
							</view>
							<view class="radio_box">

								<view class="radio_title">
									<text v-if="user.birth">{{user.birth}}</text>
									<text v-else>请选择</text>
								</view>
								<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>
							</view>
						</view>
					</picker>
					<view class="name_box min_box" @click="open(profession,'请选择从事行业')">
						<view class="name_box_left">
							你从事的行业
						</view>

						<view class="radio_box">
							<view class="radio_title">
								<template v-for="item in  user.professionIds">
									<text :key='item'>{{professionData(item,1)}}</text>
								</template>
							</view>
							<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>
						</view>
					</view>
					<picker @change="bindPickerChange" mode='selector' :range="education" range-key='name'>
						<view class="name_box min_box">
							<view class="name_box_left">
								您的最高学历
							</view>

							<view class="radio_box">


								<view class="radio_title">
									<template v-for="item in  education">
										<text :key='item.id' v-if="item.id==user.educationId">{{item.name}}</text>
									</template>
								</view>
								<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>
							</view>
						</view>
					</picker>
					<view class="name_box min_box">
						<view class="name_box_left">
							毕业院校
						</view>
						<view class="name_box_right">
							<input type="text" placeholder="可多个，用逗号隔开" v-model="user.school" />
						</view>
					</view>
					<view class="name_box min_box" @click="open(identity,'请选择身份')">
						<view class="name_box_left">
							您的当前身份
						</view>

						<view class="radio_box">

							<view class="radio_title">
								<template v-for="item in  user.identityIds">
									<text :key='item'>{{professionData(item,2)}}</text>
								</template>
							</view>
							<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>

						</view>


					</view>
					<view class="name_box min_box" @click="open(hobby,'请选择兴趣爱好')">
						<view class="name_box_left">
							您的兴趣爱好
						</view>
						<view class="radio_box">
							<view class="radio_title">
								<template v-for="item in  user.hobbyId">
									<text :key='item'>{{professionData(item,3)}}</text>
								</template>
							</view>
							<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>
						</view>

					</view>
					<view class="name_box min_box">
						<view class="name_box_left">
							企业名称
						</view>
						<view class="name_box_right">
							<input type="text" placeholder="至少填写一项" v-model="user.enterprise" />
						</view>
					</view>
					<view class="name_box min_box">
						<view class="name_box_left">
							职务
						</view>
						<view class="name_box_right">
							<input type="text" v-model="user.profession" placeholder="至少填写一项" />
						</view>
					</view>
					<view class="textArea">
						<view class="name_box_left">
							你希望在青年获得什么?
						</view>
						<view class="name_box_right">
							<textarea class="textarea_txt" v-model="user.reward" :maxlength="200" placeholder="请填写" />
						</view>
					</view>
					<view class="up_img min_box">
						<view class="up_text_box">
							<view class="up_title">
								上传企业营业执照（非必填）
							</view>
							<view class="up_text">
								点击图片更换
							</view>
						</view>
						<view class="up_image_box">
							<template>
								<u-upload @afterRead="afterRead" @delete="deletePic" name="1" multiple sizeType="['compressed']"
									:maxCount="1" width="126rpx" height="126rpx">
									<image class="yingyezhizhao" :src="user.license" v-if="user.license">
									</image>
									<image class="yingyezhizhao" src="../../static/upimg/up.png" v-else></image>
								</u-upload>
							</template>
						</view>
					</view>
					<view class="textArea">
						<view class="name_box_left">
							企业/个人介绍
						</view>
						<view class="name_box_right">
							<textarea class="textarea_txt" v-model="user.enterpriseDesc" :maxlength="200" placeholder="请填写" />
						</view>
					</view>
				</view>
			</view>
		</template>
		<uni-popup ref="popup" type="bottom" :safe-area='false'>

			<view class="popup">
				<checkbox-group @change="professionIdsCheng">
					<view class="popup_title">
						<text class="popup_title_center">{{popupTitle}}</text>
						<text class="popup_queding" @click="close()">确定</text>
					</view>
					<scroll-view class="check_div" scroll-y>

						<view class="check_box" v-for="item in propData" :key='item.id'>
							<checkbox :value="item.id" color="#007AFF">
								<view class="check_box_text">{{item.name}}</view>
							</checkbox>
						</view>


					</scroll-view>
				</checkbox-group>
			</view>
		</uni-popup>
		<view class="vip_tjr" v-if="recommendNum">
			<view class="">
				入会推荐人:
			</view>
			<view class="">
				{{recommendNum}}
			</view>
		</view>
		<template>
			<view class="but">
				<button type="default" class="button" @click="updateAppUser()" v-if="code==1">保存</button>
				<button type="default" class="button" @click="addVpi()" v-else-if="code==0">立即提交</button>
			</view>
		</template>
	</view>
</template>

<script>
	import {
		BASE_URL
	} from '../../http/const.js'
	export default {
		data() {
			return {
				recommendNum: '',
				propid: '',
				propData: '',
				fileList1: [],
				popupTitle: '请选择从事行业',
				listStyles: {
					height: 152, // 边框高度
					width: 108,
					// 是否显示边框
					border: false,
					// 是否显示分隔线
					dividline: false,
				},
				imageValue: '',
				array: ['中国', '美国', '巴西', '日本'],
				index: 0,
				user: {
					/* 头像 */
					avatar: '',
					/* 出生年月 */
					birth: "",
					/* 毕业院校 */
					education: "",
					/* 学历id */
					educationId: '',
					/* 邮箱 */
					email: "",
					/* 企业名称 */
					enterprise: '',
					/* 企业描述 */
					/*性别  */
					gender: 0,
					/* 兴趣爱好 */
					hobbyId: [],
					/* 身份id */
					identityIds: [],
					/* 用户名称 */
					nickname: '',
					/* 职务 */
					profession: '',
					/* 行业 */
					professionIds: [],
					/*手机  */
					userPhone: '',
					/* 毕业院校 */
					school: '',
					/* 营业执照 */
					license: "",
					/* 希望获得什么 */
					reward: "",
					/* 推荐人 */
					recommender: '',
					/* 企业描述 */
					enterpriseDesc: "",

				},
				/* 行业 */
				profession: [],
				/* 兴趣爱好 */
				hobby: [],
				/* 身份 */
				identity: [],
				/* 学历 */
				education: [],
				/* 推荐人姓名 */
				value: '',
				code: '',
				barTitle: '',
				renewal: false,
				id: ''
			};
		},
		onLoad(option) {
			this.code = option.code
			if (this.code == 1) {
				this.barTitle = '编辑个人资料'
			} else {
				this.barTitle = '填写入会资料'
			}
			let user = uni.getStorageSync('userInfo')
			this.getDictData()
			if (user) {
				let {
					avatar,
					birth,
					email,
					enterprise,
					enterpriseDesc,
					gender,
					license,
					nickname,
					openId,
					profession,
					recommendNum,
					recommender,
					reward,
					school,
					userPhone,
					educationId,
					/* 		education, */
					identityIds,
					professionIds,
					hobbyId,
					id,
					member
				} = JSON.parse(user)
				this.user.avatar = avatar;
				this.user.birth = birth;
				/* 	this.user.education = education; */
				this.user.educationId = educationId;
				this.user.email = email;
				this.user.identityIds = identityIds;
				this.user.enterprise = enterprise;
				this.user.gender = gender;
				this.user.nickname = nickname;
				this.user.hobbyId = hobbyId;
				this.user.profession = profession;
				this.user.professionIds = professionIds;
				this.user.userPhone = userPhone;
				this.user.school = school;
				this.user.license = license;
				this.user.enterpriseDesc = enterpriseDesc;
				this.user.id = id;
				this.user.recommender = recommender;
				this.recommendNum = recommendNum;
				this.renewal = member;
			}
			/* var arr = Object.values(this.user)
			console.log(arr); */
		},
		methods: {
			buckHome() {
				uni.navigateBack({
					delta: 1
				})
			},
			/* 获取字典 */
			async getDictData() {
				const data = await this.$api.home.getDict({
					codes: ''
				})
				console.log(data, '字典');
				this.identity = data.data.filter(item => item.code === "6")[0].voList
				this.hobby = data.data.filter(item => item.code === "9")[0].voList
				this.profession = data.data.filter(item => item.code === "8")[0].voList
				this.education = data.data.filter(item => item.code === "10")[0].voList
			},
			/*  */
			/* 打开多选 */
			open(data, name) {
				this.popupTitle = name
				this.propData = data
				this.$refs.popup.open()
			},
			close() {
				this.$refs.popup.close()
			},
			/* 选择性别 */
			radioChange(v) {
				this.user.gender = v.detail.value - 0
				console.log(this.user.gender);
			},
			/* 选择生日 */
			bindDateChange(v) {
				console.log(v);
				this.user.birth = v.detail.value
			},

			/* 选择特征 */
			professionIdsCheng(v) {
				console.log(this.value, '3333333333333333333');
				if (this.popupTitle === '请选择从事行业') {
					this.user.professionIds = v.detail.value
				}
				if (this.popupTitle === '请选择身份') {
					this.user.identityIds = v.detail.value
				}
				if (this.popupTitle === '请选择兴趣爱好') {
					this.user.hobbyId = v.detail.value
				}
				console.log(v);
			},
			// 删除图片
			deletePic(event) {
				this[`fileList${event.name}`].splice(event.index, 1)
				this.user.license = ''
			},
			deletePicB(event) {
				this[`fileList${event.name}`].splice(event.index, 1)
				this.user.avatar = ''
			},
			// 新增图片
			async afterRead(event) {
				// 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
				let lists = [].concat(event.file)
				let fileListLen = this[`fileList${event.name}`].length
				lists.map((item) => {
					this[`fileList${event.name}`].push({
						...item,
						status: 'uploading',
						message: '上传中'
					})
				})
				for (let i = 0; i < lists.length; i++) {
					const result = await this.uploadFilePromise(lists[i].url)
					let item = this[`fileList${event.name}`][fileListLen]
					this[`fileList${event.name}`].splice(fileListLen, 1, Object.assign(item, {
						status: 'success',
						message: '',
						url: result
					}))
					fileListLen++
				}

			},
			// 新增图片
			async afterReadB(event) {
				let vm = this;
				// 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
				let lists = [].concat(event.file)
				let fileListLen = vm[`fileList${event.name}`].length
				lists.map((item) => {
					vm[`fileList${event.name}`].push({
						...item,
						status: 'uploading',
						message: '上传中'
					})
				})
				console.log(lists)
				for (let i = 0; i < lists.length; i++) {
					const result = await vm.uploadFilePromiseB(lists[i].url)
					let item = vm[`fileList${event.name}`][fileListLen]
					console.log(item)
					vm[`fileList${event.name}`].splice(fileListLen, 1, Object.assign(item, {
						status: 'success',
						message: '',
						url: result
					}))
					fileListLen++
				}

			},
			uploadFilePromise(url) {
				let token = uni.getStorageSync("token");
				return new Promise((resolve, reject) => {
					let a = uni.uploadFile({
						url: BASE_URL +
							'/oss/open/v1/getUploadFileUrl?x-oss-process=image/resize,p_80', // 仅为示例，非真实的接口地址
						filePath: url,
						header: {
							"Authorization": token
						},
						name: 'file',
						formData: {
							user: 'test'
						},
						success: (res) => {
							if (res.statusCode === 200) {

								let a = JSON.parse(res.data)
								this.user.license = a.data
								console.log(this.user.license);
								resolve(res.data.data)



							}
						}
					});
				})
			},
			uploadFilePromiseB(url) {
				let token = uni.getStorageSync("token");
				console.log(BASE_URL +
							'/oss/open/v1/getUploadFileUrl?x-oss-process=image/resize,p_80')
				return new Promise((resolve, reject) => {
					uni.uploadFile({
						url: BASE_URL +
							'/oss/open/v1/getUploadFileUrl?x-oss-process=image/resize,p_80', // 仅为示例，非真实的接口地址
						filePath: url,
						header: {
							"Authorization": token
						},
						name: 'file',
						formData: {
							user: 'test'
						},
						success: (res) => {
							if (res.statusCode === 200) {

								let a = JSON.parse(res.data)
								this.user.avatar = a.data
								console.log(this.user.avatar);
								resolve(res.data.data)



							}
						}
					});
				})
			},
			/* 更新用户信息 */
			async updateAppUser() {
				let {
					gender,
					avatar,
					birth,
					email,
					userPhone,
					nickname,
					enterprise,
					school,
					profession,
					educationId
				} = this.user

				if (!avatar) {
					return uni.$u.toast('请先上传头像哦~');
				}
				if (!nickname) {
					return uni.$u.toast('请填写您的姓名~');

				}
				if (!userPhone) {
					return uni.$u.toast('请填写您的联系方式~');
				}

				if (!gender) {
					return uni.$u.toast('请选择您的性别~');
				}

				const data = await this.$api.user.updateAppUser(this.user)
				console.log(data, '????????????????????');
				if (data.code == 200) {
					uni.$u.toast('修改成功');
					setTimeout(() => {
						uni.switchTab({
							url: './my'
						})
					}, 1000)
				}
			},
			/* 学历 */
			bindPickerChange(v) {
				console.log(v);
				let index = v.detail.value - 0
				this.user.educationId = this.education[index].id
			},
			/* 特征数据渲染 */
			professionData(id, i) {

				let a = ''
				if (i === 1) {
					a = this.profession.filter(item => item.id == id).map(item => item.name + ' , ').join('');
					return a
				}
				if (i === 2) {
					a = this.identity.filter(item => item.id == id).map(item => item.name + ' , ').join('');
					return a
				}
				if (i === 3) {
					a = this.hobby.filter(item => item.id == id).map(item => item.name + ' , ').join('');
					return a
				}
			},
			/* 提交入会 */
			addVpi() {
				let {
					gender,
					avatar,
					birth,
					email,
					userPhone,
					nickname,
					enterprise,
					school,
					profession,
					educationId
				} = this.user

				if (!avatar) {
					return uni.$u.toast('请先上传头像哦~');
				}
				if (!nickname) {
					return uni.$u.toast('请填写您的姓名~');
				}
				if (!userPhone) {
					return uni.$u.toast('请填写您的联系方式~');
				}
				if (!gender) {
					return uni.$u.toast('请选择您的性别~');
				}
				if (this.renewal == 0 || this.renewal == 2) {
					this.user.renewal = false
				} else if (this.renewal >= 3) {
					this.user.renewal = true
				} else {
					return uni.$u.toast('请勿重复购买');
				}
				delete this.user.userCode
				delete this.user.disabled
				delete this.user.member
				delete this.user.userFeatures
				delete this.user.recommendNum
				delete this.user.recommendName
				delete this.user.professionList
				delete this.user.recommendName
				delete this.user.hobbyList
				delete this.user.educationList
				delete this.user.identityList
				delete this.user.hobbyList
				delete this.user.featureName
				delete this.user.joinTime
				delete this.user.memberRank
				delete this.user.excellentMember
				delete this.user.num
				delete this.user.dreamerRecommend
				delete this.user.excellentRecommend
				delete this.user.deadline
				uni.setStorageSync('ADD', JSON.stringify(this.user))
				uni.navigateTo({
					url: `../order/order`
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.min {
		padding: 0 30rpx;
	}

	.up_img {
		height: 152rpx;
		display: flex;
		align-items: center;
		padding-top: 15rpx;
		box-sizing: border-box;

	}

	.min_box {
		/* width: 100%; */
		border-bottom: 2rpx solid #E5E5E5;
		box-sizing: border-box;
	}

	.up_img_active {
		width: 108rpx;
		height: 112rpx;
		border-radius: 50%;
	}

	.up_text_box {
		.up_title {
			font-size: 28rpx;
		}

		.up_text {
			font-size: 25rpx;
			color: #C5C8C9;
			width: 520rpx;
		}
	}

	.name_box {
		height: 116rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
		font-size: 28rpx;

		.name_box_left {
			width: 200rpx;
		}
	}

	.name_box_right_radio {
		display: flex;
		align-items: center;
		flex-grow: 1;

		.radioChange {

			padding-left: 30rpx;
			margin-left: 225rpx;
			box-sizing: border-box;

			radio {
				margin-left: 30rpx;
			}
		}
	}

	.radio_title {
		width: 250rpx;
		font-size: 28rpx;
		text-align: right;
		flex-grow: 1;
		padding: 0 30rpx;
		box-sizing: border-box;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	.radio_box {

		width: 500rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
		box-sizing: border-box;
	}

	/deep/.input-placeholder {
		color: #C5C8C9;
	}

	.radio_image {
		width: 20rpx;
		height: 25rpx;
	}

	.textArea {
		padding: 20rpx 0rpx;
		height: 204rpx;
		border-bottom: 2rpx solid #E5E5E5;

		.textarea_txt {
			height: 160rpx;
			padding-top: 20rpx;
		}
	}

	.name_box_right {
		flex-grow: 1;
	}

	input {
		width: 100%;

		text-align: right;
	}

	.yingyezhizhao {
		width: 126rpx;
		height: 126rpx;
	}

	.popup {
		width: 750rpx;
		height: 500rpx;
		border-radius: 32rpx;
		background-color: #FFFFFF;

		.popup_title {
			display: flex;
			justify-content: space-between;
			line-height: 114rpx;
			padding: 0 52rpx;
			border-bottom: 2rpx solid #E5E5E5;
			box-sizing: border-box;
		}

		.quxiao {
			font-size: 32rpx;
			color: #2D3235;
		}

		.popup_title_center {
			font-size: 32rpx;
			font-weight: 400;
			color: #2D3235;
		}

		.popup_queding {
			font-size: 32rpx;
			color: #2D3235;
		}
	}

	.check_div {
		height: 350rpx;
		padding: 30rpx 52rpx;
		box-sizing: border-box;
	}

	.check_box {
		width: 100%;
		margin: 30rpx auto;

		display: flex;
		align-items: center;

	}

	.check_box_text {
		display: inline-block;
		margin-left: 20rpx;
	}

	.but {
		width: 750rpx;
		height: 166rpx;
		background-color: #FFFFFF;
		text-align: center;
		line-height: 80rpx;
		padding-top: 12rpx;

		border-top: 2rpx solid #E5E5E5;

		.button {
			width: 500rpx;
			height: 80rpx;
			background: #28519B;
			border-radius: 40rpx;
			font-size: 32rpx;
			color: #FFFFFF;



		}
	}

	.vip_tjr {
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 122rpx;
		color: #2D3235;
		font-size: 32rpx;
		padding: 0 34rpx;
	}

	.xingxing {
		color: #E43D33;
	}
</style>