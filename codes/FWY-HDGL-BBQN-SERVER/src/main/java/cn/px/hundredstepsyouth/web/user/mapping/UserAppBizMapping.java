package cn.px.hundredstepsyouth.web.user.mapping;

import cn.px.hundredstepsyouth.common.entity.PageResult;
import cn.px.hundredstepsyouth.core.user.entity.dto.UserAppDTO;
import cn.px.hundredstepsyouth.core.user.entity.dto.UserAppRecommenderDTO;
import cn.px.hundredstepsyouth.web.user.request.AppUserUpdateRequest;
import cn.px.hundredstepsyouth.web.user.request.UserAppMiniRequest;
import cn.px.hundredstepsyouth.web.user.request.UserAppRequest;
import cn.px.hundredstepsyouth.web.user.vo.UserAppRecommenderVO;
import cn.px.hundredstepsyouth.web.user.vo.UserAppVO;
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
public interface UserAppBizMapping {

    /**
     * toDto
     *
     * @param request request
     * @return dto
     */
    UserAppDTO toDto(UserAppRequest request);

    /**
     * toDto
     *
     * @param request request
     * @return dto
     */
    UserAppDTO toDto(UserAppMiniRequest request);

    /**
     * toDto
     *
     * @param request request
     * @return dto
     */
    UserAppDTO toDto(AppUserUpdateRequest request);

    /**
     * toVo
     *
     * @param dto dto
     * @return model
     */
    UserAppVO toVo(UserAppDTO dto);

    /**
     * toVos
     *
     * @param dto dto
     * @return model
     */
    List<UserAppVO> toVos(List<UserAppDTO> dtos);

    /**
     * toPage
     *
     * @param page page
     * @return page
     */
    PageResult<UserAppVO> toPage(PageResult<UserAppDTO> page);

    /**
     * toVo
     *
     * @param dto dto
     * @return vo
     */
    UserAppRecommenderVO toVo(UserAppRecommenderDTO dto);
}

