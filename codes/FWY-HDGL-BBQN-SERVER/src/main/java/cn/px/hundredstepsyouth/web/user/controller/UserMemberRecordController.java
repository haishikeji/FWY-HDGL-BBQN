package cn.px.hundredstepsyouth.web.user.controller;

import cn.px.hundredstepsyouth.core.user.service.UserMemberRecordService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 会员充值记录 前端控制器
 *
 * @author : yc
 * @date : 2022-03-04
 **/
@RestController
@RequestMapping(value = "/userMemberRecord",name = "会员充值记录")
@Api(value = "/userMemberRecord", tags = "会员充值记录")
public class UserMemberRecordController{
    @Resource
    private UserMemberRecordService userMemberRecordService;

}
