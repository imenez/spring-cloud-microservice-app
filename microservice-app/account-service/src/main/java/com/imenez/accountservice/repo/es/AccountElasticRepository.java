package com.imenez.accountservice.repo.es;

import com.imenez.accountservice.entity.es.AccountModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountElasticRepository extends ElasticsearchRepository<AccountModel, String> {
}
