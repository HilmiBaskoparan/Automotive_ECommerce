package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.model.entity.Image;
import com.hilmibaskoparan.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public Image uploadImage(MultipartFile file, Product product);
}
