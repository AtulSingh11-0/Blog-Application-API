import com.blog.blogapplication.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Implementation of the {@link FileService} interface providing methods to handle file-related operations.
 */
@Service
public class FileImpl implements FileService {

  /**
   * Uploads an image file to the specified path.
   *
   * @param path The path where the image will be uploaded.
   * @param file The image file to upload.
   * @return String The filename of the uploaded image.
   * @throws IOException If an I/O error occurs during the upload process.
   */
  @Override
  public String uploadImage(String path, MultipartFile file) throws IOException {

    /* get file name */
    String name = file.getOriginalFilename();

    /* create a unique file name for each file */
    String UID = UUID.randomUUID().toString();
    String fileName = UID.concat(name.substring(name.lastIndexOf('.')));

    /* create a file path */
    String filePath = path + File.separator + fileName;

    /* create a folder if it doesn't exist */
    File f = new File(path);
    if (!f.exists()) f.mkdir();

    /* copy file */
    Files.copy(file.getInputStream(), Paths.get(filePath));
    return fileName;
  }

  /**
   * Retrieves an input stream for accessing the specified resource.
   *
   * @param path     The path where the resource is located.
   * @param filename The name of the resource file.
   * @return InputStream An input stream for accessing the resource.
   * @throws FileNotFoundException If the specified resource file is not found.
   */
  @Override
  public InputStream getResource(String path, String filename) throws FileNotFoundException {
    String filePath = path + File.separator + filename;
    return new FileInputStream(filePath);
  }
}
