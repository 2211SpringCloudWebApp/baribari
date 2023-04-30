package com.kh.baribari.message.domain;

import lombok.Data;
import java.sql.Timestamp;
@Data
public class Message {
    private int messageNo;
    private String messageContent;
    private String messageDate;
    private int messageSendNo;
    private int messageReceiveNo;
    private int messageOpenYn;
    private String userNickname;

}
