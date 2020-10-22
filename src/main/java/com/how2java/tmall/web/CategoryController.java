package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//表示Restful服务器控制器，每个方法的返回值都会返回Json格式
public class CategoryController {
    @Autowired CategoryService categoryService;
    //自动装配服务层
    @GetMapping("/categories")
    public List<Category> list() throws Exception{
        return categoryService.list();
    }
    //对categories的访问都会返回一个Category的集合，又因为该类声明是RestController形式，因此会返回为Json格式到浏览器
}
