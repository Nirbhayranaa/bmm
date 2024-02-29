package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Entity
@Table(name = "offer")
public class Offer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long offerId;

    @Column(name = "offer_name", nullable = false)
    private String offerName;

    @Column(name = "offer_description")
    private String offerDescription;

    @Column(name = "offer_type", nullable = false)
    private String offerType;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "conditions", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> conditions;

    @Column(name = "is_active")
    boolean isActive;
}
