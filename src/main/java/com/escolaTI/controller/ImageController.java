package com.escolaTI.controller;

import com.escolaTI.model.ImageDocument;
import com.escolaTI.repository.ImageRepository;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

@RestController
public class ImageController {

    private final ImageRepository repository;

    public ImageController(ImageRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Arquivo vazio"));
        }

        ImageDocument image = new ImageDocument();
        image.setContentType(file.getContentType());
        image.setData(file.getBytes());

        ImageDocument saved = repository.save(image);

        String url = "/images/" + saved.getId();
        return ResponseEntity.ok(Map.of("url", url));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        return repository.findById(id)
                .map(image -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.parseMediaType(image.getContentType()));
                    headers.setCacheControl(CacheControl.maxAge(Duration.ofDays(30)).getHeaderValue());
                    return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
