package com.bikbaev.nailstudio.telegram.service;


import com.bikbaev.nailstudio.entity.Client;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class ClientService {

    private final DataManager dataManager;
    private final SystemAuthenticator systemAuthenticator;

    public ClientService(DataManager dataManager, SystemAuthenticator systemAuthenticator) {
        this.dataManager = dataManager;
        this.systemAuthenticator = systemAuthenticator;
    }

    public Client findChatId(Long chatId) {

        return systemAuthenticator.withSystem(() -> dataManager.load(Client.class)
                .query("select c from Client c where c.chatID = :chatId ")
                .parameter("chatId", chatId)
                .optional()
                .orElse(null));
    }

    public Client findPhone(String phone) {
        return systemAuthenticator.withSystem(() -> dataManager.load(Client.class)
                .query("select c from Client c where c.phone =:phone")
                .parameter("phone", phone)
                .optional()
                .orElse(null));
    }

    public Client createClient(Contact contact, User user, Long chatId) {
        Client client = systemAuthenticator.withSystem(
                () -> dataManager.create(Client.class));
        client.setFirst_name(user.getFirstName());
        client.setLast_name(user.getLastName());
        client.setPhone(contact.getPhoneNumber());
        client.setDiscount(0);
        client.setChatID(chatId);

        return systemAuthenticator.withSystem(
                () ->
                        dataManager.save(client)
        );
    }

    public Client updateClient(Client client) {
        return systemAuthenticator.withSystem(
                () ->
                        dataManager.save(client)
        );
    }
}