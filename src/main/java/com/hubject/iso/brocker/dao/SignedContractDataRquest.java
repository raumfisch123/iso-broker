package com.hubject.iso.brocker.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SignedContractDataRquest {

    private final String url;
    private final Date validUntil;

}
