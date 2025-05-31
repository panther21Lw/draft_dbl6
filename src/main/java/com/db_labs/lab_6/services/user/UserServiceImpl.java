package com.db_labs.lab_6.services.user;

import com.db_labs.lab_6.dto.UserDto;
import com.db_labs.lab_6.entity.Request;
import com.db_labs.lab_6.entity.User;
import com.db_labs.lab_6.exception.UserNotFoundException;
import com.db_labs.lab_6.mapper.RequestMapper;
import com.db_labs.lab_6.mapper.UserMapper;
import com.db_labs.lab_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RequestMapper requestMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = userMapper.toUser(dto);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setFirstName(dto.getFirstName());
        existingUser.setLastName(dto.getLastName());
        existingUser.setEmail(dto.getEmail());
        existingUser.setAge(dto.getAge());
        existingUser.setPassword(dto.getPassword());
        existingUser.setPhoneNumber(dto.getPhoneNumber());
        existingUser.setRoleId(dto.getRoleId());

        existingUser.getRequests().clear();

        if (dto.getRequests() != null && !dto.getRequests().isEmpty()) {
            dto.getRequests().forEach(requestDto -> {
                Request request = requestMapper.toRequest(requestDto, existingUser);
                existingUser.getRequests().add(request);
            });
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toUserDto(updatedUser);
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
