package com.danztee.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

//    removing this cuz we have lombork -- so the error goes away after adding the annotation
//    public FraudController(FraudCheckService fraudCheckService) {
//        this.fraudCheckService = fraudCheckService;
//    }

    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {

        boolean isFraudulentCustomer = fraudCheckService
                .isFraudulentCustomer(customerId);

        return new FraudCheckResponse(isFraudulentCustomer);

    }
}
