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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_property", referencedColumnName = "id")
	private Property property;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check-in")
	private LocalDate checkIn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "check-out")
	private LocalDate checkOut;
	
	@Column(name = "n_guest", nullable = false)
	private int nGuest;
	
	@Column(name = "total_price", nullable = false)
	private double totalPrice;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_boo", nullable = false)
	private LocalDateTime createdBoo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_boo", nullable = false)
	private LocalDateTime updatedBoo;

	
}
