package com.gabrieldeoliveira.cursospring.dto;

import java.io.Serializable;

import com.gabrieldeoliveira.cursospring.domain.Address;
import com.gabrieldeoliveira.cursospring.domain.City;
import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.domain.enums.ClientType;

public class NewClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer typeCode;

    private String place;
    private String number;
    private String complement;
    private String district;
    private String cep;

    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public NewClientDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Client toObj() {
        Client client = new Client(null, name, email, cpfOrCnpj, ClientType.fromCode(typeCode));
        City city = new City(cityId, null, null);
        Address address = new Address(null, place, number, complement, district, cep, city, client);
        client.getAddresses().add(address);
        client.getPhones().add(phone1);

        if (wasInserted(phone2)) {
            client.getPhones().add(phone2);
        }
        if (wasInserted(phone3)) {
            client.getPhones().add(phone3);
        }
        return client;
    }

    private boolean wasInserted(String phone) {
        return phone != null && !phone.isBlank();
    }
}
