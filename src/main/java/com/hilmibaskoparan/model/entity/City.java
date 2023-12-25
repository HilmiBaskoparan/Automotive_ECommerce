package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cities")
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class City extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "postal_code")
    private String postalCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(fetch =  FetchType.LAZY,mappedBy = "city")
    private List<District> districts;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "city")
    private List<Address> addresses;
}
