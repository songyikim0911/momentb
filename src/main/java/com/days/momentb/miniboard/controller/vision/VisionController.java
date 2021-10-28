package com.days.momentb.miniboard.controller.vision;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

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

//        String testUrl ="https://ww.namu.la/s/6fb6b0f615f6891959241fde895d72fca81cc78aa75668ac2fc074397a3751591b434cc2498b328c82a03325bbeff5a7f45e88b9dc42396ae02f8419c5b398c87e31ef7099482b7bd738e9bedd041f2e80988befea2ef60fdbd68f2d985dee26dc1d5e395045d0b19490eff1d0220520";
        String testUrl ="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";
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



//    @PostMapping("/upload")
//    public Map<String, Object> addData(@RequestBody DataDTO dataDTO) {
//
//        Map<String, Object> result = new HashMap<>();
//
//        String fileBase64 = dataDTO.getFileBase64();
//
//        // 파일이 업로드되지 않았거나 사이즈가 큰 경우를 체크합니다.
//        // 사이즈는 일반 바이트에서 1.33을 곱하면 BASE64 사이즈가 대략 나옵니다.
//
//        if(fileBase64 == null || fileBase64.equals("")) {
//            result.put("isFileInserted", false);
//            result.put("uploadStatus", "FileIsNull");
//            return result;
//        } else if(fileBase64.length() > 400000) {
//            result.put("isFileInserted", false);
//            result.put("uploadStatus", "FileIsTooBig");
//            return result;
//        }
//
//        try {
//            String fileName = dataDTO.getFileName(); // 파일네임은 서버에서 결정하거나 JSON에서 받아옵니다.
//
//            // 저장할 파일 경로를 지정합니다.
//            File file = new File(FileSystemView.getFileSystemView().getHomeDirectory()
//                    + "/app/resources/" + fileName);
//
//            // BASE64를 일반 파일로 변환하고 저장합니다.
//            Base64.Decoder decoder = Base64.getDecoder();
//            byte[] decodedBytes = decoder.decode(fileBase64.getBytes());
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(decodedBytes);
//            fileOutputStream.close();
//
//            result.put("isFileInserted", true);
//            result.put("uploadStatus", "AllSuccess");
//
//        } catch(IOException e) {
//            System.err.println(e);
//            result.put("uploadStatus", "FileIsNotUploaded");
//            result.put("isTTSInserted", false);
//        }
//
//        return result;
//    }
}
