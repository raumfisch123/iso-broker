package com.hubject.iso.brocker.repositrory;

import com.hubject.iso.brocker.model.SignedContratDataReference;
import com.hubject.iso.brocker.service.KeyFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SignedContractDataInMemoryRepository implements SignedContractDataInMemoryRepositoryInt {


    private final Map<String, String> repository = new HashMap<>();

    @Override
    public SignedContratDataReference save(final SignedContratDataReference signedContratDataReference){
        Objects.requireNonNull(signedContratDataReference, "SignedContractDataReference should not be null.");
        repository.put(KeyFactory.createKey(signedContratDataReference), signedContratDataReference.getUrl());
        return signedContratDataReference;
    }

    @Override
    public Optional<SignedContratDataReference> find( final String alias, final String emaid,  final String pcid,  final String exiVersion){
        return Optional
                .ofNullable(repository.get(KeyFactory.buildKey(alias, emaid, pcid, exiVersion)))
                .map(url -> new SignedContratDataReference(alias, emaid, pcid, exiVersion, new Date(), url));
    }

    @Override
    public Optional<SignedContratDataReference> delete( final String alias, final String emaid,  final String pcid,  final String exiVersion){
        return Optional
                .ofNullable(repository.remove(KeyFactory.buildKey(alias, emaid, pcid, exiVersion)))
                .map(url -> new SignedContratDataReference(alias, emaid, pcid, exiVersion, new Date(), url));
    }

}
