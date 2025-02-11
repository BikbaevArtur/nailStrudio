package com.bikbaev.nailstudio.view.makeappointment;

import com.bikbaev.nailstudio.entity.AppointmentStatus;
import com.bikbaev.nailstudio.entity.MakeAppointment;
import com.bikbaev.nailstudio.entity.Service;
import com.bikbaev.nailstudio.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;

import java.math.BigDecimal;
import java.util.List;

@Route(value = "makeAppointments/:id", layout = MainView.class)
@ViewController(id = "MakeAppointment.detail")
@ViewDescriptor(path = "make-appointment-detail-view.xml")
@EditedEntityContainer("makeAppointmentDc")
public class MakeAppointmentDetailView extends StandardDetailView<MakeAppointment> {


    @ViewComponent
    private InstanceContainer<MakeAppointment> makeAppointmentDc;

    @ViewComponent
    private CollectionContainer<Service> serviceDc;

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        recalculateTotalSum();
    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<MakeAppointment> event) {
        event.getEntity().setStatus(AppointmentStatus.NEW);
    }


    private void recalculateTotalSum() {
        List<Service> services = serviceDc.getItems();
        if(!services.isEmpty()){
            int discount = makeAppointmentDc.getItem().getClient().getDiscount();
            BigDecimal discountPercent = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(100)));
            BigDecimal totalSum = services.stream()
                    .map(service -> service.getPrice() != null ? service.getPrice() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalSum = totalSum.multiply(discountPercent);
            makeAppointmentDc.getItem().setPayment(totalSum);
        }
    }


}