<template>
	<view>
		<uni-nav-bar leftIcon='left' @clickLeft='buckHome()' fixed title="填写入会资料" statusBar :border='true'
			color='#000000'></uni-nav-bar>
		<view class="min ">
			<view class="up_img min_box">
				<view class="up_text_box">
					<view class="up_title">
						头像
					</view>
					<view class="up_text">
						请上传正装照片，将用于您在平台的所有活动！
					</view>
				</view>
				<view class="up_image_box">
					<uni-file-picker class="uni-file-picker" v-model="imageValue" fileMediatype="image" mode="grid"
						@select="select" @progress="progress" @success="success" @fail="fail" :limit="1"
						:image-styles='listStyles'>
						<image class="up_img_active" src="../../static/upimg/up.png" mode=""></image>
					</uni-file-picker>
				</view>
			</view>
			<view class="name_box min_box">
				<view class="name_box_left">
					姓名
				</view>
				<view class="name_box_right">
					<input type="text" value="" placeholder="可以填写中文名或英文名" />
				</view>
			</view>
			<view class="name_box min_box">
				<view class="name_box_left">
					您的性别
				</view>
				<view class="name_box_right name_box_right_radio">
					<view class="radio_title">
						一旦选定不可更改
					</view>
					<radio-group @change="radioChange">
						<view class="radioChange">

							<radio value="1" color="#28519B" :checked="user.gender=='1'" /><text>男</text>

							<radio value="2" color="#28519B" :checked="user.gender=='2'" /><text>女</text>

						</view>
					</radio-group>

				</view>
			</view>
			<view class="name_box min_box">
				<view class="name_box_left">
					生日
				</view>
				<picker class="name_box_right_radio" mode="date" :value="date" :start="startDate"
					@change="bindDateChange">
					<view class="radio_box">

						<view class="radio_title">
							一旦选定不可更改
						</view>
						<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>

					</view>
				</picker>
			</view>

			<view class="name_box min_box">
				<view class="name_box_left">
					你从事的行业
				</view>
				<picker class="name_box_right_radio" :value="index" :range-key='name' :range="array"
					@change="bindDateChange">
					<view class="radio_box">

						<view class="radio_title">
							一旦选定不可更改
						</view>
						<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>

					</view>

				</picker>
			</view>

			<view class="name_box min_box">
				<view class="name_box_left">
					你的最高学历
				</view>
				<picker class="name_box_right_radio" :value="index" :range-key='name' :range="array"
					@change="bindDateChange">
					<view class="radio_box">

						<view class="radio_title">
							含目前在读
						</view>
						<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>

					</view>

				</picker>
			</view>


			<view class="name_box min_box">
				<view class="name_box_left">
					毕业院校
				</view>
				<view class="name_box_right">
					<input type="text" value="" placeholder="可多个，用逗号隔开" />
				</view>
			</view>
			<view class="name_box min_box">
				<view class="name_box_left">
					你的当前身份
				</view>
				<picker class="name_box_right_radio" :value="index" :range-key='name' :range="array"
					@change="bindDateChange">
					<view class="radio_box">
			
						<view class="radio_title">
							请选择当前身份
						</view>
						<image src='../../static/upimg/fh.png' class="radio_image" mode=""></image>
			
					</view>
			
				</picker>
			</view>
			<view class="name_box min_box">
				<view class="name_box_left">
					您的兴趣爱好
				</view>
				<view class="name_box_right">
					<input type="text" value="" placeholder="至少填写一项" />
				</view>
			</view>
			<view class="name_box min_box">
				<view class="name_box_left">
					企业名称
				</view>
				<view class="name_box_right">
					<input type="text" value="" placeholder="至少填写一项" />
				</view>
			</view>
			<view class="name_box min_box">
				<view class="name_box_left">
					职务
				</view>
				<view class="name_box_right">
					<input type="text" value="" placeholder="至少填写一项" />
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				listStyles: {
					height: 152, // 边框高度
					width: 108,
					// 是否显示边框
					border: false,
					// 是否显示分隔线
					dividline: false,
				},
				array: ['中国', '美国', '巴西', '日本'],
				index: 0,
			};
		},
		methods: {
			buckHome() {
				uni.navigateBack({
					delta: 1
				})
			}
		},
		select(e) {
			console.log('选择文件：', e)
		},
		// 获取上传进度
		progress(e) {
			console.log('上传进度：', e)
		},

		// 上传成功
		success(e) {
			console.log('上传成功')
		},

		// 上传失败
		fail(e) {
			console.log('上传失败：', e)
		}
	}
</script>

<style lang="scss" scoped>
	.min {
		padding-left: 32rpx;
	}

	.up_img {
		height: 152rpx;
		display: flex;
		align-items: center;
		padding-top: 15rpx;
		box-sizing: border-box;

	}

	.min_box {
		width: 100%;
		border-bottom: 2rpx solid #E5E5E5;
		box-sizing: border-box;
	}

	.up_img_active {
		width: 108rpx;
		height: 112rpx;

	}

	.up_text_box {
		.up_title {
			font-size: 28rpx;
		}

		.up_text {
			font-size: 25rpx;
			color: #C5C8C9;

		}
	}

	.name_box {
		height: 116rpx;
		display: flex;
		align-items: center;
		font-size: 28rpx;

		.name_box_left {
			width: 200rpx;

		}

	}

	.name_box_right_radio {
		display: flex;
		align-items: center;
		flex-grow: 1;

		.radio_title {
			font-size: 28rpx;
			color: #C5C8C9;
		}

		.radioChange {
			radio {
				margin-left: 20rpx;
			}
		}
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
</style>
