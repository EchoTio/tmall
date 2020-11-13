package com.how2java.tmall.comparator;

import com.how2java.tmall.pojo.Product;

import java.util.Comparator;
//综合比较器 销量*评价的放前面
public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1,Product p2){
        return p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount();
    }
}
