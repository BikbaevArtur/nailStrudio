package com.bikbaev.nailstudio.telegram.event;

import com.bikbaev.nailstudio.entity.Service;
import com.bikbaev.nailstudio.telegram.service.ServiceService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class PriceEventHandler implements EventHandler{

    private final ServiceService serviceService;

    public PriceEventHandler(ServiceService service) {
        this.serviceService = service;
    }

    @Override
    public SendMessage handler(Update update) {
        StringBuilder stringBuilder = new StringBuilder("Прайс лист: ");
        List<Service> serviceList = serviceService.getAllService();

        for(Service service : serviceList){
            String text = String.format(
                    """
                            
                            ___________
                            %s
                            %s
                            """,service.getName(),service.getPrice()
            );
            stringBuilder.append(text);
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(stringBuilder.toString());
        return sendMessage;
    }
}
