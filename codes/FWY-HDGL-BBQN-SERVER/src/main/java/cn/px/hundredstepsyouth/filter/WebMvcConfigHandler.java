package cn.px.hundredstepsyouth.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 品讯科技
 * @date 2024-08
 */
@Configuration
public class WebMvcConfigHandler implements WebMvcConfigurer {

    @Resource
    private GateWayFilter gateWayFileter;

    @Value("#{'${notify.url}'.split(',')}")
    private List<String> notifyUrls;

    /**
     * 配置跨域
     *
     * @return CorsWebFilter
     **/
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("/**");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3000L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }

    /**
     * 拦截器
     * addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
     * addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
     * excludePathPatterns：用于设置不需要拦截的过滤规则
     * @param registry  registry
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        ArrayList<String> excludePathList = new ArrayList<>();
        notifyUrls.forEach(e -> {
            excludePathList.add(e);
        });
        registry.addInterceptor(gateWayFileter)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathList);

//		super.addInterceptors(registry);
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * 需要重新指定静态资源
     *
     * @param registry addResourceHandlers：静态资源
     *                 比如，我们想自定义静态资源映射目录的话，只需重写addResourceHandlers方法即可
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/**")
                //下面的是指可以对应resources文件下那些内容
                .addResourceLocations("classpath:/")
                .addResourceLocations("classpath:/templates/")
                .addResourceLocations("classpath:/static");

    }


}
