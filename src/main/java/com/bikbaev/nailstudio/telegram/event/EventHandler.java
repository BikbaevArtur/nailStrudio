package com.bikbaev.nailstudio.telegram.event;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface EventHandler {

    SendMessage handler (Update update);

}
