package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page4Navigator;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
//表示Restful服务器控制器，每个方法的返回值都会返回Json格式
//对categories的访问都会返回一个Category的集合，又因为该类声明是RestController形式，因此会返回为Json格式到浏览器
public class CategoryController {

    @Autowired CategoryService categoryService;
    //自动装配服务层
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start",defaultValue = "0")int start,
                                         @RequestParam(value = "size",defaultValue = "5")int size)throws Exception{
        start = start<0?0:start;
        Page4Navigator<Category> page = categoryService.list(start,size,5);//5表示导航页最多有五个
        return page;
    }
    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception{
        categoryService.add(bean);
        saveOrUpdateImageFile(bean,image,request);
        return bean;
    }
    public void saveOrUpdateImageFile(Category bean,MultipartFile image,HttpServletRequest request)
        throws IOException{
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdir();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img,"jpg",file);
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id")int id,HttpServletRequest request)throws Exception{
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    //提供Get方法，把id对应的Category取出来，转换为Json对象发给浏览器
    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id)throws Exception{
        Category bean=categoryService.get(id);
        return bean;
    }

    @PutMapping("/categories/{id}")
    public Object update(Category bean,MultipartFile image,HttpServletRequest request)throws Exception{
        //获取参数名,Put无法像Get一样注入Category对象
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.update(bean);
        //如果上传图片，调用该方法
        if(image!=null){
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }
}
