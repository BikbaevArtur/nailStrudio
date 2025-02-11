package com.bikbaev.nailstudio.view.freeentrieslist;


import com.bikbaev.nailstudio.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.view.*;
import io.jmix.fullcalendarflowui.component.event.DateClickEvent;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "free-entries-list", layout = MainView.class)
@ViewController(id = "FreeEntriesList")
@ViewDescriptor(path = "free-entries-list.xml")
public class FreeEntriesList extends StandardView {
    @Autowired
    private Dialogs dialogs;

    @Subscribe("calendar")
    public void onCalendarDateClick(final DateClickEvent event) {
    }

}