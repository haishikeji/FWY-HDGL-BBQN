package cn.px.hundredstepsyouth.web.user.mapping;

import cn.px.hundredstepsyouth.common.entity.PageResult;
import cn.px.hundredstepsyouth.core.user.entity.dto.UserMemberAuthDTO;
import cn.px.hundredstepsyouth.web.user.request.UserMemberAuthRequest;
import cn.px.hundredstepsyouth.web.user.vo.UserMemberAuthVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈MsVoMapping〉
 *
 * @author 品讯科技
 * @date 2024-08
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMemberAuthBizMapping {

    /**
     * toDto
     *
     * @param request request
     * @return dto
     */
    UserMemberAuthDTO toDto(UserMemberAuthRequest request);

    /**
     * toVo
     *
     * @param dto dto
     * @return model
     */
    UserMemberAuthVO toVo(UserMemberAuthDTO dto);

    /**
     * toDtos
     *
     * @param dtos dtos
     * @return list
     */
    List<UserMemberAuthVO> toVos(List<UserMemberAuthDTO> dtos);

    /**
     * toPage
     *
     * @param page page
     * @return page
     */
    PageResult<UserMemberAuthVO> toPage(PageResult<UserMemberAuthDTO> page);

}

