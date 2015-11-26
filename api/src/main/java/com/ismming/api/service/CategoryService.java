package com.ismming.api.service;

import com.ismming.api.domain.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    public List<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("women's store"));
        categories.add(new Category("men's store"));
        categories.add(new Category("collections"));
        categories.add(new Category("Marni's world"));
        categories.add(new Category("anticamera"));
        categories.add(new Category("store locator"));
        return categories;
    }

}
