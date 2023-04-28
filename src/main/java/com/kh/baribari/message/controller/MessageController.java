package com.kh.baribari.message.controller;

import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.message.domain.Message;
import com.kh.baribari.message.service.MessageService;
import com.kh.baribari.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("messageList",messageList);
        return "message/messageSend";
    }

    @GetMapping("/messageWrite")
    public String messageWrite(){
        return "message/messageWrite";
    }

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


}
