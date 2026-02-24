package com.arquitecturaviva.actrices;

public class Actriz {
    private Integer id;
    private String name;
    private String description;
    private Integer age;
    private String gender;
    private String nationality;
    private Double height;
    private Boolean prize;
    private String imagen;

    public Actriz() {
    }

    public Actriz(Integer id, String name, String description, Integer age, String gender, String nationality, Double height, Boolean prize, String imagen) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
        this.height = height;
        this.prize = prize;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Boolean getPrize() {
        return prize;
    }

    public void setPrize(Boolean prize) {
        this.prize = prize;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
