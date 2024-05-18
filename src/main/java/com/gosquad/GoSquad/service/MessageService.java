package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages();

    Boolean createMessage(Message message);

    Boolean updateMessage(Message message);

    Boolean deleteMessage(String id);
}
