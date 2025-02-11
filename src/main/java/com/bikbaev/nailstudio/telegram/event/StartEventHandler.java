package com.bikbaev.nailstudio.telegram.event;

import com.bikbaev.nailstudio.telegram.service.ClientService;
import com.bikbaev.nailstudio.telegram.util.TelegramKeyboardReply;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartEventHandler implements EventHandler{

    public final ClientService clientService;

    public StartEventHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handler(Update update) {
        long chatId = update.getMessage().getChatId();
        if(clientService.findChatId(chatId) != null){
            System.out.println(clientService.findChatId(chatId));
            return TelegramKeyboardReply.mainMenuKeyboard(chatId);
        }else {
            return TelegramKeyboardReply.getContactKeyboard(chatId);
        }

    }
}
