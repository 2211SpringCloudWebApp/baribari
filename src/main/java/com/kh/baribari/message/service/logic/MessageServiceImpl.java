package com.kh.baribari.message.service.logic;

import com.kh.baribari.message.domain.Message;
import com.kh.baribari.message.repository.MessageRepository;
import com.kh.baribari.message.service.MessageService;
import com.kh.baribari.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository mRepository;


    @Override
    public List<Message> selectReceiveMessageList(int userNo) {
        return mRepository.selectReceiveMessageList(userNo);
    }

//  메시지 open 여부 업데이트
    @Override
    public int updateMsgOpen(int messageNo) {
        return mRepository.updateMsgOpen(messageNo);
    }

//  보낸 메시지 리스트 로딩
    @Override
    public List<Message> selectSendMessageList(int userNo) {
        return mRepository.selectSendMessageList(userNo);
    }

    @Override
    public User selectSearchUser(String userNickname) {
        return mRepository.selectSearchUser(userNickname);
    }

    @Override
    public int insertMsgSend(Message message) {
        return mRepository.insertMsgSend(message);
    }
}
