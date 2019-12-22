package com.hubject.iso.brocker.model;

import java.util.Date;

public class SignedContractDataReferendeFactory {

    public static SignedContratDataReference create(String alaias, String emaid, String pcid, String exiVersion,
                                                    Date validUntil, String url){
        return new SignedContratDataReference(alaias, emaid, pcid, exiVersion, validUntil, url);
    }

}
