package com.ecommerce.project.service.File;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${project.image}")
    private String path;
    private Path rootImagesDirectory;

    @PostConstruct
    public void init() {
        rootImagesDirectory = Paths.get(path);
    }

    @Override
    public String uploadImage(MultipartFile file) {

        if (file.isEmpty()) {
            throw new APIException("Error while reading the image");
        }

        String originalFileName = Optional.ofNullable(file.getOriginalFilename())
                .orElseThrow(() -> new APIException("Cannot resolve image name"));

        String extension = getFileNameExtension(originalFileName)
                .orElseThrow(() -> new APIException("Cannot resolve image extension"));

        String uniqueName = UUID.randomUUID() + "." + extension;

        try {
            Files.createDirectories(rootImagesDirectory);
        } catch (IOException e) {
            throw new APIException("Unknown error happened");
        }
        Path destinationPath = rootImagesDirectory.resolve(uniqueName);

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new APIException("Unknown error happened");
        }
        return uniqueName;
    }

    private Optional<String> getFileNameExtension(String filename) {

        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return Optional.of(filename.substring(dotIndex + 1));
        }
        return Optional.empty();
    }
}
