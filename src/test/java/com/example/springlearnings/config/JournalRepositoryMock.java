package com.example.springlearnings.config;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.persistence.IJournalRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class JournalRepositoryMock implements IJournalRepository {
    @Override
    public <S extends Journal> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Journal> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Journal> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Journal> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Journal> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Journal> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Journal> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Journal> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Journal, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Journal> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Journal> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Journal> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Journal> findAll() {
        return List.of();
    }

    @Override
    public List<Journal> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Journal entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Journal> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Journal> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Journal> findAll(Pageable pageable) {
        return null;
    }
}
