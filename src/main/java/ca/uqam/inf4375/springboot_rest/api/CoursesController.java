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
import ca.uqam.inf4375.springboot_rest.model.MapRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    MapRepository<Course> courses = Database.getInstance().getCourses();

    @RequestMapping("")
    Iterable<Course> getCourses() {
        return courses.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    Course postCourse(@RequestBody Course course) {
        // TODO check & sanitize input
        return courses.save(course);
    }

    @RequestMapping(value = "/{cId}")
    Object getCourse(@PathVariable("cId") String cId) {
        Course res = courses.findOne(cId);
        if (res == null) {
            return new Error(404, "Course not found");
        }
        return res;
    }

    @RequestMapping(value = "/{cId}", method = RequestMethod.PUT)
    Object putCourse(@PathVariable("cId") String cId, @RequestBody Course edit) {
        Course res = courses.findOne(cId);
        if (res == null) {
            return new Error(404, "Course not found");
        }
        // TODO check & sanitize input
        res.setTitle(edit.getTitle());
        return courses.save(res);
    }

    @RequestMapping(value = "/{cId}", method = RequestMethod.DELETE)
    Object deleteCourse(@PathVariable("cId") String cId) {
        Course res = courses.findOne(cId);
        if (res == null) {
            return new Error(404, "Course not found");
        }
        courses.delete(cId);
        return res;
    }
}
