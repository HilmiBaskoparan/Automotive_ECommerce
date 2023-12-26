package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private List<Product> products;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "categories")
    private List<Brand> brands = new ArrayList<>();

    public Category( String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        super(id);
        this.name = name;
    }
}
