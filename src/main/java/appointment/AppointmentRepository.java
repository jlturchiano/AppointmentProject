//Joseph Turchiano
//2018

package appointment;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

//Exposes the Mongo repository to REST API
@RepositoryRestResource(collectionResourceRel = "appointments", path = "appointments")
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

	//Query sort= was added in Spring Data MongoDB 2.1.0; Needed Spring Boot 2.1.0 snapshot instead of 2.05
	@Query(value="{'appointmentDate' : {'$gt' : ?0, '$lt' : ?1}}", sort="{ price : 1 }")
	List<Appointment> findByAppointmentDateBetween(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, 
												   @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate);

	//Bonus queries
	//List<Appointment> findByNameOfDoctor(@Param("nameOfDoctor") String nameOfDoctor);
	//List<Appointment> findByAppointmentDuration(@Param("appointmentDuration") Integer appointmentDuration);
	//List<Appointment> findAllByOrderByPriceAsc();
}