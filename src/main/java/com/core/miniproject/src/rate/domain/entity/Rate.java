package com.core.miniproject.src.rate.domain.entity;

import com.core.miniproject.src.accommodation.domain.entity.Accommodation;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Accommodation accommodation;

    @Column(name = "rate")
    private double rate;

}
