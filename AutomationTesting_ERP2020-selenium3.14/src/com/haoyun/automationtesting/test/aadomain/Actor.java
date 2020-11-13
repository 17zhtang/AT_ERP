package com.haoyun.automationtesting.test.aadomain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor {

	@Id
	@Column(name = "actor_id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(updatable = false, name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(updatable = false, name = "last_name", nullable = false, length = 45)
	private String lastName;

	@Column(updatable = false, name = "last_update", nullable = false, length = 45)
	private Date lastUpdate;

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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
