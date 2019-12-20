package com.hubject.iso.brocker.repositrory;

import com.hubject.iso.brocker.model.SignedContratDataReference;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface SignedContractDataInMemoryRepositoryInt {
    SignedContratDataReference save(final SignedContratDataReference signedContratDataReference);

    Optional<SignedContratDataReference> find(final String emaid, final String pcid, final String exiVersion);

    Optional<SignedContratDataReference> delete(final String emaid, final String pcid, final String exiVersion);
}
