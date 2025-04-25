package org.lukasz.dropboxclient;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class AppConfig {
     @Value("${spring.kafka.bootstrap-servers}")
     private String bootstrapServers;
     @Value("${spring.kafka.consumer.key-deserializer}")
     private String keydeserializer;
     @Value("${spring.kafka.consumer.value-deserializer}")
     private String valuedeserializer;


    @Value("${baseUrl}")
    private String baseUrl;








    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "your-consumer-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,keydeserializer);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valuedeserializer);
        return consumerProps;
    }
        @Bean
        public WebClient.Builder webClientBuilder() {
            return WebClient.builder().baseUrl(baseUrl);
        }

}
