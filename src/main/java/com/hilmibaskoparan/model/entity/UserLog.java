package com.hilmibaskoparan.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_logs")
public class UserLog extends BaseEntity {

    private String logDescription;
    private Date loginDate;
    private Date logoutDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
