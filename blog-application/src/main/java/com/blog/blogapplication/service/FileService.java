package com.blog.blogapplication.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * The service interface for handling file-related operations.
 */
public interface FileService {

  /**
   * Uploads an image file to the specified path.
   *
   * @param path The path where the image will be uploaded.
   * @param file The image file to upload.
   * @return String The filename of the uploaded image.
   * @throws IOException If an I/O error occurs during the upload process.
   */
  String uploadImage(String path, MultipartFile file) throws IOException;

  /**
   * Retrieves an input stream for accessing the specified resource.
   *
   * @param path     The path where the resource is located.
   * @param filename The name of the resource file.
   * @return InputStream An input stream for accessing the resource.
   * @throws FileNotFoundException If the specified resource file is not found.
   */
  InputStream getResource(String path, String filename) throws FileNotFoundException;
}
