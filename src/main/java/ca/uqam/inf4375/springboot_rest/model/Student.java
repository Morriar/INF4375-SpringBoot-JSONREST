/*
 * Copyright 2017 Alexandre Terrasa <alexandre@moz-code.org>.
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
package ca.uqam.inf4375.springboot_rest.model;

import java.util.HashMap;
import java.util.Map;

public class Student implements RepoObject {

    // The student code like ABCD123456789
    String id;

    String firstname;

    String lastname;

    Map<Course, Map<String, Grade>> grades = new HashMap<>();

    public boolean hasGradeForCourse(Course course) {
        return grades.containsKey(course);
    }
    
    public Map<Course, Map<String, Grade>> allGrades() {
        return grades;
    }

    public Map<String, Grade> gradesForCourse(Course course) {
        return grades.get(course);
    }

    public double meanForCourse(Course course) {
        Map<String, Grade> grades = gradesForCourse(course);
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Grade grade : grades.values()) {
            sum += grade.getGrade();
        }
        return sum / grades.size();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
