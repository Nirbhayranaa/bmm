/**
 *
 */
package com.nirbhay.bmm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Nirbhay Rana
 *
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -2538728790069222345L;


    @Column(name = "create_time", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    LocalDateTime createTime;

    @JsonIgnore
    @Column(name = "update_time")
    @UpdateTimestamp
    LocalDateTime updateTime;

    @PrePersist
    void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updateTime = LocalDateTime.now();
    }

}
