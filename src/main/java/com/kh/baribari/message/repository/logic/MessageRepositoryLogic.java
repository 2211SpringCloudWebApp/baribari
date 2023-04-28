package com.kh.baribari.message.repository.logic;

import com.kh.baribari.message.domain.Message;
import com.kh.baribari.message.repository.MessageRepository;
import com.kh.baribari.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepositoryLogic implements MessageRepository {
    @Autowired
    private SqlSession session;

    @Override
    public List<Message> selectReceiveMessageList(int userNo) {
        return session.selectList("MessageMapper.selectReceiveMessageList",userNo);
    }

    @Override
    public int updateMsgOpen(int messageNo) {
        return session.update("MessageMapper.updateMsgOpen",messageNo);
    }

//    보낸 메시지 리스트 로딩
    @Override
    public List<Message> selectSendMessageList(int userNo) {
        return session.selectList("MessageMapper.selectSendMessageList",userNo);
    }

    @Override
    public User selectSearchUser(String userNickname) {
        return session.selectOne("MessageMapper.selectSearchUser", userNickname);
    }

    @Override
    public int insertMsgSend(Message message) {
        return session.insert("MessageMapper.insertMsgSend",message);
    }
}
