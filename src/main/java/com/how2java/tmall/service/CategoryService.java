package com.how2java.tmall.service;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//表示这是一个服务类
public class CategoryService {
    @Autowired CategoryDAO categoryDAO;
    //自动装配DAO层对象

    public List<Category> list(){
        Sort sort =new Sort(Sort.Direction.DESC,"id");
        return categoryDAO.findAll(sort);
    }//创建一个通过id倒排序，用CategoryDAO进行查询

}
