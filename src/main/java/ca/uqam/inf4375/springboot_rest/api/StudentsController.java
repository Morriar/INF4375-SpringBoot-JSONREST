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

import ca.uqam.inf4375.springboot_rest.model.MapRepository;
import ca.uqam.inf4375.springboot_rest.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentsController {

    MapRepository<Student> students = Database.getInstance().getStudents();

    @RequestMapping("")
    Iterable<Student> getStudents() {
        return students.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    Student postStudent(@RequestBody Student student) {
        // TODO check & sanitize input
        return students.save(student);
    }

    @RequestMapping(value = "/{sId}")
    Object getStudent(@PathVariable("sId") String sId) {
        Student student = students.findOne(sId);
        if (student == null) {
            return new Error(404, "Student not found");
        }
        return student;
    }

    @RequestMapping(value = "/{sId}", method = RequestMethod.PUT)
    Object putStudent(@PathVariable("sId") String sId, @RequestBody Student edit) {
        Student student = students.findOne(sId);
        if (student == null) {
            return new Error(404, "Student not found");
        }
        // TODO check & sanitize input
        student.setFirstname(edit.getFirstname());
        student.setLastname(edit.getLastname());
        return students.save(student);
    }

    @RequestMapping(value = "/{sId}", method = RequestMethod.DELETE)
    Object deleteStudent(@PathVariable("sId") String sId) {
        Student student = students.findOne(sId);
        if (student == null) {
            return new Error(404, "Student not found");
        }
        students.delete(sId);
        return student;
    }
}
