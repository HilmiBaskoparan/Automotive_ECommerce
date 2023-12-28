package com.hilmibaskoparan.business.services;

import com.cloudinary.utils.ObjectUtils;
import com.hilmibaskoparan.business.abstracts.ImageService;
import com.hilmibaskoparan.model.entity.Image;
import com.hilmibaskoparan.model.entity.Product;
import com.hilmibaskoparan.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final Cloudinary cloudinary;

    public ImageServiceImpl(ImageRepository imageRepository, Cloudinary cloudinary) {
        this.imageRepository = imageRepository;
        this.cloudinary = cloudinary;
    }

    @Transactional
    @Override
    public Image uploadImage(MultipartFile file, Product product) {

        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String url =  uploadResult.get("secure_url").toString();
        String name = file.getOriginalFilename();
        long size = file.getSize();

        Image image = new Image();
        image.setProduct(product);
        image.setName(name);
        image.setSize(size);
        image.setUrl(url);

        return imageRepository.save(image);

    }
}
