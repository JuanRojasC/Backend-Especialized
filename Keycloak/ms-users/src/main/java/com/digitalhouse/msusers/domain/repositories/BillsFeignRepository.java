package com.digitalhouse.msusers.domain.repositories;

import com.digitalhouse.msusers.config.feign.OAuthFeignConfig;
import com.digitalhouse.msusers.domain.models.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
@FeignClient(name = "ms-bill", configuration = OAuthFeignConfig.class)
public interface BillsFeignRepository {

    @GetMapping("/bills/{id}")
    List<Bill> getBillsByCustomerBill(@PathVariable String id);

}
