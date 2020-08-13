package medo.common.kafka.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import medo.common.kafka.common.KafkaConfigurationProperties;

@EnableConfigurationProperties
@Configuration
public class KafkaPropertiesConfiguration {

    @Bean
    public KafkaConfigurationProperties kafkaConfigurationProperties(
            @Value("${medo.kafka.bootstrap.servers:localhost:9092}") String bootstrapServers,
            @Value("${medo.kafka.connection.validation.timeout:#{1000}}") long connectionValidationTimeout) {
        return new KafkaConfigurationProperties(bootstrapServers, connectionValidationTimeout);
    }

}