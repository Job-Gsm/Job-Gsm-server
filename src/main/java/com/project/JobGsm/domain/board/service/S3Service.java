package com.project.JobGsm.domain.board.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String upload(MultipartFile file, String dirName);

    void deleteFile(String fileName);

    String createFileName(String fileName) throws IllegalAccessException;

    String getFileExtension(String fileName) throws IllegalAccessException;

}
