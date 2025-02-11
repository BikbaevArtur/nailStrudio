package com.bikbaev.nailstudio.telegram.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TelegramKeyboardReply {

    public static SendMessage mainMenuKeyboard(Long chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Выберите действие: ");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        keyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("\uD83D\uDCCB список мастеров");
        row1.add("\uD83D\uDCB0 прайс");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("\uD83D\uDCB0 записаться");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("❤\uFE0F мои данные");

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        keyboardMarkup.setKeyboard(keyboard);

        sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }


    public static SendMessage chooseMonth(Long chatId) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите месяц для записи");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        keyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        LocalDate nowDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL", new Locale("ru", "RU"));

        KeyboardRow keyboardRow = new KeyboardRow();

        for (int i = 0; i < 3; i++) {
            LocalDate monthDate = nowDate.plusMonths(i);
            String monthName = monthDate.format(formatter);
            keyboardRow.add(monthName);
        }
        keyboard.add(keyboardRow);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;

    }


    public static SendMessage appointment(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Посмотрите свободные даты и запишитесь");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("\uD83D\uDCC6 свободные даты");
        row1.add("\uD83D\uDCC5 записаться");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("⬅\uFE0F главное меню");


        keyboard.add(row1);
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        return sendMessage;
    }


    public static SendMessage getContactKeyboard(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Нажмите кнопку ниже, чтобы поделиться своим номером телефона:");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton("\uD83D\uDCF1 Поделиться номером телефона");
        keyboardButton.setRequestContact(true);
        row.add(keyboardButton);

        keyboard.add(row);

        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }


}
