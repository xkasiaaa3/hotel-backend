package com.tijo.kw.hotel;

import com.tijo.kw.hotel.room.entity.Room;
import com.tijo.kw.hotel.room.repository.RoomRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryRoomRepository implements RoomRepository {

    List<Room> table = new ArrayList<>();

    @Override
    public void flush() {

    }

    @Override
    public <S extends Room> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Room> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Room> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Room getOne(UUID uuid) {
        return null;
    }

    @Override
    public Room getById(UUID uuid) {
        return null;
    }

    @Override
    public Room getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Room> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Room> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Room> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Room> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Room> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Room> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Room, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Room> S save(S entity) {
        table.add(entity);
        return entity;
    }

    @Override
    public <S extends Room> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Room> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return table.stream().anyMatch(r -> r.getId() == uuid);
    }

    @Override
    public List<Room> findAll() {
        return table;
    }

    @Override
    public List<Room> findAllById(Iterable<UUID> uuids) {
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
    public void delete(Room entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Room> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Room> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean existsByNumber(int number) {
        return !table.stream().filter(room -> room.getNumber() == number).collect(Collectors.toList()).isEmpty();
    }
}
