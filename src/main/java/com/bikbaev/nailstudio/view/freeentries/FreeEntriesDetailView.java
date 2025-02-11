package com.bikbaev.nailstudio.view.freeentries;

import com.bikbaev.nailstudio.entity.FreeEntries;
import com.bikbaev.nailstudio.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "freeEntrieses/:id", layout = MainView.class)
@ViewController(id = "FreeEntries.detail")
@ViewDescriptor(path = "free-entries-detail-view.xml")
@EditedEntityContainer("freeEntriesDc")
public class FreeEntriesDetailView extends StandardDetailView<FreeEntries> {
}