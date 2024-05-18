package com.dobie.backend.domain.docker.readjson.service;

import java.util.Map;

public interface ReadJsonService {
    Map<String, Object> JsonToMap();

    Object JsonGetOne(Map<String, Object> projectJsonMap, String a);
    Object JsonGetTwo(Map<String, Object> projectJsonMap, String a, String b);
    Object JsonGetThree(Map<String, Object> projectJsonMap, String a, String b, String c);
    Object JsonGetFour(Map<String, Object> projectJsonMap, String a, String b, String c, String d);
}
