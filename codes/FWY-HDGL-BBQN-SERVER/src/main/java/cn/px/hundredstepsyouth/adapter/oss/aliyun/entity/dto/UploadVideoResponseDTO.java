package cn.px.hundredstepsyouth.adapter.oss.aliyun.entity.dto;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈阿里云视屏上传对象〉
 *
 * @author 品讯科技
 * @create 2021/9/10 19:45
 * @since 1.0.0
 */
@Getter
@Setter
public class UploadVideoResponseDTO implements Serializable {


    private static final long serialVersionUID = 8207678296298450044L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 视频ID。
     */
    private String videoId;

    /**
     * 上传地址。
     */
    private VideoAddressDTO uploadAddress;

    /**
     * 上传凭证。
     */
    private String uploadAuth;

    /**
     * 请求ID。
     */
    private String requestId;

    /**
     * message 异常消息
     */
    private String message;

    /**
     * playAuth
     */
    private String playAuth;

    /**
     * coverURL
     */
    private String coverUrl;
    private Float duration;
    private String status;
    private String title;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

