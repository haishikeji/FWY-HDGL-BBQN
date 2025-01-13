package cn.px.hundredstepsyouth.adapter.oss.aliyun.service;


import cn.px.hundredstepsyouth.adapter.oss.aliyun.entity.dto.UploadVideoResponseDTO;
import cn.px.hundredstepsyouth.adapter.oss.aliyun.entity.dto.VideoPlayInfoDTO;

/**
 * 〈一句话功能简述〉<br>
 * 〈阿里云视频上传〉
 *
 * @author 品讯科技
 * @create 2021/9/10 19:53
 * @since 1.0.0
 */
public interface AliyunUploadService {

    /**
     * 获取视频上传凭证
     * @param title 标题
     * @param fileName 必须带扩展名，且扩展名不区分大小写
     * @return cn.px.ppk.module.adapter.oss.aliyun.entity.dto.UploadVideoDTO
    **/
    UploadVideoResponseDTO createUploadVideo(String title, String fileName);

    /**
     * 刷新视频上传凭证
     * @param vedioId vedioId
     * @return cn.px.ppk.module.adapter.oss.aliyun.entity.dto.UploadVideoDTO
     **/
    UploadVideoResponseDTO refreshUploadVideo(String vedioId);


    /**
     * 刷新视频上传凭证
     * @param videoId videoId
     * @param authInfoTimeout authInfoTimeout
     * @return cn.px.ppk.module.adapter.oss.aliyun.entity.dto.UploadVideoDTO
     **/
    UploadVideoResponseDTO getVideoPlayAuth(String videoId, String authInfoTimeout);

    /**
     * 获取视频播放id
     * @param vedioId vedioId
     * @return cn.px.ppk.module.adapter.oss.aliyun.entity.dto.UploadVideoDTO
     **/
    VideoPlayInfoDTO getPlayInfo(String vedioId);
}
