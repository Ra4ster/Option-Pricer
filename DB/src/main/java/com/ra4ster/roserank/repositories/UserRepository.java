package com.ra4ster.roserank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ra4ster.roserank.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public Optional<User> findByClerkId(String name);
	
	public Optional<User> findByEmail(String email);
	
	boolean existsByClerkId(String clerkId);
}
