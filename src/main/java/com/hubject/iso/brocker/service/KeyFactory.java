package com.hubject.iso.brocker.service;

import com.hubject.iso.brocker.model.SignedContratDataReference;

public class KeyFactory {

    public static String createKey(SignedContratDataReference signedContratDataReference) {
        return buildKey(signedContratDataReference.getAlias(), signedContratDataReference.getEmaid(), signedContratDataReference.getPcid(), signedContratDataReference.getExiVersion());
    }

    public static String buildKey(String alias, String emaid, String pcid, String exiVersion){
        return alias + "::" + emaid + "::" + pcid + "::" + exiVersion;
    }

}
