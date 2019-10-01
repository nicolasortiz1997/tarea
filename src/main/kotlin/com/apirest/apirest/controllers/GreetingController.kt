package com.apirest.apirest.controllers


import com.apirest.apirest.models.Greeting
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/greeting")
class GreetingController {
    private val counter = AtomicLong()

    @ApiOperation(value = "Returns Hello World")
    @ApiResponses(value = [ApiResponse(code = 100, message = "100 is the message"), ApiResponse(code = 200, message = "Successful Hello World")])
    @GetMapping
    fun greeting(@RequestParam(value = "name", defaultValue = "World UNIAJC") name: String): Greeting {
        return Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name))
    }

    companion object {
        private val TEMPLATE = "Hi, %s!"
    }
}