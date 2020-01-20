package com.hubject.iso.brocker.service;

import com.hubject.iso.brocker.dao.SignedContractDataRquest;
import com.hubject.iso.brocker.model.SignedContractDataMapper;
import com.hubject.iso.brocker.model.SignedContratDataReference;
import com.hubject.iso.brocker.repositrory.SignedContractDataInMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestBrokerService {

    private final SignedContractDataInMemoryRepository signedContractDataInMemoryRepository;

    public RequestBrokerService(SignedContractDataInMemoryRepository signedContractDataInMemoryRepository) {
        this.signedContractDataInMemoryRepository = signedContractDataInMemoryRepository;
    }

    public SignedContratDataReference pushContractLocation(SignedContratDataReference signedContratDataReference){
        return signedContractDataInMemoryRepository.save(signedContratDataReference);
    }

    public Optional<SignedContratDataReference> getContractLocation(String alias, String emaid, String pcid,
                                                                    String exiVersion){
        return signedContractDataInMemoryRepository.find(alias, emaid, pcid, exiVersion);
    }

    public List<SignedContratDataReference> getContractLocations(String alias, String emaid){
        return signedContractDataInMemoryRepository.find(alias, emaid);
    }

    public Optional<SignedContratDataReference> deleteContractLocation(String alias, String emaid, String pcid, String exiVersion){
        return signedContractDataInMemoryRepository.delete(alias, emaid, pcid, exiVersion);
    }

}
