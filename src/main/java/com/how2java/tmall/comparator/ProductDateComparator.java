package com.how2java.tmall.comparator;

import com.how2java.tmall.pojo.Product;

import java.util.Comparator;
//创建时间晚的放前面
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1,Product p2){
        return p1.getCreateDate().compareTo(p2.getCreateDate());
    }
}
