package com.hubject.iso.brocker.controler;

import com.hubject.iso.brocker.service.RequestBrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController("RequestBrokerV1")
@RequestMapping(path = "/broker")
public class RequestBrokerController {

    Logger log = LoggerFactory.getLogger(RequestBrokerController.class);

    private final RequestBrokerService requestBrokerService;

    public RequestBrokerController(RequestBrokerService requestBrokerService){
        this.requestBrokerService = requestBrokerService;
    }

    @PutMapping("/{alias}/{emaid}/{pcid}/{exiVersion}")
    public String postSignContractData(@RequestParam String alias, @RequestParam String emaid, @RequestParam String pcid, @RequestParam String exiVersion){
        log.info("PUT alias: {} emaid: {} pcid: {} exiVersion: {}.", alias, emaid, pcid, exiVersion);
        return "done";
    }

}
