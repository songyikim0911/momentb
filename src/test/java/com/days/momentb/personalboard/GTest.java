package com.days.momentb.personalboard;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;

@SpringBootTest
@Log4j2
public class GTest {


    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @Autowired
    ResourceLoader resourceLoader;


    @Test
    public void test1()throws Exception {

        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream("C:\\zzz\\cat.jpg"));

        AnnotateImageResponse response =
                this.cloudVisionTemplate.analyzeImage(inputStreamResource, Feature.Type.LABEL_DETECTION);

        log.info("---------------------------");
        log.info(response);
    }
}
