package cn.px.hundredstepsyouth.web.user.request;


import cn.px.hundredstepsyouth.common.entity.BaseRequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @@author 品讯科技
 * @Description: AppUserDTO
 * @Date: 2021-04-28
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AppUserUpdateRequest数据传输对象", description="小程序用户")
public class AppUserUpdateRequest extends BaseRequestEntity {

    private static final long serialVersionUID = -7994587830393015261L;
    @ApiModelProperty(value = "用户名称",required = true)
    private String nickname;

    @ApiModelProperty(value = "用户手机号",required = true)
    private String userPhone;

    @ApiModelProperty(value = "出生年月",required = true)
    private String birth;

    @ApiModelProperty(value = "性别 0：未知  1：男  2 ：女",required = true)
    private Integer gender;

    @ApiModelProperty(value = "小程序唯一标识",required = true)
    private String openId;

    @ApiModelProperty(value = "从事的行业id 字典表获取",required = true)
    private List<String> professionIds;

    @ApiModelProperty(value = "学历id",required = true)
    private String educationId;

    @ApiModelProperty(value = "身份id",required = true)
    private List<String> identityIds;

    @ApiModelProperty(value = "兴趣爱好id",required = true)
    private List<String> hobbyId;

    @ApiModelProperty(value = "头像",required = true)
    private String avatar;

    @ApiModelProperty(value = "注册时间",required = true)
    private LocalDateTime registerTime;

    @ApiModelProperty(value = "会员期数id",required = true)
    private String memberCycleId;

    @ApiModelProperty(value = "毕业院校（可多个，用，隔开）",required = true)
    private String school;

    @ApiModelProperty(value = "企业名称",required = true)
    private String enterprise;

    @ApiModelProperty(value = "您希望在百步青年获得什么",required = true)
    private String reward;

    @ApiModelProperty(value = "企业描述",required = true)
    private String enterpriseDesc;

    @ApiModelProperty(value = "推荐人id",required = true)
    private String recommender;

    @ApiModelProperty(value = "营业执照")
    private String license;

    @ApiModelProperty(value = "职务")
    private String profession;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否优秀会员 0-否；1-是")
    private Boolean excellentMember;

    @ApiModelProperty(value = "是否助梦人 0-否；1-是")
    private Boolean dreamer;

    @ApiModelProperty(value = "会员过期时间")
    private String deadline;

    @ApiModelProperty(value = "毕业院校")
    private String education;
}
