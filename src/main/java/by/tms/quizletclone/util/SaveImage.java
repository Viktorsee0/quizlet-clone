package by.tms.quizletclone.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SaveImage {

    @Value("${upload.path}")
    private static String webRootPath;


    public static void validateImage(MultipartFile image) {
        if (!Objects.equals(image.getContentType(), "image/jpeg")) {
            throw new ImageUploadException("Only JPG images accepted");
        }
    }

    public static void saveImage(String filename, MultipartFile image) {
        try {

//            File uploadDir = new File(webRootPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            image.transferTo(new File(webRootPath + "/" + filename));

            File file = new File(webRootPath + "/" + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageUploadException("Unable to save image", e);
        }
    }
}
