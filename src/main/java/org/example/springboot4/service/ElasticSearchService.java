package org.example.springboot4.service;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.EmployeeSearchRequest;
import org.example.springboot4.model.entity.Employee;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElasticSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public List<Employee> searchPersons(
            EmployeeSearchRequest request
    ) {

        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        if (request.name() != null) {
            Query nameQuery = Query.of(q -> q.multiMatch(m -> m
                    .query(request.name())
                    .fields("firstName", "lastName")
                    .type(TextQueryType.BestFields)
            ));

            boolQuery.must(nameQuery);
        }

        if (request.surnameRegex() != null) {
            Query nameWildcardQuery = Query.of(q -> q.wildcard(
                    w -> w.field("lastName")
                            .caseInsensitive(true)
                            .value(request.surnameRegex())
            ));
            boolQuery.should(nameWildcardQuery);

            Query namePrefixQuery = Query.of(q -> q.prefix(
                    p -> p.field("lastName")
                            .value(request.surnameRegex())
                            .caseInsensitive(true)
            ));
            boolQuery.should(namePrefixQuery);

            Query namePhrasePrefixQuery = Query.of(q -> q.matchPhrasePrefix(
                    m -> m.field("lastName")
                            .query(request.surnameRegex())
            ));
            boolQuery.should(namePhrasePrefixQuery);

            Query nameRegexQuery = Query.of(q -> q.regexp(
                    r -> r.field("lastName")
                            .value(request.surnameRegex())
                            .caseInsensitive(true)
            ));
            boolQuery.should(nameRegexQuery);
        }

        if (request.country() != null) {
            Query countryQuery = new Query.Builder()
                    .term(t -> t.field("country").value(request.country()))
                    .build();

            boolQuery.must(countryQuery);
        }

        Query dobRangeQuery = Query.of(q -> q.range(r -> r
                .date(d -> {
                    d.field("dob");
                    d.gte(request.dobFrom() != null ? request.dobFrom().toString() : null);
                    d.lte(request.dobTo() != null ? request.dobTo().toString() : null);
                    return d;
                })
        ));
        boolQuery.must(dobRangeQuery);

        Query salaryRangeQuery = Query.of(q -> q.range(r -> r
                .number(n -> {
                    n.field("salary");
                    n.gte(request.minSalary() != null ? request.minSalary() : null);
                    n.lte(request.maxSalary() != null ? request.maxSalary() : null);
                    return n;
                })
        ));
        boolQuery.must(salaryRangeQuery);

        if (request.occupation() != null) {
            Query occupationQuery = new Query.Builder()
                    .term(t -> t.field("occupation").value(request.occupation()))
                    .build();

            boolQuery.filter(occupationQuery);
        }

        if (request.bioText() != null) {
            Query bioQuery = new Query.Builder()
                    .match(m -> m.field("bio").query(request.bioText()))
                    .build();

            boolQuery.must(bioQuery);
        }

        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.bool(boolQuery.build()))
                .build();

        SearchHits<Employee> hits = elasticsearchOperations.search(query, Employee.class);

        return hits.stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
