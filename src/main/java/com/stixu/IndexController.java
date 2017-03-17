package com.stixu;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ganhuan
 */
@RestController
public class IndexController {
	   
    @RequestMapping("/home")
    public Map<String, String> home() {
        Map<String, String> result = new HashMap<>();
        String message = FuncUtils.sayHello("甘焕");
        result.put("username", message);
        return result;
    }
    
}
