package com.example.WeatherAiAgent.tools

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.time.LocalDateTime
import java.time.ZoneId


@Service
class WeatherTool(private val restClient: RestClient) {

    private val logger: Logger = LoggerFactory.getLogger(WeatherTool::class.java)
    @Value("\${weather.api.key}")
    private lateinit var WEATHER_API_KEY: String

    @Tool(description = "Get the current date and time in the user's local time zone")
    fun currentDateAndTime(): String{
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toString()
    }



//    @Tool(description = "Get the current weather conditions for a specific city. Input should be the city name.")
    @Tool(description = "Get the current weather conditions for a specific location. Always try to provide the city and country.")
    fun currentWeather(@ToolParam(description = "The city and country name (e.g. 'London, UK', 'Paris, France', 'Delhi, India').") city: String): String{

    val body =  this.restClient.get()
            .uri { uriBuilder -> uriBuilder.path("/current.json")
                .queryParam("key",WEATHER_API_KEY)
                .queryParam("q",city)
                .build()
            }
            .retrieve()
            .body<Map<String, Any>>()
    return body.toString()
    }


}