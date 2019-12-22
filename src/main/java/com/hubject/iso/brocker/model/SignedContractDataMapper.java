package com.hubject.iso.brocker.model;

import com.hubject.iso.brocker.dao.SignedContractDataResponse;
import com.hubject.iso.brocker.dao.SignedContractDataRquest;

public interface SignedContractDataMapper {

    SignedContractDataResponse mapp(SignedContratDataReference signedContratDataReference);

    SignedContratDataReference mapp(SignedContractDataRquest signedContractDataRquest);

}
