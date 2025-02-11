package com.bikbaev.nailstudio.telegram.event;

import com.bikbaev.nailstudio.entity.Client;
import com.bikbaev.nailstudio.telegram.service.ClientService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class SendingContactEvent implements EventHandler{

    private final ClientService clientService;

    public SendingContactEvent(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handler(Update update) {

        Contact contact = update.getMessage().getContact();
        User user = update.getMessage().getFrom();
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if(clientService.findPhone(contact.getPhoneNumber()) == null){
            clientService.createClient(contact,user,chatId);
            sendMessage.setText("Вы успешно зарегестрированы");
            return sendMessage;
        }
        else {
            Client client = clientService.findPhone(contact.getPhoneNumber());
            clientService.updateClient(client);
            sendMessage.setText("Ваш профиль телеграм успешно добавлен в базу клиентов");
            return sendMessage;
        }
    }
}
