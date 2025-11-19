package com.example.WeatherAiAgent.services.impl

import com.example.WeatherAiAgent.services.ChatService
import com.example.WeatherAiAgent.tools.WeatherTool
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.document.Document
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class ChatServiceImpl(private val chatClient: ChatClient,private val weatherTool: WeatherTool) : ChatService {




    override fun chatTemplate(query: String): String? {
        return chatClient.prompt()
            .tools(weatherTool)
            .user { u->u.text(query) }
            .call()
            .content()
    }
}