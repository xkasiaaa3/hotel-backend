package com.tijo.kw.hotel;

import com.tijo.kw.hotel.reservation.entity.Reservation;
import com.tijo.kw.hotel.reservation.repository.ReservationRepository;
import com.tijo.kw.hotel.room.entity.Room;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryReservationRepository implements ReservationRepository {

    List<Reservation> table = new ArrayList<>();

    @Override
    public void flush() {

    }

    @Override
    public <S extends Reservation> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Reservation> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Reservation> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Reservation getOne(UUID uuid) {
        return null;
    }

    @Override
    public Reservation getById(UUID uuid) {
        return null;
    }

    @Override
    public Reservation getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Reservation> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Reservation> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Reservation> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Reservation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Reservation> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Reservation> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Reservation, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Reservation> S save(S entity) {
        table.add(entity);
        return entity;
    }

    @Override
    public <S extends Reservation> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public List<Reservation> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Reservation entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Reservation> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Reservation> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Reservation> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UUID> getRoomIdsNotAvailable(LocalDate startDate, LocalDate endDate) {

        return table.stream()
                .filter(r -> (startDate.isAfter(r.getStartDate()) || r.getStartDate().isEqual(startDate))
                        && (endDate.isBefore(r.getEndDate()) || r.getEndDate().isEqual(endDate))
                        || (startDate.isBefore(r.getStartDate()) && endDate.isAfter(r.getEndDate()))
                        || (startDate.isAfter(r.getStartDate()) && startDate.isBefore(r.getEndDate()))
                        || (endDate.isAfter(r.getStartDate()) && endDate.isBefore(r.getEndDate())))
                .map(Reservation::getRoomId)
                .collect(Collectors.toList());
    }
//    @Query("SELECT r.roomId FROM Reservation r WHERE r.startDate <= :startDate AND r.endDate >= :endDate")
}
