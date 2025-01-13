package cn.px.hundredstepsyouth.web.activity.request;

import cn.px.hundredstepsyouth.common.entity.BaseRequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  ActivityParticipantsDTO
 *
 * @author 品讯科技
 * @date 2024-08
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ActivityParticipantsRequest", description="活动参与记录")
public class ActivityParticipantsRequest extends BaseRequestEntity {

    @ApiModelProperty(value = "参与人员id",required = true)
    private String userId;

    @ApiModelProperty(value = "活动id",required = true)
    private String activityId;

    @ApiModelProperty(value = "报名时间",required = true)
    private LocalDateTime applyTime;

    @ApiModelProperty(value = "活动评价",required = true)
    private String evaluation;

    @ApiModelProperty(value = "评论时间",required = true)
    private LocalDateTime evaluationTime;

    @Override
    public String toString() {
        return super.toString();
    }
}
