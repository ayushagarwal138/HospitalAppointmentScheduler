package com.example.hospital.doctor;

import com.example.hospital.appointment.dto.BookingResponse;
import com.example.hospital.common.exception.NoAvailableDoctorException;
import com.example.hospital.common.exception.ResourceNotFoundException;
import com.example.hospital.doctor.dto.CreateDoctorRequest;
import com.example.hospital.doctor.dto.DoctorResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorResponse addDoctor(CreateDoctorRequest request) {
        Doctor doctor = new Doctor(
                request.getSpecialization().trim(),
                request.getMaxDailyPatients()
        );
        Doctor saved = doctorRepository.save(doctor);
        return toDoctorResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(this::toDoctorResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse bookAppointment(String specialization) {
        List<Doctor> doctors = doctorRepository.findBySpecializationIgnoreCase(specialization.trim());

        if (doctors.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No doctors found for specialization: " + specialization
            );
        }

        Doctor selected = doctors.stream()
                .filter(d -> d.getCurrentAppointments() < d.getMaxDailyPatients())
                .min(Comparator
                        .comparing(Doctor::getCurrentAppointments)
                        .thenComparing(Doctor::getDoctorId))
                .orElseThrow(() -> new NoAvailableDoctorException(
                        "All doctors for specialization '" + specialization + "' are fully booked for today."
                ));

        selected.setCurrentAppointments(selected.getCurrentAppointments() + 1);

        return new BookingResponse(
                "Appointment booked successfully.",
                selected.getDoctorId(),
                selected.getSpecialization(),
                selected.getCurrentAppointments(),
                selected.getMaxDailyPatients()
        );
    }

    private DoctorResponse toDoctorResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getDoctorId(),
                doctor.getSpecialization(),
                doctor.getMaxDailyPatients(),
                doctor.getCurrentAppointments()
        );
    }
}

