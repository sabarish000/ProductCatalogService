package com.example.product.catalog.clients;

import com.example.product.catalog.dtos.FakeStoreProductDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class FakeResponseApiClient {
    private final String PRODUCTS_URL = "http://fakestoreapi.com/products/";
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDTO getProductById(Long id) {
        return requestForEntity(PRODUCTS_URL + "{id}", HttpMethod.GET, null,
                FakeStoreProductDTO.class, id).getBody();
    }

    public List<FakeStoreProductDTO> getProducts() {
        FakeStoreProductDTO[] fakeStoreProductDTOS =
                requestForEntity(PRODUCTS_URL, HttpMethod.GET, null, FakeStoreProductDTO[].class).getBody();
        if(fakeStoreProductDTOS == null) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.asList(fakeStoreProductDTOS);
    }

    public FakeStoreProductDTO updateProduct(Long id, @NotNull FakeStoreProductDTO fakeStoreProductDTO) {
        return requestForEntity(PRODUCTS_URL + "{id}", HttpMethod.PATCH, fakeStoreProductDTO, FakeStoreProductDTO.class, id).getBody();
    }

    public FakeStoreProductDTO replaceProduct(Long id, @NotNull FakeStoreProductDTO fakeStoreProductDTO) {
        return requestForEntity(PRODUCTS_URL + "{id}", HttpMethod.PUT, fakeStoreProductDTO, FakeStoreProductDTO.class, id).getBody();
    }
    public FakeStoreProductDTO deleteProduct(Long id) {
        return requestForEntity(PRODUCTS_URL + "{id}", HttpMethod.PUT, null, FakeStoreProductDTO.class, id).getBody();
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request,
                                                   Class<T> responseType,
                                                   Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
