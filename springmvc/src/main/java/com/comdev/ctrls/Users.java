package com.comdev.ctrls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: gnoloahs
 * Date: 2013-04-09
 * Time: 下午1:37
 */
@Controller
@RequestMapping("users")
public class Users
{

    @RequestMapping("")
    public String listUsers()
    {
        return "users/list";
    }


}
