/*
 * Copyright 2016 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.uqam.inf4375.springboot_rest.api;

import ca.uqam.inf4375.springboot_rest.model.Course;
import ca.uqam.inf4375.springboot_rest.model.Grade;
import ca.uqam.inf4375.springboot_rest.model.MapRepository;
import ca.uqam.inf4375.springboot_rest.model.Student;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students/{sId}")
public class StudentGradesController {

    MapRepository<Student> students = Database.getInstance().getStudents();
    MapRepository<Course> courses = Database.getInstance().getCourses();

    @RequestMapping("/courses")
    Object getStudentCourses(@PathVariable("sId") String sId) {
        Student student = students.findOne(sId);
        if (student == null) {
            return new Error(404, "Student not found");
        }
        return student.allGrades().keySet();
    }

    @RequestMapping(value = "/grades")
    Object getStudentGrades(@PathVariable("sId") String sId) {
        Student student = students.findOne(sId);
        if (student == null) {
            return new Error(404, "Student not found");
        }
        return student.allGrades();
    }

    @RequestMapping(value = "/grades/{cId}")
    Object getStudentGradesForCourse(@PathVariable("sId") String sId, @PathVariable("cId") String cId) {
        Student student = students.findOne(sId);
        if (student == null) {
            return new Error(404, "Student not found");
        }
        Course course = courses.findOne(cId);
        if (course == null) {
            return new Error(404, "Course not found");
        }
        if (!student.hasGradeForCourse(course)) {
            return new Error(404, "Course not found for this student");
        }
        return student.gradesForCourse(course);
    }

    @RequestMapping(value = "/grades/{cId}", method = RequestMethod.POST)
    Object postGradeForCourse(@PathVariable("sId") String sId, @PathVariable("cId") String cId, @RequestBody Grade grade) {
        Student student = students.findOne(sId);
        if (student == null) {
            return new Error(404, "Student not found");
        }
        Course course = courses.findOne(cId);
        if (course == null) {
            return new Error(404, "Course not found");
        }
        if (!student.hasGradeForCourse(course)) {
            student.allGrades().put(course, new HashMap<String, Grade>());
        }
        // TODO check & sanitize input
        student.gradesForCourse(course).put(grade.getEval(), grade);
        return grade;
    }
}
