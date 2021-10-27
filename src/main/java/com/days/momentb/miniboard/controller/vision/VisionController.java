package com.days.momentb.miniboard.controller.vision;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/vision")
public class VisionController {

    private final CloudVisionTemplate cloudVisionTemplate;

    private final ResourceLoader resourceLoader;


    @GetMapping("/extractLabels")
    public ModelAndView imageLabel(String imageUrl, ModelMap map){

        log.info("ImageURL: "+imageUrl);

        String testUrl ="https://www.google.com/logos/doodles/2018/childrens-day-2018-argentina-peru-5906663952351232-2x.png";

    AnnotateImageResponse response =
            this.cloudVisionTemplate.analyzeImage(
                    this.resourceLoader.getResource(testUrl), Feature.Type.LABEL_DETECTION);


    log.info("--------------------------------------");
    log.info("--------------------------------------");
    log.info("response : " + response);
    log.info("--------------------------------------");

    Map<String, Float> imageLabels =
            response.getLabelAnnotationsList().stream()
                    .collect(
                            Collectors.toMap(
                                    EntityAnnotation::getDescription,
                                    EntityAnnotation::getScore,
                                    (u, v) -> {
                                        throw new IllegalStateException(String.format("Duplicate key %s", u));
                                    },
                                    LinkedHashMap::new));

        log.info("--------------------------------------");
        log.info("--------------------------------------");
        log.info("imageLabels : " + imageLabels);
        log.info("--------------------------------------");

            map.addAttribute("annotations", imageLabels);
            map.addAttribute("imageUrl", imageUrl);

            return new ModelAndView("result", map);
    }
}
