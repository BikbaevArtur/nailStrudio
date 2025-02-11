package com.bikbaev.nailstudio.telegram.service;


import com.bikbaev.nailstudio.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {

    private final DataManager dataManager;
    SystemAuthenticator systemAuthenticator;

    public MasterService(DataManager dataManager, SystemAuthenticator systemAuthenticator) {
        this.dataManager = dataManager;
        this.systemAuthenticator = systemAuthenticator;
    }


    public List<User> getAllMasters(){
        List<User> masters = systemAuthenticator.withSystem(()->dataManager.load(User.class).all().list());
        masters.removeIf(m->m.getUsername().equals("admin"));
        masters.removeIf(m->m.getUsername().equals("bot"));
      return masters;

    }

}