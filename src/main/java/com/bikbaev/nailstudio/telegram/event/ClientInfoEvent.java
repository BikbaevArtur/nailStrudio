package com.bikbaev.nailstudio.telegram.event;

import com.bikbaev.nailstudio.entity.Client;
import com.bikbaev.nailstudio.telegram.service.ClientService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class ClientInfoEvent implements EventHandler{

    private final ClientService clientService;

    public ClientInfoEvent(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public SendMessage handler(Update update) {


        long chatId = update.getMessage().getChatId();

        Client client = clientService.findChatId(chatId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton updateInfo = new InlineKeyboardButton("Изменить: ");
        updateInfo.setCallbackData("update");

        List<InlineKeyboardButton> row1 = List.of(updateInfo);
        inlineKeyboardMarkup.setKeyboard(List.of(row1));

        String text = textMessage(
                client.getFirst_name(),
                client.getLast_name(),
                client.getPhone(),
                client.getDiscount()
        );


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    private String textMessage(String firstName,String lastName,String phone,Integer discount){
        return String.format(
                """
                Личные данные:
                ---------------------------------
                ФИО    :    %s %s
                Телефон:    %s
                Скидка :    %s%%
                ---------------------------------
                """,firstName,lastName,phone,discount) ;
    }
}
