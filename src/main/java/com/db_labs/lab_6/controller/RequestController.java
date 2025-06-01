package com.db_labs.lab_6.controller;

import com.db_labs.lab_6.dto.RequestDto;
import com.db_labs.lab_6.services.request.RequestService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    private RequestService requestService;

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests() {
        List<RequestDto> requests = requestService.findAll();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestDto> getRequestById(@PathVariable("id") Long id) {
        RequestDto requestDto = requestService.findById(id);
        return ResponseEntity.ok(requestDto);
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto) {
        RequestDto savedRequestDto = requestService.create(requestDto);
        return ResponseEntity.ok(savedRequestDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<RequestDto> updateRequest(@PathVariable("id") Long id, @RequestBody RequestDto requestDto) {
        RequestDto updatedRequestDto = requestService.update(id, requestDto);
        return ResponseEntity.ok(updatedRequestDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        Boolean status = requestService.deleteById(id);
        return ResponseEntity.ok("Request with ID: " + id + " DELETED successfully. Confirm: " + status);
    }
}
