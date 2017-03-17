package com.stixu

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
class LoginController {
    
    //  kotlin没有静态方法，建议添加在包上
    @RequestMapping("/login")
    fun login(): Map<String, String> {
        return mapOf("username" to "yiifaa")
    }
    
}