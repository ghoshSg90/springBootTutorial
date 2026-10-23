package com.ecom.app.service;

import com.ecom.app.dto.AddressDTO;
import com.ecom.app.dto.UserRequest;
import com.ecom.app.dto.UserResponse;
import com.ecom.app.model.Address;
import com.ecom.app.model.User;
import com.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUsers(){
        //return userRepository.findAll();
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest){
        User newUser = new User();
        updateUserFromRequest(newUser,userRequest);
        userRepository.save(newUser);
    }


    public Optional<UserResponse> getUserById(Long id) {
//        return userList.stream().
//                filter(user -> user.getId().equals(id))
//                .findFirst();
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    public boolean updateUserById(Long id, UserRequest updatedUserRequest){
//        return userList.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .map(existingUser -> {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
//                    return true;
//                }).orElse(false);
        return userRepository.findById(id)
                .map(existingUser -> {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
                    updateUserFromRequest(existingUser, updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();

        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNo(user.getPhoneNo());
        response.setRole(user.getRole());

        if(user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());

            response.setAddressDTO(addressDTO);
        }

        return response;
    }

    private void updateUserFromRequest(User newUser, UserRequest userRequest) {
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPhoneNo(userRequest.getPhoneNo());

        if(userRequest.getAddress() != null){
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getState());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());

            newUser.setAddress(address);
        }
    }
}
