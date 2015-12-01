package com.ismming.api.service;

import com.ismming.api.domain.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LogManager.getLogger(CategoryService.class.getName());

    public List<Category> getCategories() {
        LOG.info("Test LOG");
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("COLLECTION ONE", "collection one produced by ismming.com"));
        categories.add(new Category("COLLECTION TWO", "collection two produced by ismming.com"));
        categories.add(new Category("COLLECTION THREE", "collection three produced by ismming.com"));
        return categories;
    }

}
