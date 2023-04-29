package com.kh.baribari.message.repository;

import com.kh.baribari.message.domain.Message;
import com.kh.baribari.user.domain.User;

import java.util.List;

public interface MessageRepository {
//    받은 메시지 리스트 로딩
    List<Message> selectReceiveMessageList(int userNo);

//  메시지 open 여부 업데이트
    int updateMsgOpen(int messageNo);

//   보낸 메시지 리스트 로딩
    List<Message> selectSendMessageList(int userNo);
//  유저검색 , ajax
    User selectSearchUser(String userNickname);
//  메시지 전송
    int insertMsgSend(Message message);
//  메시지 안읽은 갯수 가져오기
    int selectMsgRead(int userNo);
}
