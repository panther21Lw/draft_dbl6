package com.db_labs.lab_6.services.user;

import com.db_labs.lab_6.dto.UserDto;
import com.db_labs.lab_6.entity.Request;
import com.db_labs.lab_6.entity.User;
import com.db_labs.lab_6.exception.UserNotFoundException;
import com.db_labs.lab_6.mapper.RequestMapper;
import com.db_labs.lab_6.mapper.UserMapper;
import com.db_labs.lab_6.repository.RequestRepository;
import com.db_labs.lab_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final UserMapper userMapper;
    private final RequestMapper requestMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = UserMapper.toEntity(dto, requestRepository);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setFirstName(dto.firstName());
        existingUser.setLastName(dto.lastName());
        existingUser.setEmail(dto.email());
        existingUser.setAge(dto.age());
        existingUser.setPassword(dto.password());
        existingUser.setPhoneNumber(dto.phoneNumber());
        existingUser.setRoleId(dto.roleId());

        existingUser.getRequests().clear();

        if (dto.requestsIds() != null && !dto.requestsIds().isEmpty()) {
            List<Request> requests = requestRepository.findAllById(dto.requestsIds());
            requests.forEach(request -> request.setUser(existingUser));
            existingUser.getRequests().addAll(requests);
        }

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return true;
    }
}
