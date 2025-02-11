package com.bikbaev.nailstudio.view.service;

import com.bikbaev.nailstudio.entity.Service;
import com.bikbaev.nailstudio.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "services/:id", layout = MainView.class)
@ViewController(id = "Service.detail")
@ViewDescriptor(path = "service-detail-view.xml")
@EditedEntityContainer("serviceDc")
public class ServiceDetailView extends StandardDetailView<Service> {
}