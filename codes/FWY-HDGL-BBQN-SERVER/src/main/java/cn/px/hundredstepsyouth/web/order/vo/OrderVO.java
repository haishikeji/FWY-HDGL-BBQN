package cn.px.hundredstepsyouth.web.order.vo;

import cn.px.hundredstepsyouth.common.entity.BaseVoEntity;
import cn.px.hundredstepsyouth.web.user.vo.UserAppVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * OrderVO
 *
 * @author 品讯科技
 * @date 2024-08
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderVO", description="订单表")
public class OrderVO extends BaseVoEntity {

    @ApiModelProperty(value = "订单编号")
    private String code;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "订单类型：1-会员入驻；2-活动订单；3-会员学费")
    private Integer type;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "支付金额")
    private Long paymentAmount;

    @ApiModelProperty(value = "订单金额")
    private Long orderAmount;

    @ApiModelProperty(value = "支付状态：1-未支付；2-已支付；3-支付失败")
    private Integer state;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "下单人姓名")
    private String name;

    @ApiModelProperty(value = "商品名称")
    private String orderName;

    @ApiModelProperty(value = "用户信息")
    private UserAppVO memberInfo;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "产品id")
    private String productId;

    @Override
    public String toString() {
        return super.toString();
    }
}
