package com.hilmibaskoparan.bootstrap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilmibaskoparan.model.entity.*;
import com.hilmibaskoparan.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CountryRepository countryRepository;
    private final RoleRepository roleRepository;
    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;
    private final CustomerRepository customerRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final PasswordEncoder passwordEncoder;

    public BootstrapData(CategoryRepository categoryRepository, ProductRepository productRepository,
                         CountryRepository countryRepository, RoleRepository roleRepository,
                         BrandRepository brandRepository, ImageRepository imageRepository,
                         CustomerRepository customerRepository, CityRepository cityRepository,
                         DistrictRepository districtRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.countryRepository = countryRepository;
        this.roleRepository = roleRepository;
        this.brandRepository = brandRepository;
        this.imageRepository = imageRepository;
        this.customerRepository = customerRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        roleRepository.save(new Role(0, ERole.ROLE_USER));
        roleRepository.save(new Role(0, ERole.ROLE_ADMIN));

        ObjectMapper objectMapper = new ObjectMapper();

        List<Category> categories = objectMapper.readValue(getClass().getResourceAsStream("/data/categories.json"),
                new TypeReference<List<Category>>() {
                });

        List<Product> products = objectMapper.readValue(getClass().getResourceAsStream("/data/products.json"),
                new TypeReference<List<Product>>() {
                });

        List<Brand> brands = objectMapper.readValue(getClass().getResourceAsStream("/data/brands.json"),
                new TypeReference<List<Brand>>() {
                });

        List<Image> images = objectMapper.readValue(getClass().getResourceAsStream("/data/images.json"),
                new TypeReference<List<Image>>() {
                });

        List<Country> countries = objectMapper.readValue(getClass().getResourceAsStream("/data/countries.json"),
                new TypeReference<List<Country>>() {
                });

        List<City> cities = objectMapper.readValue(getClass().getResourceAsStream("/data/cities.json"),
                new TypeReference<List<City>>() {
                });

        List<District> districts = objectMapper.readValue(getClass().getResourceAsStream("/data/districts.json"),
                new TypeReference<List<District>>() {
                });

        categoryRepository.saveAll(categories);
        brandRepository.saveAll(brands);
        productRepository.saveAll(products);
        imageRepository.saveAll(images);
        countryRepository.saveAll(countries);
        cityRepository.saveAll(cities);
        districtRepository.saveAll(districts);

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
        Customer customer = new Customer();
        customer.setFirstName("Hilmi");
        customer.setLastName("Başkoparan");
        customer.setEmail("h.baskoparan@gmail.com");
        customer.setUsername("hbasko");
        customer.setPassword(passwordEncoder.encode("123456"));
        customer.setEmailVerified(true);
        customer.setRoles(roles);
        customerRepository.save(customer);

        List<Role> roles1 = new ArrayList<>();
        roles1.add(roleRepository.findByName(ERole.ROLE_USER).get());
        Customer customer1 = new Customer();
        customer1.setFirstName("Ahmet");
        customer1.setLastName("Türk");
        customer1.setEmail("ahmettürk@gmail.com");
        customer1.setUsername("aTurk");
        customer1.setPassword(passwordEncoder.encode("123456"));
        customer1.setEmailVerified(true);
        customer1.setRoles(roles1);
        customerRepository.save(customer1);

		/*Address address = new Address();
		address.setProvince("Ankara");
		address.setDistrict("Çankaya");
		address.setDoorNumber("12");
		address.setStreet("Anıttepe Mahallesi");
		address.setFullAddress("Anıttepe Mahallesi No:12 Çankaya/Ankara");
		address.setPostCode("06570");
		address.setUser(customer);
		addressRepository.save(address);*/
    }
}
