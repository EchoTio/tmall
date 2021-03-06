package com.how2java.tmall.pojo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
//表示这是一个实体类
@Table(name = "category")
//表示对应的表名是category
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
//本项目用的是Jpa来做实体类持久化，在 jpa 工作过程中，就会创造代理类来继承 Category ，
// 并添加 handler 和 hibernateLazyInitializer 这两个无须 json 化的属性，所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。

public class Category {
    @Id
    //表示该属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //采用数据库ID自增长的方式来自增主键字段
    @Column(name = "id")
    //标识实体类属性与数据库表字段对应关系
    int id;

    String name;
    @Transient
    List<Product> products;
    @Transient
    List<List<Product>> productsByRow;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    @Override
    public String toString(){
        return "Category[id="+id+",name="+name+"]";
    }
}