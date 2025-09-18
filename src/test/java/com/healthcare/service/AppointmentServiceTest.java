package com.healthcare.service;

import com.healthcare.service.AppointmentService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Date;

public class AppointmentServiceTest {
    AppointmentService service = new AppointmentService();

    @Test
    public void testBookAppointment() throws Exception {
        service.bookAppointment(1, 2, new Timestamp(new Date().getTime()));
        Assert.assertTrue(service.getAllAppointments().size() > 0);
    }
}
