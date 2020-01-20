package com.hubject.iso.brocker.repositrory;

import com.hubject.iso.brocker.model.SignedContratDataReference;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface SignedContractDataInMemoryRepositoryInt {
    SignedContratDataReference save(final SignedContratDataReference signedContratDataReference);

    List<SignedContratDataReference> find(final String alias, final String emaid);

    Optional<SignedContratDataReference> find(final String alias, final String emaid, final String pcid, final String exiVersion);

    Optional<SignedContratDataReference> delete(final String alias, final String emaid, final String pcid, final String exiVersion);
}
