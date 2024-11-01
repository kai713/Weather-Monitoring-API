package ru.kairgaliyev.springCourse.Project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Sensor")
public class Sensor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //    Уже как внешний ключ
    @Column(name = "name")
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3, max = 30, message = "must be between 3 and 30")
    private String name;


//    needn't
//    @OneToMany(mappedBy = "sensor")
//    List<Measurements> measurementsList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
