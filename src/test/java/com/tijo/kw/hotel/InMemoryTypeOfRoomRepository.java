package com.tijo.kw.hotel;

import com.tijo.kw.hotel.room.entity.TypeOfRoom;
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryTypeOfRoomRepository implements TypeOfRoomRepository{

    List<TypeOfRoom> table = new ArrayList<>();

    @Override
    public void flush() {

    }

    @Override
    public <S extends TypeOfRoom> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends TypeOfRoom> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<TypeOfRoom> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TypeOfRoom getOne(UUID uuid) {
        return null;
    }

    @Override
    public TypeOfRoom getById(UUID uuid) {
        return null;
    }

    @Override
    public TypeOfRoom getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends TypeOfRoom> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TypeOfRoom> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TypeOfRoom> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TypeOfRoom> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TypeOfRoom> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TypeOfRoom> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TypeOfRoom, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends TypeOfRoom> S save(S entity) {
        table.add(entity);
        return entity;
    }

    @Override
    public <S extends TypeOfRoom> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TypeOfRoom> findById(UUID uuid) {
        return table.stream().filter(type -> type.getId() == uuid).findFirst();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return !table.stream().filter(type -> type.getId() == uuid).collect(Collectors.toList()).isEmpty();
    }

    @Override
    public List<TypeOfRoom> findAll() {
        return table;
    }

    @Override
    public List<TypeOfRoom> findAllById(Iterable<UUID> uuids) {
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
    public void delete(TypeOfRoom entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends TypeOfRoom> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TypeOfRoom> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TypeOfRoom> findAll(Pageable pageable) {
        return null;
    }
}
