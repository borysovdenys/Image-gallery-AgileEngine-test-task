package com.borysov.agileengine.services;

import com.borysov.agileengine.dtos.ItemDto;
import com.borysov.agileengine.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    void updateData(String token);

    List<ItemDto> getAll();

    Optional<Item> getItemById(String id);

    List getPage(Integer page);

    List searchItems(String searchTerm);
}
