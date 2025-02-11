package com.bikbaev.nailstudio.telegram.config;

import com.bikbaev.nailstudio.telegram.bot.NailStudioAppointment_bot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Configuration
public class NailStudioBotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(NailStudioAppointment_bot nailStudioAppointmentBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(nailStudioAppointmentBot);
        return api;
    }
}