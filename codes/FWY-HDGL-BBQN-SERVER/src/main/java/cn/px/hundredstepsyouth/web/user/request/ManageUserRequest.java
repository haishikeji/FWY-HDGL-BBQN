package cn.px.hundredstepsyouth.web.user.request;


import cn.px.hundredstepsyouth.common.entity.BaseRequestEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @@author 品讯科技
 * @Description: ManageUserDTO
 * @Date: 2021-04-28
 **/
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@ApiModel(value="ManageUserRequest数据传输对象", description="管理端用户")
public class ManageUserRequest extends BaseRequestEntity {

    private static final long serialVersionUID = 2084777761002370543L;
    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String account;

    @ApiModelProperty(value = "密码")
//    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    @ApiModelProperty(value = "名称")
    private String name;

    public ManageUserRequest() {
    }

    public ManageUserRequest(String account, String name) {
        this.account = account;
        this.name = name;
    }
}
