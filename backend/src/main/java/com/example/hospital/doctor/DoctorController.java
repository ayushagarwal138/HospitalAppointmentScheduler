package com.example.hospital.doctor;

import com.example.hospital.appointment.dto.BookAppointmentRequest;
import com.example.hospital.appointment.dto.BookingResponse;
import com.example.hospital.doctor.dto.CreateDoctorRequest;
import com.example.hospital.doctor.dto.DoctorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorResponse> addDoctor(@Valid @RequestBody CreateDoctorRequest request) {
        DoctorResponse response = doctorService.addDoctor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping("/appointments/book")
    public ResponseEntity<BookingResponse> bookAppointment(@Valid @RequestBody BookAppointmentRequest request) {
        BookingResponse response = doctorService.bookAppointment(request.getSpecialization());
        return ResponseEntity.ok(response);
    }
}

