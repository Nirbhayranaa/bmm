package com.nirbhay.bmm.model.usermanagement;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;


@Data
@Entity
public class AppUser extends BaseEntity {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 4244312494823824720L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private Long id;
    private String username;
    private String email;
    private String mobileNumber;
    private String password;
    private int enabled;
    private int accountExpired;
    private int accountLocked;



}
