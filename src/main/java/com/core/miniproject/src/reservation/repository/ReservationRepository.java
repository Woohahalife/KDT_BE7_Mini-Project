package com.core.miniproject.src.reservation.repository;

import com.core.miniproject.src.common.constant.IsVisited;
import com.core.miniproject.src.reservation.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
            select r
            from Reservation r
            left join r.member m
            where m.id = :memberId
            """)
    List<Reservation> findAllReservation(@Param("memberId") Long id);

    @Modifying(clearAutomatically = true)
    @Query("""
            update Reservation r
            set r.isVisited = :isVisited
            where r.checkIn = LOCAL_DATE
            """)
    int updateOnReservationDay(@Param("isVisited") IsVisited isVisited);

    @Modifying(clearAutomatically = true)
    @Query("""
            update Reservation r
            set r.isVisited = :isVisited
            where r.checkOut <= LOCAL_DATE
            """)
    int updateReservationOverDue(@Param("isVisited") IsVisited isVisited);
}
