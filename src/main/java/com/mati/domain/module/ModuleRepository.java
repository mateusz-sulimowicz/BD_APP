package com.mati.domain.module;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;


public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query(
            value = "SELECT m.* " +
                    "FROM module m " +
                    "WHERE m.id = :module_id",
            nativeQuery = true
    )
    @Override
    Optional<Module> findById(@Param("module_id") Long id);

    @Query(
            value = "SELECT * FROM module m",
            nativeQuery = true
    )
    @Override
    List<Module> findAll();

}
