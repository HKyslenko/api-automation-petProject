package org.example.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTOs.POJO.*;
import org.example.common.Context;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ComplexJSONTests {

    private Context context;

    private UniversityBody universityBody;
    private UniversityDetails universityDetails;
    private LocationDetails locationDetails;

    List<DepartmentsDetails> departmentsDetails;
    List<CampusesDetails> campusesDetails;

    @Before
    public void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        context = new Context("complexJson");
        universityBody = objectMapper.readValue(
                context.getResponse().getBody().prettyPrint(), UniversityBody.class);

        universityDetails = universityBody.getUniversity();
        locationDetails = universityDetails.getLocation();

        departmentsDetails = universityDetails.getDepartments();
        campusesDetails = locationDetails.getCampuses();
    }

    @Test
    public void schemaValidation() {
        File schema = new File("src/test/java/org/example/schemas/complexJsonSchema.json");
        assertThat(context.getResponse().getBody().prettyPrint(), matchesJsonSchema(schema));
    }

    @Test
    public void universityVerification() {
        assertEquals("Grand University", universityDetails.getName());
    }

    @Test
    public void departmentVerification() {
        assertTrue(departmentsDetails.stream().anyMatch(department -> department.getHead().equals("Dr. Brown")));
        assertTrue(departmentsDetails.stream().anyMatch(department -> department.getName().equals("Computer Science")));
    }

    @Test
    public void coursesVerification() {
        assertTrue(departmentsDetails.stream().anyMatch(department ->
                department.getCourses().stream().anyMatch(course ->
                        course.getCode().equals("CS101"))));

        assertTrue(departmentsDetails.stream().anyMatch(department ->
                department.getCourses().stream().anyMatch(course ->
                        course.getInstructor().equals("Prof. Williams"))));

        assertTrue(departmentsDetails.stream().anyMatch(department ->
                department.getCourses().stream().anyMatch(course ->
                        course.getName().equals("Introduction to Biology"))));
    }

    @Test
    public void studentsVerification() {
        assertTrue(departmentsDetails.stream().anyMatch(department ->
                department.getCourses().stream().anyMatch(course ->
                        course.getStudents().contains("John"))));

        assertTrue(departmentsDetails.stream().anyMatch(department ->
                department.getCourses().stream().anyMatch(course ->
                        course.getStudents().contains("Daniel"))));

        assertTrue(departmentsDetails.stream().anyMatch(department ->
                department.getCourses().stream().anyMatch(course ->
                        course.getStudents().contains("Ava"))));

        assertTrue(departmentsDetails.stream().anyMatch(department ->
                department.getCourses().stream().anyMatch(course ->
                        course.getStudents().contains("Mia"))));
    }

    @Test
    public void locationVerification() {
        assertEquals("Metropolis", locationDetails.getCity());
        assertEquals("USA", locationDetails.getCountry());
    }

    @Test
    public void campusesVerification() {
        assertTrue(campusesDetails.stream().anyMatch(campus -> campus.getAddress().equals("123 University Ave")));
        assertTrue(campusesDetails.stream().anyMatch(campus -> campus.getName().equals("Downtown Campus")));
    }

    @Test
    public void facilitiesVerification() {
        assertTrue(campusesDetails.stream().anyMatch(campus ->
                campus.getFacilities().stream().anyMatch(facility ->
                        facility.getFloors() == 5)));

        assertTrue(campusesDetails.stream().anyMatch(campus ->
                campus.getFacilities().stream().anyMatch(facility ->
                        facility.getName().equals("Gymnasium"))));
    }

    @Test
    public void equipmentVerification() {
        assertTrue(campusesDetails.stream().anyMatch(campus ->
                campus.getFacilities().stream().anyMatch(facility ->
                        facility.getEquipment() != null && facility.getEquipment().contains("Bunsen burners"))));

        assertTrue(campusesDetails.stream().anyMatch(campus ->
                campus.getFacilities().stream().anyMatch(facility ->
                        facility.getEquipment() != null && facility.getEquipment().contains("Test tubes"))));
    }

    @Test
    public void staffVerification() {
        assertTrue(campusesDetails.stream().anyMatch(campus ->
                campus.getFacilities().stream().anyMatch(facility ->
                        facility.getStaff() != null && facility.getStaff().contains("Alice"))));

        assertTrue(campusesDetails.stream().anyMatch(campus ->
                campus.getFacilities().stream().anyMatch(facility ->
                        facility.getStaff() != null && facility.getStaff().contains("David"))));
    }
}