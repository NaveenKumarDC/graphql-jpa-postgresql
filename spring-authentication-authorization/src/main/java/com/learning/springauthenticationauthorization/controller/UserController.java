package com.learning.springauthenticationauthorization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Naveen Kumar D C
 * Date: 01/07/24
 */
@RestController
@RequestMapping("/api/v1.0")
public class UserController {

 @GetMapping("/welcome")
 public String welcome(){
  return "Welcome This end point is not secure";
 }

 //Only user with the role ROLE_USER can access this api.
 @GetMapping("/user/profile")
 @PreAuthorize("hasAuthority('ROLE_USER')")
 public String userProfile() {
  return "Welcome to User Profile";
 }


 //Only user with the role ROLE_ADMIN can access this api.
 //RFC 3986 defines URLs as case-sensitive for different parts of the URL. Since URLs are case sensitive, keeping it low-key (lower cased) is always safe and considered a good standard. Now that takes a camel case out of the window.
 //@GetMapping("/admin/adminProfile") --CamelCase URIs not recommended.
 @GetMapping("/admin/profile")
 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
 public String adminProfile() {
  return "Welcome to Admin Profile";
 }
}
