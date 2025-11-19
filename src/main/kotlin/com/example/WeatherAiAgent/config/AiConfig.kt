package com.example.WeatherAiAgent.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository
import org.springframework.ai.ollama.api.OllamaChatOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class AiConfig {

    private val ollamaUrl = "http://localhost:11434"
    private val apiUrl = "https://api.weatherapi.com/v1"

    @Bean
    fun inMemoryChatRepository(): InMemoryChatMemoryRepository = InMemoryChatMemoryRepository()


    @Bean
    fun chatClient(builder: ChatClient.Builder,chatMemory: ChatMemory): ChatClient{
        val messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build()
        return builder
            .defaultAdvisors(messageChatMemoryAdvisor)
            .defaultOptions(
                OllamaChatOptions.builder()
                    .model("llama3.2:latest")
                    .temperature(0.6)
                    .build()
            )
            .build()
    }


    @Bean
    fun restClient(): RestClient{
        return RestClient.builder()
            .baseUrl(apiUrl)
            .build()
    }
}