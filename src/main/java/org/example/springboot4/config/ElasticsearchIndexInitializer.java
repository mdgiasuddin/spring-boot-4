package org.example.springboot4.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot4.model.entity.Employee;
import org.example.springboot4.model.entity.Product;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ElasticsearchIndexInitializer {

    private final ElasticsearchOperations operations;

    @Bean
    public ApplicationRunner elasticsearchIndexSetupRunner() {
        return args -> {
            createIndexIfNeeded(Employee.class);
            createIndexIfNeeded(Product.class);
        };
    }

    private <T> void createIndexIfNeeded(Class<T> clazz) {
        IndexOperations indexOps = operations.indexOps(clazz);
        try {
            String indexName = indexOps.getIndexCoordinates().getIndexName();
            if (!indexOps.exists()) {
                log.info("[Elasticsearch] Creating index '{}' for {}", indexName, clazz.getSimpleName());
                indexOps.create();
                boolean mappingOk = indexOps.putMapping(indexOps.createMapping(clazz));
                log.info("[Elasticsearch] Put mapping for '{}' (ok={})", indexName, mappingOk);
            } else {
                // Ensure mapping is applied/updated on startup
                boolean mappingOk = indexOps.putMapping(indexOps.createMapping(clazz));
                log.info("[Elasticsearch] Index '{}' already exists. Mapping updated (ok={})", indexName, mappingOk);
            }
        } catch (Exception e) {
            log.error("[Elasticsearch] Failed to ensure index for {}: {}", clazz.getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }
}
