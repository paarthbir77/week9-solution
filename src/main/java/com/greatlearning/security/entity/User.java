package com.greatlearning.security.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	public User() {
		
	}
	public User(String email, String type, String password) {
		this.email = email;
		this.password = password;
		this.type = type;
	}
	public User(String email, String type) {
		this.email = email;
		this.type = type;
	}
	
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMAIL",unique=true, nullable = false)
    private String email;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Type")
    private String type;
    
    @Override
	public String toString() {
		return "USER: "  + ", Email = " + email + ", Type = "+ type;
	}
    
    public void setEmail(String email) {
    	this.email= email;
    }
    public void setPassword(String password) {
    	this.password= password;
    }
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getEmail() {
    	return this.email;
    }
    public String getPass() {
    	return this.password;
    }
    public String getType() {
    	return this.type;
    }

}
