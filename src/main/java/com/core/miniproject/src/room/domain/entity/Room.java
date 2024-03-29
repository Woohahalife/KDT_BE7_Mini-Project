package com.core.miniproject.src.room.domain.entity;

import com.core.miniproject.src.accommodation.domain.entity.Accommodation;
import com.core.miniproject.src.reservation.model.entity.Reservation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="accommodation_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Accommodation accommodationId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name="room_info")
    private String roomInfo;

    @Column(name = "room_count")
    private int roomCount;

    @Column(name="fixed_member")
    private int fixedMember;

    @Column(name="maxed_member")
    private int maxedMember;

    //이미지 링크 추가
    @Column(name="room_images")
    private String roomImage;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    @Column(name = "reservation_id")
    private List<Reservation> reservations;


}
