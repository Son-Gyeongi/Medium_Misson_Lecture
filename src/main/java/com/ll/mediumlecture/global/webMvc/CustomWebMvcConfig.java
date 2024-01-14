package com.ll.mediumlecture.global.webMvc;

import com.ll.mediumlecture.global.app.AppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
Spring MVC에서 사용되는 WebMvcConfigurer를 구현한 클래스
CORS(Cross-Origin Resource Sharing)와 정적 리소스 핸들링에 관련된 구성을 담당
 */
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    // 스벨트킷 사용할 때 필요
    // CORS 설정을 추가하는 메서드
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // "/api/**" 경로에 대한 CORS 설정을 추가
                .allowedOrigins("https://cdpn.io", "http://localhost:5173") // 허용할 출처를 지정, 다음 출처에서 요청 허용
                .allowedMethods("*") // 모든 HTTP 메서드 허용
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 자격 증명(쿠키, HTTP 인증과 같은)을 허용
    }

    // 파일 업로드 시 필요
    // 정적 리소스 핸들링을 추가하는 메서드
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/gen/**") // "/gen/**" 경로에 대해서
                .addResourceLocations("file:///" + AppConfig.getGenFileDirPath() + "/");
        // 지정된 디렉토리에서 정적 리소스를 찾도록 설정
        // 지정된 디렉토리에서 정적 리소스 제공
    }
}
