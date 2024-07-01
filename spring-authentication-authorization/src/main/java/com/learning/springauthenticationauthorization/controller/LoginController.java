package com.learning.springauthenticationauthorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: Naveen Kumar D C
 * Date: 01/07/24
 */
@Controller
public class LoginController {
  @GetMapping("/login")
  String login() {
    //Custom login page implementation
    return "login";
  }
}
