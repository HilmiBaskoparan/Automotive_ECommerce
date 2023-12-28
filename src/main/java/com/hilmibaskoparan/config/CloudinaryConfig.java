package com.hilmibaskoparan.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CloudinaryConfig {

    //@Value(staticConstructor = "cloudinary.cloud_name")
    private String cloudName;

    //@Value(staticConstructor = "cloudinary.api_key")
    private String apiKey;

    //@Value(staticConstructor = "cloudinary.api_secret")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));
    }

}
