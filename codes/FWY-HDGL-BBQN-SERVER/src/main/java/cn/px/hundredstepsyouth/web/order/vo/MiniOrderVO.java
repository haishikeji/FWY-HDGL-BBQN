package cn.px.hundredstepsyouth.web.order.vo;

import cn.px.hundredstepsyouth.common.entity.BaseVoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * OrderVO
 *
 * @author 品讯科技
 * @date 2024-08
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MiniOrderVO", description="用户订单表")
public class MiniOrderVO extends BaseVoEntity {

    @ApiModelProperty(value = "订单信息")
    private OrderVO order;

    @ApiModelProperty(value = "活动编号")
    private String code;

    @ApiModelProperty(value = "活动日期")
    private String activityDate;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动类型id")
    private String typeId;

    @ApiModelProperty(value = "活动主题")
    private String topic;

    @ApiModelProperty(value = "是否限制会员参与：0-否；1-是")
    private Boolean limitMember;

    @ApiModelProperty(value = "会员期数id")
    private String memberCycleId;

    @ApiModelProperty(value = "普通会员价格，仅在不限制会员参与时存在")
    private Long userPrice;

    @ApiModelProperty(value = "活动地址")
    private String activityAddress;

    @ApiModelProperty(value = "活动详情")
    private String activityDetail;

    @ApiModelProperty(value = "活动状态：1-待发布；2-即将开始||报名中；3-已开始；4-已结束")
    private Integer status;

    @ApiModelProperty(value = "是否上架：0-否；1-是")
    private Boolean released;

    @ApiModelProperty(value = "是否推荐：0-否；1-是")
    private Boolean recommend;

    @ApiModelProperty(value = "活动封面")
    private String cover;

    @ApiModelProperty(value = "参与人数")
    private Integer participate;

    @ApiModelProperty(value = "标签,隔开")
    private String label;

    @Override
    public String toString() {
        return super.toString();
    }
}
