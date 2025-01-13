import request from '../request.js';
import {
	GETUSERMEMBERAUTH,
	MEMBERPAY,
	MINICOMMODITINFO,
	REGISTERPLAY,
	SHOWER
} from "../const.js"
const vip = {
	/* 获取会员权限 */
	getUserMemberAuth(data) {
		return request({
			url: GETUSERMEMBERAUTH,
			data
		})
	},
	/* 提交购买 */

	memberPayAsync(data) {
		return request({
			url: MEMBERPAY,
			method: 'POST',
			data
		})
	},
	/* 获取商品列表 */
	miniCommodityInfoAsync(data) {
		return request({
			url: MINICOMMODITINFO,
			data
		})
	},
	/* 非会员购买 */
	activityRegister(data) {
		return request({
			url: REGISTERPLAY,
			method: 'POST',
			data
		})
	},
	/* H获取优秀会员||助梦人 */
	getShowData(data){
		return request({
			url: SHOWER,
			data
		})
	}
};
export default vip;