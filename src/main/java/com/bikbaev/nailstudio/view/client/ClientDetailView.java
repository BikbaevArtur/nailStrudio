package com.bikbaev.nailstudio.view.client;

import com.bikbaev.nailstudio.entity.Client;
import com.bikbaev.nailstudio.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "clients/:id", layout = MainView.class)
@ViewController(id = "Client.detail")
@ViewDescriptor(path = "client-detail-view.xml")
@EditedEntityContainer("clientDc")
public class ClientDetailView extends StandardDetailView<Client> {
    @Subscribe
    public void onInitEntity(final InitEntityEvent<Client> event) {
        event.getEntity().setDiscount(0);
    }

}