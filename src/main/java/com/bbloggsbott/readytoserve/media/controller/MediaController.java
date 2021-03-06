package com.bbloggsbott.readytoserve.media.controller;

import com.bbloggsbott.readytoserve.media.service.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MediaController {

    @Autowired
    private MediaService mediaService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/files/{filename}")
    public ResponseEntity<byte[]> getResource(@PathVariable("filename") String filename) throws IOException {
        logger.info("Received request for Resource {}", filename);
        byte[] content = mediaService.getFileAsByteArray(filename);
        return new ResponseEntity<byte[]>(content, mediaService.getHeaders(filename), HttpStatus.OK);
    }
}
