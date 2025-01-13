package cn.px.hundredstepsyouth.adapter.goose.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈注册小鹅通传输对象〉
 *
 * @author 品讯科技
 * @create 2021/9/14 15:56
 * @since 1.0.0
 */
@Setter
@Getter
public class AccessTokenResponse implements Serializable {

    private static final long serialVersionUID = 4212402754590184207L;

    /**
     * 凭证
     */
    private String accessToken;

    /**
     * 过期时间，单位秒
     */
    private Integer expiresIn;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;
}

