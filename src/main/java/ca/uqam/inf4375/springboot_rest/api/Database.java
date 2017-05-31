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
package ca.uqam.inf4375.springboot_rest.api;

import ca.uqam.inf4375.springboot_rest.model.Course;
import ca.uqam.inf4375.springboot_rest.model.MapRepository;
import ca.uqam.inf4375.springboot_rest.model.Student;

/**
 * A singleton database
 */
public class Database {

    private static Database instance = null;

    public static Database getInstance() {
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return Database.instance;
    }

    private Database() {
    }

    MapRepository<Student> students = new MapRepository<>();
    MapRepository<Course> courses = new MapRepository<>();

    public MapRepository<Student> getStudents() {
        return students;
    }

    public MapRepository<Course> getCourses() {
        return courses;
    }

}
