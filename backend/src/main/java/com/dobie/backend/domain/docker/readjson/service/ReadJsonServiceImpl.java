package com.dobie.backend.domain.docker.readjson.service;

import com.dobie.backend.domain.docker.dockerfile.service.DockerfileService;
import com.dobie.backend.exception.exception.Environment.*;
import com.dobie.backend.exception.exception.build.BackendBuildFailedException;
import com.dobie.backend.exception.exception.build.FrontendBuildFailedException;
import com.dobie.backend.exception.exception.docker.DockerPsErrorException;
import com.dobie.backend.exception.exception.docker.DockerPsLinePartsErrorException;
import com.dobie.backend.exception.exception.file.SaveFileFailedException;
import com.dobie.backend.util.file.FileManager;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReadJsonServiceImpl implements ReadJsonService {

    @Override
    public  Map<String, Object> JsonToMap() {
        try {
            // ObjectMapper 인스턴스 생성
            ObjectMapper mapper = new ObjectMapper();

            // 클래스 로더를 사용하여 InputStream 얻기
            InputStream inputStream = ReadJsonServiceImpl.class.getClassLoader().getResourceAsStream("data/project.json");

            // InputStream을 사용하여 JSON 내용을 Map으로 변환
            Map<String, Object> map = mapper.readValue(inputStream, Map.class);

            // 맵 출력
            System.out.println(map);

            // 맵 반환
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonToMapErrorException();
        }
    }

    @Override
    public Object JsonGetOne(Map<String, Object> projectJsonMap, String a) {
        return projectJsonMap.get(a);
    }

    @Override
    public Object JsonGetTwo(Map<String, Object> projectJsonMap, String a, String b) {
        Map<String, Object> firstMap = (Map<String, Object>) projectJsonMap.get(a);
        return firstMap.get(b);
    }

    @Override
    public Object JsonGetThree(Map<String, Object> projectJsonMap, String a, String b, String c) {
        Map<String, Object> firstMap = (Map<String, Object>) projectJsonMap.get(a);
        Map<String, Object> secondMap = (Map<String, Object>) firstMap.get(b);
        return secondMap.get(c);
    }

    @Override
    public Object JsonGetFour(Map<String, Object> projectJsonMap, String a, String b, String c, String d) {
        Map<String, Object> firstMap = (Map<String, Object>) projectJsonMap.get(a);
        Map<String, Object> secondMap = (Map<String, Object>) firstMap.get(b);
        Map<String, Object> thirdMap = (Map<String, Object>) secondMap.get(c);
        return thirdMap.get(d);
    }
}
