package com.example.observationdemo.controllers

import com.example.observationdemo.MyUserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
internal class MyController(myUserService: MyUserService) {
    private val myUserService: MyUserService

    init {
        this.myUserService = myUserService
    }

    @GetMapping("/user/{userId}")
    fun userName(@PathVariable("userId") userId: String?): String {
        log.info("Got a request")
        return myUserService.userName(userId)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(MyController::class.java)
    }
}