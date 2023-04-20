package com.kh.baribari.user.domain;

import lombok.Data;

@Data
public class Address {
    private int addressNo;
    private int userNo;
    private String addressZipCode;
    private String addressName;
    private String addressPickup;
    private String addressJibun;
    private String addressJibunDetail;
    private String addressPhone;
}
