package com.hubject.iso.brocker.controler;

import com.hubject.iso.brocker.dao.SignedContractDataResponse;
import com.hubject.iso.brocker.dao.SignedContractDataRquest;
import com.hubject.iso.brocker.model.SignedContractDataReferendeFactory;
import com.hubject.iso.brocker.model.SignedContratDataReference;
import com.hubject.iso.brocker.service.RequestBrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController("RequestBrokerV1")
@RequestMapping(path = "/broker")
public class RequestBrokerController {

    Logger log = LoggerFactory.getLogger(RequestBrokerController.class);

    private final RequestBrokerService requestBrokerService;

    public RequestBrokerController(RequestBrokerService requestBrokerService) {
        this.requestBrokerService = requestBrokerService;
    }

    @PutMapping("/{alias}/{emaid}/{pcid}/{exiVersion}")
    public SignedContratDataReference postSignContractData(@PathVariable String alias, @PathVariable String emaid,
                                                           @PathVariable String pcid, @PathVariable String exiVersion,
                                                           @RequestBody SignedContractDataRquest location) {
        log.info("PUT alias: {} emaid: {} pcid: {} exiVersion: {}.", alias, emaid, pcid, exiVersion);
        return requestBrokerService.pushContractLocation(
                SignedContractDataReferendeFactory.create(alias, emaid, pcid, exiVersion, location.getValidUntil(),
                        location.getUrl()));
    }

    @GetMapping("/{alias}/{emaid}/{pcid}/{exiVersion}")
    public SignedContratDataReference getSignContractData(@PathVariable String alias, @PathVariable String emaid,
                                                          @PathVariable String pcid, @PathVariable String exiVersion) {
        log.info("GET alias: {} emaid: {} pcid: {} exiVersion: {}.", alias, emaid, pcid, exiVersion);
        return requestBrokerService
                .getContractLocation(alias, emaid, pcid, exiVersion)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "contract location not found"
                ));
    }

    @GetMapping("/{alias}/{emaid}")
    public List<SignedContratDataReference> getAllSignContractDataForEmais(@PathVariable String alias, @PathVariable String emaid) {
        log.info("GET alias: {} emaid: {}.", alias, emaid);
        return requestBrokerService
                .getContractLocations(alias, emaid);
    }

    @DeleteMapping("/{alias}/{emaid}/{pcid}/{exiVersion}")
    public SignedContratDataReference deleteSignContractData(@PathVariable String alias, @PathVariable String emaid,
                                                          @PathVariable String pcid, @PathVariable String exiVersion) {
        log.info("GET alias: {} emaid: {} pcid: {} exiVersion: {}.", alias, emaid, pcid, exiVersion);
        return requestBrokerService
                .getContractLocation(alias, emaid, pcid, exiVersion)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "contract location not found"
                ));
    }

}
