package com.info.configdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildInfoController {

    @Value("${BUILD_ID:default}") //using env variable OS  USERPROFILE PROCESSOR_LEVEL   //used to inject value into the variable from the external sources such as yml files     "${build.id:default}"  using application .yml
    private String buildId;
    @Value("${BUILD_VERSION:default}")    //"${build.version:default}"
    private String buildVersion;
    @Value("${BUILD_NAME:default}")    //"${build.name:default}"
    private String buildName;
 @GetMapping("/build-info")
    public String getBuildInfo(){
        return "Build ID: " + buildId + " , Version: " + buildVersion +" , Name: " + buildName;

    }
}
