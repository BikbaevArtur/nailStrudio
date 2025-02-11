package com.bikbaev.nailstudio.telegram.bot;

import com.bikbaev.nailstudio.telegram.event.MasterEventHandler;
import com.bikbaev.nailstudio.telegram.event.PriceEventHandler;
import com.bikbaev.nailstudio.telegram.event.SendingContactEvent;
import com.bikbaev.nailstudio.telegram.event.StartEventHandler;
import com.bikbaev.nailstudio.telegram.event.ClientInfoEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class NailStudioAppointment_bot extends TelegramLongPollingBot {

    private static final String START = "/start";
    private static final String MASTER_LIST = "\uD83D\uDCCB список мастеров";
    private static final String PRICE_LIST = "\uD83D\uDCB0 прайс";
    private static final String PROFILE_INFO = "❤\uFE0F мои данные";

    private final StartEventHandler startEventHandler;
    private final SendingContactEvent sendingContactEvent;
    private final MasterEventHandler masterEventHandler;
    private final PriceEventHandler priceEventHandler;
    private final ClientInfoEvent clientInfoEvent;


    public NailStudioAppointment_bot(@Value("${bot.token}")
                                     String botToken,
                                     StartEventHandler startEventHandler,
                                     SendingContactEvent sendingContactEvent,
                                     MasterEventHandler masterEventHandler,
                                     PriceEventHandler priceEventHandler,
                                     ClientInfoEvent clientInfoEvent) {
        super(botToken);
        this.startEventHandler = startEventHandler;
        this.sendingContactEvent = sendingContactEvent;
        this.masterEventHandler = masterEventHandler;
        this.priceEventHandler = priceEventHandler;
        this.clientInfoEvent = clientInfoEvent;
    }


    @Override
    public void onUpdateReceived(Update update) {

        if (!update.hasMessage()) {
            return;
        }
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        if (START.equals(message)) {
            sentMessage(startEventHandler.handler(update));
        }
        if (update.hasMessage() && update.getMessage().hasContact()) {
            sentMessage(sendingContactEvent.handler(update));
        }
        if (MASTER_LIST.equals(message)) {
            sentMessage(masterEventHandler.handler(update));
        }
        if (PRICE_LIST.equals(message)) {
            sentMessage(priceEventHandler.handler(update));
        }
        if(PROFILE_INFO.equals(message)){
            sentMessage(clientInfoEvent.handler(update));
        }

    }

    @Override
    public String getBotUsername() {
        return "NailStudioAppointment_bot";
    }


    private void sentMessage(Long chatId, String text) {
        String strChatId = String.valueOf(chatId);
        var sendMessage = new SendMessage(strChatId, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void sentMessage(SendMessage message) {
        try {

            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}