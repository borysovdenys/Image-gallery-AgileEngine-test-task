package com.borysov.agileengine.controllers;

import com.borysov.agileengine.exceptions.EntityNotFoundException;
import com.borysov.agileengine.models.Item;
import com.borysov.agileengine.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/images")
    public List getAll(@RequestParam(required = false) Integer page) {
        return page != null ? itemService.getPage(page) : itemService.getAll();
    }

    @GetMapping("/images/{id}")
    public Item get(@PathVariable String id) {
        return itemService.getItemById(id)
                .orElseThrow(() -> new EntityNotFoundException(Item.class, id));
    }

    @GetMapping("/search/{searchTerm}")
    public List search(@PathVariable String searchTerm) {
        return itemService.searchItems(searchTerm);
    }
}
