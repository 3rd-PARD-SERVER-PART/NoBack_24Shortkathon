package com.example.demo.Image.Service;

import com.example.demo.Image.Entity.Image;
import com.example.demo.Image.Repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

@Service
public class ImageService {


    @Autowired
    private ImageRepository imageRepository;

    public ResponseEntity<Long> uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            String tempFilePath = tempDir + image.getOriginalFilename();
            image.transferTo(new File(tempFilePath));

            BufferedImage bufferedImage = ImageIO.read(new File(tempFilePath));
            if (bufferedImage == null) {
                System.out.println("The provided file is not a valid image.");
                return ResponseEntity.badRequest().body(null);
            }

            String uploadDir = "/Users/ision/Desktop/Server-Sion/Shorkathon/Shorkathon/src/main/resources/static/images";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = new File(tempFilePath).getName();
            File outputFile = new File(uploadDir + fileName);

            ImageIO.write(bufferedImage, "jpg", outputFile);

            Image imageRecord = new Image();
            imageRecord.setFilePath(outputFile.getAbsolutePath());
            Image savedImage = imageRepository.save(imageRecord);

            new File(tempFilePath).delete();

            return ResponseEntity.ok(savedImage.getImageId());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    public Image findById(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow();
    }

}