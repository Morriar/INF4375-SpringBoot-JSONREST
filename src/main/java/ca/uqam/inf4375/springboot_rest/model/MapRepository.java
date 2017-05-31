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

/*
 * A fake repository to simulate our database based on a HashMap
 */
public class MapRepository<T extends RepoObject> {

    protected Map<String, T> map = new HashMap<>();

    public T save(T t) {
        if (t.getId() == null) {
            t.setId(genId(t));
        }
        map.put(t.getId(), t);
        return t;
    }

    public T findOne(String id) {
        return map.get(id);
    }

    public boolean exists(String id) {
        return map.containsKey(id);
    }

    public Iterable<T> findAll() {
        return map.values();
    }

    public long count() {
        return map.size();
    }

    public void delete(String id) {
        map.remove(id);
    }

    public void deleteAll() {
        map.clear();
    }

    private String genId(T t) {
        return System.currentTimeMillis() + "." + t.hashCode();
    }

}
