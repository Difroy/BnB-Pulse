package bnb.pulse.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String surname;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column
	private String gender;

	@Column
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_pr", nullable = false, updatable = false)
	private LocalDateTime createdPr;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_pr", nullable = false)
    private LocalDateTime updatePr;
	
	@OneToOne(
			mappedBy = "profile",
			cascade = CascadeType.REFRESH
			)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreatedPr() {
		return createdPr;
	}

	public void setCreatedPr(LocalDateTime createdPr) {
		this.createdPr = createdPr;
	}

	public LocalDateTime getUpdatePr() {
		return updatePr;
	}

	public void setUpdatePr(LocalDateTime updatePr) {
		this.updatePr = updatePr;
	}
}
