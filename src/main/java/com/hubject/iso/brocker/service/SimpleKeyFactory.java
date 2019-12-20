package com.hubject.iso.brocker.service;

import com.hubject.iso.brocker.model.SignedContratDataReference;

public class SimpleKeyFactory implements KeyFactory{

    @Override
    public String createKey(SignedContratDataReference signedContratDataReference) {
        return signedContratDataReference.getEmaid() + "::" + signedContratDataReference.getPcid() + "::" + signedContratDataReference.getExiVersion();
    }

}
