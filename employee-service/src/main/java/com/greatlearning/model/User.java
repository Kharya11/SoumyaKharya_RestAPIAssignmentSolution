package com.greatlearning.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;

	@ManyToMany(mappedBy = "users",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	public void addRole(Role role){
        if(this.roles == null){
            this.roles = new HashSet<>();
        }
        //set the bidirectional mapping
        this.roles.add(role);
        role.getUsers().add(this);
    }

}
