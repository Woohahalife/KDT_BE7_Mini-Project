package com.core.miniproject.src.accommodation.repository;

import com.core.miniproject.src.accommodation.domain.entity.Accommodation;
import com.core.miniproject.src.accommodation.domain.entity.AccommodationType;
import com.core.miniproject.src.location.domain.entity.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{

    @Override
    Accommodation save(Accommodation accommodation);

    @Override
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    List<Accommodation> findByAccommodationType(AccommodationType accommodationType);

    @Query("select a from Accommodation a where a.location.locationName = ?1")
    List<Accommodation> findByLocationType(LocationType locationType);

    @Query("select a from Accommodation a where a.accommodationType = ?1 and a.location.locationName = ?2")
    List<Accommodation> findByAccommodationTypeAndLocationType(AccommodationType aType, LocationType lType);

    @Query(""" 
       select a 
       from Accommodation a join Room r on a.id=r.accommodationId.id
       where a.location.locationName=?1 
       and r.fixedMember=?2 
       """)
    List<Accommodation> findByLocationTypeAndFixedNumber(LocationType type, int fixedMember);


    //save 후 숙소 평점의 평균 값을 가져와서 기존 rate를 update 해줌.
    @Modifying
    @Query("""
        update Accommodation a
        set a.rate = (select avg(r.rate) from Rate r where r.accommodation.id=?1)
        where a.id=?1
    """)
    void updateRate(Long id);

    @Modifying
    @Query("""
        update Accommodation a
        set a.price=(select min(r.price) from Room r where r.accommodationId.id=?1)
        where a.id=?1   
            """)
    void updatePrice(Long id);


}
