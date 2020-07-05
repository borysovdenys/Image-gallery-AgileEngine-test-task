package com.borysov.agileengine.services.impl;

import com.borysov.agileengine.dtos.ItemDto;
import com.borysov.agileengine.helpers.RequestHelper;
import com.borysov.agileengine.models.Item;
import com.borysov.agileengine.repositories.ItemRepository;
import com.borysov.agileengine.services.ItemService;
import com.borysov.agileengine.specifications.CustomSpecs;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Data
@Log
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void updateData(String token) {
        try {
            Map<Integer, List> pageImages = RequestHelper.getImages(token);

            for (Map.Entry<Integer, List> elem : pageImages.entrySet()) {
                for (Object o : elem.getValue()) {

                    Map image = (Map) o;

                    Item imageInfo = RequestHelper.getImageInfo((String) image.get("id"), token);

                    Item item = itemRepository
                            .findById((String) image.get("id"))
                            .orElse(new Item((String) image.get("id")));

                    item.setAuthor(imageInfo.getAuthor());
                    item.setCamera(imageInfo.getCamera());
                    item.setCropped_picture(imageInfo.getCropped_picture());
                    item.setFull_picture(imageInfo.getFull_picture());
                    item.setTags(imageInfo.getTags());
                    item.setPage(elem.getKey());

                    itemRepository.save(item);
                }
            }
        } catch (URISyntaxException e) {
            log.severe(e.getMessage());
        }
    }

    @Override
    public List<ItemDto> getAll() {
        return itemRepository.findAll().stream()
                .map(item -> new ItemDto(item.getId(), item.getCropped_picture()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> getItemById(String id) {
        return itemRepository.findById(id);
    }

    @Override
    public List getPage(Integer page) {
        return itemRepository.findAllByPage(page);
    }

    @Override
    public List searchItems(String searchTerm) {
        return itemRepository.findAll(CustomSpecs.containsText(searchTerm));
    }
}
