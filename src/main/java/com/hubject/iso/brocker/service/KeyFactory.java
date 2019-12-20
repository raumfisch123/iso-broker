package com.hubject.iso.brocker.service;

import com.hubject.iso.brocker.model.SignedContratDataReference;

public interface KeyFactory {

    default String createKey(SignedContratDataReference signedContratDataReference) {
        return buildKey(signedContratDataReference.getEmaid(), signedContratDataReference.getPcid(), signedContratDataReference.getExiVersion());
    }

    default String buildKey(String emaid, String pcid, String exiVersion){
        return emaid + "::" + pcid + "::" + exiVersion;
    }

}
