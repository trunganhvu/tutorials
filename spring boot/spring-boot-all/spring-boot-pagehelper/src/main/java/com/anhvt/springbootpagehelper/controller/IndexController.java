/**
 * Copyright 2024
 * Name: IndexController
 */
package com.anhvt.springbootpagehelper.controller;

import com.anhvt.springbootpagehelper.model.Index;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
@RestController
public class IndexController {
    @GetMapping("/index1")
    public List<Index> index1() {
        PageHelper.startPage(1, 2);
        List<Index> list = indexService();
        PageInfo<Index> pageInfo = new PageInfo<>(list);
        List<Index> result = pageInfo.getList();
        for (Index u : result) {
            System.out.println(u.getId());
        }

        return result;
    }

    private List<Index> indexService() {
        return List.of(
                new Index(1, "title1"),
                new Index(2, "title2"),
                new Index(3, "title3"),
                new Index(4, "title4")
        );
    }
}
