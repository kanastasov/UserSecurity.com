//package com.personalPage.service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.personalPage.model.Role;
//import com.personalPage.model.User;
//import com.personalPage.modelDto.UserDTO;
//import com.personalPage.repository.UserDTORepository;
//import com.personalPage.repository.UserRepository;
//
//@Service
//public class UserDTOService {
//
//	
//	  @Autowired
//	    private UserDTORepository userRepository;
//
//	    @Autowired
//	    private BCryptPasswordEncoder passwordEncoder;
//
//	    public UserDTO findByEmail(String email) {
//	        return userRepository.findByEmail(email);
//	    }
//
//	    public UserDTO save(UserDTO registration) {
//	        UserDTO user = new UserDTO();
//	        user.setFirstName(registration.getFirstName());
//	        user.setLastName(registration.getLastName());
//	        user.setEmail(registration.getEmail());
//	        user.setPassword(passwordEncoder.encode(registration.getPassword()));
////	        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//	        return userRepository.save(user);
//	    }
//	    
//
//	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//	        UserDTO user = userRepository.findByEmail(email);
//	        if (user == null) {
//	            throw new UsernameNotFoundException("Invalid username or password.");
//	        }
//	        return new org.springframework.security.core.userdetails.User(user.getEmail(),
//	            user.getPassword(),
//	            mapRolesToAuthorities(user.getRoles())
//	            );
//	    }
//
//	    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection <Role> roles) {
//	        return roles.stream()
//	            .map(role -> new SimpleGrantedAuthority(role.getName()))
//	            .collect(Collectors.toList());
//	    }
//
//		public List<UserDTO> getAllUsers() {
//			return this.userRepository.findAll();
//		}
//
//		public Optional<UserDTO> getUserById(long id) {
//			return userRepository.findById(id);
//		
//		}
//
//		public void deleteUserById(long id) {
//			this.userRepository.deleteById(id);
//		}
//}
