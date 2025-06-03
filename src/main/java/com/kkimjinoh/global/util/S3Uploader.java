package com.kkimjinoh.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * MultipartFile을 S3에 업로드하고 해당 파일의 접근 가능한 URL을 반환합니다.
     *
     * @param file    업로드할 파일
     * @param dirName 버킷 내 디렉토리 이름 (예: "lost-items")
     * @return 업로드된 파일의 S3 URL
     */
    public String uploadFile(MultipartFile file, String dirName) throws IOException {
        String fileName = dirName + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    /**
     * S3에 업로드된 파일을 삭제한다.
     *
     * @param imageUrl 삭제할 S3 객체의 전체 URL
     */
    public void deleteFile(String imageUrl) {
        // URL에서 key 부분 추출 (버킷 이름 이후 경로)
        String key = extractKeyFromUrl(imageUrl);
        amazonS3.deleteObject(bucket, key);
    }

    /**
     * S3 URL에서 key 경로를 추출한다.
     */
    private String extractKeyFromUrl(String url) {
        try {
            URL s3Url = new URL(url);
            String path = s3Url.getPath();
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            return URLDecoder.decode(path, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("S3 URL에서 Key를 추출하는 중 오류 발생", e);
        }
    }
}