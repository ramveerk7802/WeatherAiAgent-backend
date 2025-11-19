package com.example.WeatherAiAgent.services

interface ChatService {

    fun chatTemplate(query: String): String?
}