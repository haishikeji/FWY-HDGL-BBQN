package cn.px.hundredstepsyouth.web.user.request;

import cn.px.hundredstepsyouth.common.entity.BaseRequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *  UserFeatureDTO
 *
 * @author 品讯科技
 * @date 2024-08
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserFeatureRequest", description="用户身份特征")
public class UserFeatureRequest extends BaseRequestEntity {

    @ApiModelProperty(value = "特征id字典表获取",required = true)
    private String featureId;

    @ApiModelProperty(value = "用户id",required = true)
    private String userId;

    @ApiModelProperty(value = "用户特征：1-职业；2-学历；3-当前身份；4-兴趣爱好",required = true)
    private Integer type;

    @Override
    public String toString() {
        return super.toString();
    }
}
