package com.bikbaev.nailstudio.telegram.event;

import com.bikbaev.nailstudio.entity.User;
import com.bikbaev.nailstudio.telegram.service.MasterService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class MasterEventHandler implements EventHandler {

    private final MasterService masterService;

    public MasterEventHandler(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public SendMessage handler(Update update) {
        long chatId = update.getMessage().getChatId();

        List<User> masters = masterService.getAllMasters();
        StringBuilder masterInfo = new StringBuilder("Cписок мастеров: ");

        for (int i = 0; i < masters.size(); i++) {
            String firstName = masters.get(i).getFirstName();
            String lastName = masters.get(i).getLastName();
            String phone = masters.get(i).getPhone();
            String masterDetail = String.format(
                    """
                                                        
                            ________________
                            👩
                            %s
                            %s
                            %s
                            """, firstName, lastName, phone
            );
            masterInfo.append(masterDetail);
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(masterInfo.toString());
        return sendMessage;
    }
}
