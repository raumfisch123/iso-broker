package com.hubject.iso.brocker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SignedContratDataReference {

    // dont forget to validate the emaid pcid exiVerson date and url :-)
    private String emaid;
    private String pcid;
    private String exiVersion;
    private Date created;
    private String url;

}
