package com.kh.baribari.message.controller;

import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.message.domain.Message;
import com.kh.baribari.message.service.MessageService;
import com.kh.baribari.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService mService;

    @Autowired
    private ReturnUser returnUser;

    //  받은 메시지 로딩
    @GetMapping("message")
    public String messageView(
            Authentication authentication,
            Model model
    ) {
        User user = returnUser.returnUser(authentication);
        List<Message> messageList = mService.selectReceiveMessageList(user.getUserNo());
        model.addAttribute("messageList", messageList);
        return "message/message";
    }

    //    보낸 메시지 로딩
    @GetMapping("messageSend")
    public String messageSendView(
            Authentication authentication,
            Model model
    ) {
        User user = returnUser.returnUser(authentication);
        List<Message> messageList = mService.selectSendMessageList(user.getUserNo());
        model.addAttribute("messageList", messageList);
        return "message/messageSend";
    }

//    쪽지 작성창 뷰
    @GetMapping("/messageWrite")
    public String messageWrite() {
        return "message/messageWrite";
    }

//    쪽지 오픈시 읽음 표시
    @PostMapping("/msgOpen")
    @ResponseBody
    public String ajaxMsgOpen(int messageNo) {
        int result = mService.updateMsgOpen(messageNo);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

//    유저 검색 ajax
    @GetMapping("/searchUser")
    @ResponseBody
    public String ajaxSearchUser(String userNickname) {
        User user = mService.selectSearchUser(userNickname);
        if (user != null) {
            return String.valueOf(user.getUserNo());
        }else {
            return "실패";
        }
    }

//    쪽지 보내기
    @PostMapping("/msgSend")
    @ResponseBody
    public String messageSend(
            @ModelAttribute Message message
    ){
        int result = mService.insertMsgSend(message);
        if(result > 0){
            return "<script>alert('쪽지 전송이 잘되었어요!'); location.href='/message';</script>";
        }else {
            return "<script>alert('쪽지 전송이 실패했어요 ㅠㅠ');</script>";
        }
    }


}
