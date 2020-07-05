package com.borysov.agileengine.repositories;

import com.borysov.agileengine.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>, JpaSpecificationExecutor {

    Optional<Item> findById(String id);

    List<Item> findAllByPage(Integer page);

}
