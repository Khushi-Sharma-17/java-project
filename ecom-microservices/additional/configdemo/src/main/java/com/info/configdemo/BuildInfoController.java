package com.info.configdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildInfoController {

    @Value("${OS:default}") //using env variable    //used to inject value into the variable from the external sources such as yml files     "${build.id:default}"  using application .yml
    private String buildId;
    @Value("${USERPROFILE:default}")    //"${build.version:default}"
    private String buildVersion;
    @Value("${JAVA_HOME:default}")    //"${build.name:default}"
    private String buildName;
 @GetMapping("/build-info")
    public String getBuildInfo(){
        return "Build ID: " + buildId + " , Version: " + buildVersion +" , Name: " + buildName;

    }
}
