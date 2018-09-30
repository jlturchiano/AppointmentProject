package appointment;

import java.util.Random;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Runs the application
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private AppointmentRepository appointmentRepository;

    public static void main(String[] args ) {
        SpringApplication.run(Application.class, args);
    }

    //Clear down database and schedule some random appointments at startup
    @Override
    public void run(String... args) throws Exception {

    	Random rand = new Random();
    	appointmentRepository.deleteAll();

    	int[] durations = new int[]{30, 60, 90, 120};
    	String[] names = new String[]{"Dr. Brown", "Dr. Eggman", "Dr. Faustus", "Dr. Frankenstein", "Dr. Heidegger", 
    								  "Dr. Lazarus", "Dr. No", "Dr. Phil", "Dr. Strange", "Dr. Who"};

    	for (int i = 0; i < 100; i++) {
    		Appointment appointment = new Appointment();
    		appointment.setCreatedAt(LocalDateTime.now());
    		appointment.setAppointmentDate(LocalDateTime.of(2018, 10, rand.nextInt(31)+1, rand.nextInt(24), 0));
    		appointment.setAppointmentDuration(durations[rand.nextInt(4)]);
    		appointment.setNameOfDoctor(names[rand.nextInt(10)]);
    		appointment.setStatus(Status.AVAILABLE);
    		appointment.setPrice((double)rand.nextInt(100));
    		appointmentRepository.save(appointment);
    	}
    }
}