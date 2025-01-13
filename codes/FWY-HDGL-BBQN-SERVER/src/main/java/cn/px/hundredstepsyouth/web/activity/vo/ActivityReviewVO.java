package cn.px.hundredstepsyouth.web.activity.vo;

import cn.px.hundredstepsyouth.common.entity.BaseVoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ActivityReviewVO
 *
 * @author 品讯科技
 * @date 2024-08
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ActivityReviewVO", description="活动回顾")
public class ActivityReviewVO extends BaseVoEntity {


    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "阅读量")
    private Integer readNum;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime publisTime;

    @ApiModelProperty(value = "发布人员id，管理端人员")
    private String publicUserId;

    @ApiModelProperty(value = "是否发布：0-否；1-是")
    private Boolean released;

    @ApiModelProperty(value = "是否置顶：0-否；1-是")
    private Boolean recommend;

    @ApiModelProperty(value = "活动回顾详情")
    private String detail;

    @ApiModelProperty(value = "活动报名人数")
    private Integer num;

    @ApiModelProperty(value = "活动详情")
    private ActivityVO activity;

    @ApiModelProperty(value = "发布人")
    private String publisher;

    @Override
    public String toString() {
        return super.toString();
    }
}
