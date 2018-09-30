README

2018
Created by Joseph Turchiano

Installation/Run instructions:

To run via command line: 	./mvnw spring-boot:run
To build/run as a JAR: 		./mvnw clean package

This application requires an installation of MongoDB listening on port 8080.
It should find and connect to MongoDB automatically.

!!IMPORTANT!! - THE APPLICATION WILL DROP THE DATABASE ON STARTUP

Use Instructions:

On startup the Application will clear down the database and insert 100
random Appointments.

Any REST client (ex: curl, Postman) may be used to target the application 
at http://localhost:8080. The API expects and returns data in 
application/json format

The following API functions are available at these extensions:

/get			HTTP GET returns all Appointments in the database
/get/{id}		HTTP GET returns the Appointment with id value {id}
/delete/{id}	HTTP DELETE deletes the Appointment with id value {id}
/create 		HTTP POST creates an Appointment with values denoted by the 
				given JSON object
/update/{id}	HTTP PUT updates the status field of the Appointment with
				id value {id}

/findByAppointmentDateBetween?startDate={startDate}&endDate={endDate}

HTTP GET returns all Appointments between {startDate} and {endDate} and
sorts by increasing price. 

Dates should be given in ISO 8601 format (yyyy-MM-ddTHH:mm:ss.SSSZ)
									ex:	  2018-10-01T08:00:00.0000

Valid JSON fields are:
field 				dataType

createdAt			Date/Time
appointmentDate 	Date/Time
appointmentDuration	integer
nameOfDoctor		String/text
status				"AVAILABLE" or "BOOKED"
price				double


