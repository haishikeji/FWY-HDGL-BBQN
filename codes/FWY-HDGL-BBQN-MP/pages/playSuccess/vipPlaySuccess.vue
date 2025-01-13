<template>
	<view>
		<!-- 自定义头部 -->
		<uni-nav-bar leftIcon='left' @clickLeft='buckHome()' color="#000000" fixed title="报名成功" statusBar
			:border='false'></uni-nav-bar>
			<view class="image">
				<image src="@/static/my/dui.png" mode=""></image>
			</view>
			<view class="play_text">
				<text>参与成功</text>
				
			</view>
			<view class="play_detail">
				   你是第{{activeInfo.participate}}位报名的会员，感谢您对本次活动的鼎力支持！
			</view>
		<view class="header">
			<image :src="bannerList" mode="" class="cover"></image>
			<view class="header_text">
				<view class="header_text_title">
					{{activeInfo.name}}
				</view>
				<view class="header_text_text">
					<text>活动日期:</text><text> {{activeInfo.activityDate}}</text>
				</view>
				<view class="header_text_text">
					<text>活动时间:</text><text>{{activeInfo.beginTime}}~{{activeInfo.endTime}}</text>
				</view>
			</view>
		</view>
		<view class="c_t_user_active">
			<view class="user_num">
				<image :src="item.avatar" mode="" v-for="item in userActive" :key="item.id" class="avatar"></image>
				<view class="avatar_view">
					...
				</view>
			</view>
			<view class="">
				{{activeInfo.participate}}人已经报名
			</view>
		</view>
			<view class="border">
				
			</view>
			<view class="content">
			
				<button type="default"  open-type="share" class="buta">邀请好友一起参与</button>
				<button type="default" class="but_b"  @click="linkActive">返回首页</button>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo:'',
				activeId:'',
				activeInfo:'',
				userActive:'',
				bannerList:""
			};
		},
		onLoad(option) {
			let user=uni.getStorageSync('userInfo')
				this.activeId=option.id
			if(user){
				this.userInfo=user
			}
			this.getActivityInfo(option.id)
		},
		onShareAppMessage() {
					return {
						title:'青年',
						path: `pages/activeInfo/activeInfo?id=${this.activeId}&userId${this.userInfo.id}`,
						imageUrl:'../../static/csimg/propcs.png'
					}
				},
				
		methods:{
			
			/* 获取活动详情*/
			async getActivityInfo(id) {
				const {
					data
				} = await this.$api.active.getActivInof({
					id
				})
				console.log(data,'11111111111111');
				this.activeInfo = data;
				if (data.userApp) {
					this.userActive = data.userApp.slice(0, 4)
				}
				if (data.cover) {
					this.bannerList = data.cover.split(',')[0];
				}
			},
			buckHome(){
				uni.switchTab({
					url:'../index/index'
				})
			},
			/* 跳转活动 */
			linkActive(){
				uni.switchTab({
					url:'../index/index'
				})
			}
		}
		
	}
</script>

<style lang="scss" scoped>
	.c_t_user_active {
		width: 686rpx;
		height: 88rpx;
		background: #F8F8F8;
		border-radius: 16rpx 16rpx 16rpx 16rpx;
		opacity: 1;
		margin: 38rpx auto 46rpx auto;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0 26rpx;
		box-sizing: border-box;
	}
	
	.avatar {
		width: 60rpx;
		height: 60rpx;
		border-radius: 50%;
		opacity: 1;
		border: 2rpx solid #FFFFFF;
		margin-left: -10rpx;
	}

	.user_num {
		display: flex;
		align-items: center;

	}

	.avatar_view {
		width: 60rpx;
		height: 60rpx;
		font-size: 30rpx;
		font-weight: bold;
		letter-spacing: 5rpx;
		text-align: center;
		border-radius: 50%;
		background: #28519B;
		opacity: 1;
		color: #FFFFFF;
		border: 2rpx solid #FFFFFF;
	}
.image{
	width: 750rpx;
	text-align: center;
	margin-top: 100rpx;
	
	image{
		width: 136rpx;
		height: 136rpx;
	}
}
.play_text{
	width: 750rpx;
	font-weight: bold;
	color: #2D3235;
	text-align: center;
	line-height: 44rpx;	
	margin-bottom: 50rpx;
}
.play_detail{
	box-sizing: border-box;
	margin: 0 auto 0rpx auto;
	width: 692rpx;
	height: 100rpx;
	font-size: 28rpx;
	color: #636669;
	display: flex;
	justify-content: center;
}
.buta{
	width: 492rpx;
	height: 80rpx;
	border-radius: 40rpx;
	border: 2rpx solid #28519B;
	font-size: 32rpx;
	color: #28519B;
}
.but_b{
	width: 492rpx;
	height: 80rpx;
	border-radius: 40rpx;
	border: 2rpx solid #E5E5E5;
	font-size: 32rpx;
	color: #2D3235;
	margin-top: 16rpx;
}
.look_order{
	color: #28519B;
	border-bottom: 1rpx solid #28519B;
}
.content_box{
	width: 100%;
	text-align: center;
}
.content_box_img{
	width: 280rpx;
	height: 281rpx;
}
.content{
	height: 906rpx;
	padding-top: 106rpx;
	box-sizing: border-box;
}
.border{
	width: 750rpx;
	height: 20rpx;
	background: #F5F5F5;
}
.content_box_text{
	margin: 12rpx 0 86rpx 0;
	font-size: 28rpx;
	font-weight: 400;
	color: #636669;
	
	
	
}
	.header {
		width: 100%;
		display: flex;
		box-sizing: border-box;
		padding: 32rpx;
		border-top:#E5E5E5 2rpx solid ;

		.cover {
			width: 200rpx;
			height: 200rpx;
			border-radius: 16rpx;
			opacity: 1;
		}

		.header_text_title {
			font-size: 32rpx;
			font-weight: bold;
			margin-bottom: 18rpx;
		}

		.header_text {
			margin-left: 30rpx;
			padding-top: 10rpx;
			box-sizing: border-box;

			.header_text_text {
				margin-bottom: 8rpx;
				font-size: 28rpx;

				text:nth-child(1) {
					color: #9B9FA0;
				}

				text:nth-child(2) {
					color: #2D3235;
				}
			}
		}
	}
</style>