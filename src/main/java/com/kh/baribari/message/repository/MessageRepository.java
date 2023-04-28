package com.kh.baribari.message.repository;

import com.kh.baribari.message.domain.Message;

import java.util.List;

public interface MessageRepository {
//    받은 메시지 리스트 로딩
    List<Message> selectReceiveMessageList(int userNo);

//  메시지 open 여부 업데이트
    int updateMsgOpen(int messageNo);

//   보낸 메시지 리스트 로딩
    List<Message> selectSendMessageList(int userNo);
}
