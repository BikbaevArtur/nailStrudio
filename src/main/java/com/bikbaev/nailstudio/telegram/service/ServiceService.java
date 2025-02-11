package com.bikbaev.nailstudio.telegram.service;

import com.bikbaev.nailstudio.entity.Service;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    private final DataManager dataManager;
    private final SystemAuthenticator systemAuthenticator;

    public ServiceService(DataManager dataManager, SystemAuthenticator systemAuthenticator) {
        this.dataManager = dataManager;
        this.systemAuthenticator = systemAuthenticator;
    }

    public List<Service> getAllService() {
        return systemAuthenticator.withSystem(
                ()->
                        dataManager
                                .load(Service.class)
                                .all()
                                .list()
        );
    }


}
