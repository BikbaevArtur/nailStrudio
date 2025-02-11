package com.bikbaev.nailstudio.view.makeappointment;

import com.bikbaev.nailstudio.entity.MakeAppointment;
import com.bikbaev.nailstudio.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "makeAppointments", layout = MainView.class)
@ViewController(id = "MakeAppointment.list")
@ViewDescriptor(path = "make-appointment-list-view.xml")
@LookupComponent("makeAppointmentsDataGrid")
@DialogMode(width = "64em")
public class MakeAppointmentListView extends StandardListView<MakeAppointment> {
}