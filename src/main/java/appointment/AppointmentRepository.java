//Joseph Turchiano
//2018

package appointment;

import java.util.List;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

@RepositoryRestResource(collectionResourceRel = "appointments", path = "appointments")
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

	List<Appointment> findByNameOfDoctor(@Param("nameOfDoctor") String nameOfDoctor);

	List<Appointment> findByAppointmentDuration(@Param("appointmentDuration") Integer appointmentDuration);

	List<Appointment> findByAppointmentDateBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, 
												   			  @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate);

	List<Appointment> findAllByOrderByPriceAsc();
}