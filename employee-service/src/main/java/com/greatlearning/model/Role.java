package com.greatlearning.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int roleId;
	
	@Column(name="name")
	private String roleName;
	
	@ManyToMany
	@JoinTable( 
			name="users_roles",
			joinColumns = @JoinColumn(name="role_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	 @JsonIgnore
	    private Set<User> users;

	    public Set<User> getUsers(){
	        if (this.users == null){
	            this.users = new HashSet<>();
	        }
	        return this.users;
	    }

	
	
}

