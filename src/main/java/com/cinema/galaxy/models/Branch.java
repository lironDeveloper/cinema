package com.cinema.galaxy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "שם סניף נדרש.")
    @Size(min = 2, max = 50, message = "שם סניף חייב להיות באורך של 2-50 תווים.")
    private String name;
    @NotBlank(message = "שם עיר של סניף נדרש.")
    @Size(min = 2, max = 50, message = "שם עיר חייב להיות באורך של 2-50 תווים.")
    private String city;

    @NotBlank(message = "כתובת סניף נדרשת.")
    @Size(min = 2, max = 150, message = "כתובת חייבת להיות באורך של 2-150 תווים.")
    private String address;

    @NotBlank(message = "איש קשר של הסניף נדרש.")
    @Size(min = 2, max = 50, message = "שם איש קשר חייב להיות באורך של 2-50 תווים.")
    private String contact_info;

    public Branch(String name, String city, String address, String contact_info) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.contact_info = contact_info;
    }

    public Branch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", contact_info='" + contact_info + '\'' +
                '}';
    }
}
