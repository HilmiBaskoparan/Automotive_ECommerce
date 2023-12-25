package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "countries")
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Country  extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "iso_code")
    private String isoCode;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "country")
    private List<City> cities;

}
