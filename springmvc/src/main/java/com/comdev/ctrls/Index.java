package com.comdev.ctrls;

import com.comdev.vos.VOPet;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: gnoloahs
 * Date: 2013-04-09
 * Time: 上午9:06
 */

@Controller
@RequestMapping("/")
public class Index
{

    @Autowired
    private NutDao dao;

    @RequestMapping("/")
    public ModelAndView showIndex()
    {
        ModelAndView out = new ModelAndView();

        out.addObject("hello", "你好！java！");

        out.addObject("db",   dao.meta().getProductName() + "--" + dao.meta().getVersion());

        out.addObject("pet",   dao.fetch(VOPet.class,"xiaomao"));

        out.setViewName("index");

        return out;
    }


}
