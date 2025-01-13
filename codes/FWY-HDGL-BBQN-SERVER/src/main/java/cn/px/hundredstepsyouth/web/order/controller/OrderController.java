package cn.px.hundredstepsyouth.web.order.controller;

import cn.px.hundredstepsyouth.common.entity.JwtUserInfo;
import cn.px.hundredstepsyouth.common.entity.PageResult;
import cn.px.hundredstepsyouth.common.enumeration.OrderConstant;
import cn.px.hundredstepsyouth.common.result.Result;
import cn.px.hundredstepsyouth.common.utils.JwtUtil;
import cn.px.hundredstepsyouth.core.activity.entity.dto.ActivityDTO;
import cn.px.hundredstepsyouth.core.adapter.wx.api.dto.WxPayMpOrderResultDTO;
import cn.px.hundredstepsyouth.core.order.entity.dto.OrderDTO;
import cn.px.hundredstepsyouth.core.order.service.OrderService;
import cn.px.hundredstepsyouth.core.user.entity.dto.UserAppDTO;
import cn.px.hundredstepsyouth.core.user.service.UserAppService;
import cn.px.hundredstepsyouth.web.order.mapping.OrderBizMapping;
import cn.px.hundredstepsyouth.web.order.mapping.WxPayOrderBizMapping;
import cn.px.hundredstepsyouth.web.order.request.ActivityOrderRequest;
import cn.px.hundredstepsyouth.web.order.vo.MiniOrderVO;
import cn.px.hundredstepsyouth.web.order.vo.OrderVO;
import cn.px.hundredstepsyouth.web.order.vo.WxPayMpOrderResultVO;
import cn.px.hundredstepsyouth.web.user.mapping.UserAppBizMapping;
import cn.px.hundredstepsyouth.web.user.request.UserAppMiniRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 订单表 前端控制器
 *
 * @author : yc
 * @date : 2022-03-05
 **/
@RestController
@RequestMapping(value = "/order", name = "订单表")
@Api(value = "/order", tags = "订单表")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private OrderBizMapping bizMapping;

    @Resource
    private HttpServletRequest servletRequest;

    @Resource
    private UserAppBizMapping userAppBizMapping;

    @Resource
    private WxPayOrderBizMapping payOrderBizMapping;

    @Resource
    private UserAppService appUserService;

    @GetMapping("/manager/v1/getOrderInfo")
    @ApiOperation("订单详情")
    public Result<OrderVO> getOrderInfo(@RequestParam String id) {
        OrderDTO orderInfo = orderService.getOrderInfo(id, null);
        return Result.SUCCESS(bizMapping.toVo(orderInfo));
    }

    @GetMapping("/manager/v1/getOrderPage")
    @ApiOperation("获取订单分页")
    public Result<PageResult<OrderVO>> getOrderPage(@RequestParam(defaultValue = "1") Long curernt,
                                                    @RequestParam(defaultValue = "10") Long size,
                                                    @RequestParam(required = false) String nameOrPhone,
                                                    @RequestParam(required = false) Integer state,
                                                    @RequestParam(required = false) Integer type,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime beginTime,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {

        PageResult<OrderDTO> page = orderService.getOrderPage(curernt, size, nameOrPhone, state, type, beginTime, endTime, null);
        return Result.SUCCESS(bizMapping.toPage(page));
    }

    @DeleteMapping("/manager/v1/deleteOrderf")
    @ApiOperation("删除订单")
    public Result<Boolean> deleteOrderf(@RequestParam String id) {
        JwtUserInfo jwtUser = JwtUtil.getJwtUser(servletRequest);
        return Result.SUCCESS(orderService.deleteOrderf(id, jwtUser.getUserId()));
    }

    @RequestMapping(value = "/app/v1/memberPay", method = RequestMethod.POST)
    @ApiOperation("加入会员")
    public Result<WxPayMpOrderResultVO> addMiniMember(@RequestBody @Valid UserAppMiniRequest request) {
        JwtUserInfo jwtUser =JwtUtil.getJwtUser(servletRequest);
        UserAppDTO dto = userAppBizMapping.toDto(request);
        dto.setUpdateUser(jwtUser.getUserId());
        dto.setCreateUser(jwtUser.getUserId());
        dto.setId(jwtUser.getUserId());
        WxPayMpOrderResultDTO orderResult = orderService.memberPay(dto, true);
        return Result.SUCCESS(payOrderBizMapping.toVo(orderResult));
    }

    @PostMapping(value = "/open/payNotify/{type}")
    @ApiOperation("购买会员回调")
    public String payNotify(@PathVariable(required = false) String type) {
        return orderService.payNotify(type);
    }

    @PostMapping("/app/v1/activityRegister")
    @ApiOperation("活动报名")
    public Result<WxPayMpOrderResultVO> activityRegister(@RequestBody @Validated ActivityOrderRequest request) {
        JwtUserInfo jwtUser = JwtUtil.getJwtUser(servletRequest);
        OrderDTO order = bizMapping.toDto(request);
        order.setCreateUser(jwtUser.getUserId());
        order.setUpdateUser(jwtUser.getUserId());
        WxPayMpOrderResultDTO info = orderService.activityRegister(order, jwtUser.getUserId());
        return Result.SUCCESS(payOrderBizMapping.toVo(info));
    }

    @GetMapping("/app/v1/miniOrderPage")
    @ApiOperation("获取订单分页  status-活动状态：1-待发布；2-即将开始||报名中；3-已开始；4-已结束")
    public Result<PageResult<MiniOrderVO>> miniOrderPage(@RequestParam(defaultValue = "1") Long curernt,
                                                         @RequestParam(defaultValue = "10") Long size,
                                                         @RequestParam(required = false) Integer status) {
        JwtUserInfo jwtUser = JwtUtil.getJwtUser(servletRequest);
        PageResult<ActivityDTO> page = orderService.getOrderActivityPage(curernt, size, null, null, OrderConstant.OrderType.ACTIVITY, null, null, jwtUser.getUserId(), status);

        return Result.SUCCESS(bizMapping.toMiniPage(page));
    }

}
