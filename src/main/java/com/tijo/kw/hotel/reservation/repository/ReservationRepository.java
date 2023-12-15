package com.tijo.kw.hotel.reservation.repository;

import com.tijo.kw.hotel.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query("SELECT r.roomId FROM Reservation r " +
            "WHERE (:startDate >= r.startDate AND :startDate <= r.endDate) " +
            "OR (:endDate >= r.startDate AND :endDate <= r.endDate) " +
            "OR (:startDate <= r.startDate AND :endDate >= r.endDate)")
    List<UUID> getRoomIdsNotAvailable(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
