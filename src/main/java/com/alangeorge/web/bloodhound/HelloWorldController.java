package com.alangeorge.web.bloodhound;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
    private static final Logger log = Logger.getLogger(HelloWorldController.class);

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(ModelMap modelMap) {
        log.debug("in hello()");
        modelMap.addAttribute("message", "Hello World");
        return "hello";
    }

    @RequestMapping(value = "helloJson")
    public @ResponseBody Hello hello() {
        Hello hello = new Hello();

        hello.setMessage("HelloWorld");

        return hello;
    }

    public class Hello {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
