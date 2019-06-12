package org.fasttrackit.onlineshopapi.dto;

import javax.validation.constraints.NotNull;

public class CreateCustomerRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String eMail;

    @NotNull
    private Long phoneNumber;

    @NotNull
    private int age;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", age=" + age +
                '}';
    }
}

