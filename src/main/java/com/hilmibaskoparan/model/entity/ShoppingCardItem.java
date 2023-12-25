package com.hilmibaskoparan.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "shopping_card_items")
@Entity
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShoppingCardItem extends BaseEntity {


}
