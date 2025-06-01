package com.db_labs.lab_6.services.request;

import com.db_labs.lab_6.dto.RequestDto;
import com.db_labs.lab_6.entity.Request;
import com.db_labs.lab_6.entity.User;
import com.db_labs.lab_6.exception.RequestNotFoundException;
import com.db_labs.lab_6.exception.UserNotFoundException;
import com.db_labs.lab_6.mapper.RequestMapper;
import com.db_labs.lab_6.repository.RequestRepository;
import com.db_labs.lab_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;

    @Override
    public List<RequestDto> findAll() {

        List<Request> requests = requestRepository.findAll();
        return requests.stream()
                .map(RequestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto findById(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
        return RequestMapper.toDto(request);
    }

    @Override
    public RequestDto create(RequestDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(dto.userId()));
        Request request = RequestMapper.toEntity(dto, userRepository);
        Request savedRequest = requestRepository.save(request);
        return RequestMapper.toDto(savedRequest);
    }

    @Override
    public RequestDto update(Long id, RequestDto dto) {
        Request existingRequest = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(dto.userId()));

        existingRequest.setTime(dto.time());
        existingRequest.setUrl(dto.url());
        existingRequest.setUser(user);

        Request updated = requestRepository.save(existingRequest);
        return RequestMapper.toDto(updated);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!requestRepository.existsById(id)){
            throw new RequestNotFoundException(id);
        }
        requestRepository.deleteById(id);
        return true;
    }
}
