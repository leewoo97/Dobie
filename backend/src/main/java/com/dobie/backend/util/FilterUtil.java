package com.dobie.backend.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

//어떤 URL 경로들이 토큰 검사로부터 제외될지를 관리하는 util
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "filter-path")//'filter-path' 프리픽스를 가진 설정 값을 자동으로 바인딩
public class FilterUtil {

    private Map<String, String> paths; // 필터링할 URL 패턴을 저장할 맵
//    //getter
//    public Map<String, String> getPaths() {
//        return paths;
//    }//
//    //setter
//    public void setPaths(Map<String, String> paths) {
//        this.paths = paths;
//    }
}
