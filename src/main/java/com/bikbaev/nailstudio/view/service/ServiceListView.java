package com.bikbaev.nailstudio.view.service;

import com.bikbaev.nailstudio.entity.Service;
import com.bikbaev.nailstudio.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "services", layout = MainView.class)
@ViewController(id = "Service.list")
@ViewDescriptor(path = "service-list-view.xml")
@LookupComponent("servicesDataGrid")
@DialogMode(width = "64em")
public class ServiceListView extends StandardListView<Service> {
}