package com.sumup.os.apptemplate.controllers

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {
    @RequestMapping("/hello")
    fun helloWorld(): ResponseEntity<Any> =
        ok("Hello World")
}
