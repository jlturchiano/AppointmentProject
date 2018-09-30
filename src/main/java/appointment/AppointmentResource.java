//Joseph Turchiano
//2018

package appointment;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Provides REST endpoints for creating, retrieving, 
//updating, and deleting records
@RestController
@RequestMapping("/appointments")
public class AppointmentResource {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@GetMapping("/get")
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public Appointment getAppointment(@PathVariable String id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);

		return appointment.get();
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAppointment(@PathVariable String id) {
		appointmentRepository.deleteById(id);
	}

	@PostMapping("/create")
	public Appointment createAppointment(@RequestBody Appointment appointment) {

		appointment.setCreatedAt(LocalDateTime.now());
		if (appointment.getStatus() == null) appointment.setStatus(Status.AVAILABLE);
		Appointment saved = appointmentRepository.save(appointment);
		return saved;
	}

	@PutMapping("/update/{id}")
	public Appointment updateAppointmentStatus(@PathVariable String id, @RequestBody Appointment appStatus) {
		Status status = appStatus.getStatus();
		Optional<Appointment> appointment = appointmentRepository.findById(id);

		if (appointment.isPresent()) {
			Appointment found = appointment.get();
			found.setStatus(status);
			Appointment saved = appointmentRepository.save(found);
			return saved;
		}
		else {
			return appointment.get();
		}
	}
}