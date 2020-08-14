package medo.framework.message.consumer.jdbc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import medo.common.spring.transactional.TransactionHelper;
import medo.common.spring.transactional.configuration.TransactionHelperConfiguration;
import medo.framework.message.consumer.common.handler.DuplicateMessageDetector;
import medo.framework.message.consumer.jdbc.MessageConsumerJdbcOptions;
import medo.framework.message.consumer.jdbc.SqlTableBasedDuplicateMessageDetector;

@Configuration
@Import({ TransactionHelperConfiguration.class })
@ConditionalOnMissingBean(DuplicateMessageDetector.class)
public class ConsumerJdbcConfiguration {

    @Bean
    public DuplicateMessageDetector duplicateMessageDetector(MessageConsumerJdbcOptions messageConsumerJdbcOptions,
            TransactionHelper<?, ?> transactionHelper, @Value("${medo.message.received-message-table:received_message}") String receivedMessageTable) {
        return new SqlTableBasedDuplicateMessageDetector(messageConsumerJdbcOptions, transactionHelper,
                receivedMessageTable);
    }

}
