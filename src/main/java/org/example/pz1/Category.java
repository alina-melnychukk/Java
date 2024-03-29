package org.example.pz1;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Category {

    private  int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
