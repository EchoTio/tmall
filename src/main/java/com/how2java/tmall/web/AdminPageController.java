package com.how2java.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//表示这是一个控制器
public class AdminPageController {

    @GetMapping(value="/admin")
    //访问地址admin会跳转到admin_category_list去
    public String admin(){
        return "redirect:admin_category_list";
    }
    @GetMapping(value="/admin_category_list")
    public String admin_category_list(){
        return "redirect:admin/listCategory";
    }

}
