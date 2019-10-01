package com.apirest.apirest.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/hello")
class HelloController {

    @ApiOperation(value = "Returns Hello World")
    @ApiResponses(value = [ApiResponse(code = 100, message = "100 is the message"), ApiResponse(code = 200, message = "Successful Hello World")])
    @GetMapping
    fun hello(): String {
        return "Hello World uniajc"
    }

    @ApiOperation(value = "Returns Hello World")
    @PostMapping("/post")
    fun helloPost(@RequestBody hello: String): String {
        return hello
    }

    @ApiOperation(value = "Returns Hello World")
    @PutMapping("/put")
    fun helloPut(@RequestBody hello: String): String {
        return hello
    }
}
