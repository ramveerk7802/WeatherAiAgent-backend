package com.example.WeatherAiAgent.controllers

import com.example.WeatherAiAgent.services.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ChatController(private val chatService: ChatService) {



    @GetMapping("/chat")
    fun chat(@RequestParam("q") query: String): ResponseEntity<Any>{
        return ResponseEntity.ok(chatService.chatTemplate(query))
    }
}