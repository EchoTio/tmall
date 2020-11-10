package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    //查询所有分类
    @GetMapping("/forehome")
    public Object home() {
        //为分类填充产品集合
        List<Category> cs= categoryService.list();
        productService.fill(cs);
        //为分类填充填充推荐产品集合
        productService.fillByRow(cs);
        //移除产品的分类信息，以免出现重复递归
        categoryService.removeCategoryFromProduct(cs);
        return cs;
    }

}

