package com.stixu

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import javax.inject.Inject

@RestController
class LoginController {
    
    val loginService: LoginService;
    
    @Inject
    constructor(loginService: LoginService) {
        this.loginService = loginService
    }
    
    //  kotlin没有静态方法，建议添加在包上
    @RequestMapping("/login")
    fun login(): Map<String, String> {
        val username = loginService.create()
        return mapOf("username" to username)
    }
    
}