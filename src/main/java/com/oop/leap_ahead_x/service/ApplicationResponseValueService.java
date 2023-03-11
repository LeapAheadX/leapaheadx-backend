package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.Application;
import com.oop.leap_ahead_x.domain.ApplicationResponseValue;
import com.oop.leap_ahead_x.domain.InputComponent;
import com.oop.leap_ahead_x.domain.SubformCanvas;
import com.oop.leap_ahead_x.dto.ApplicationResponseValueDTO;
import com.oop.leap_ahead_x.repos.ApplicationRepository;
import com.oop.leap_ahead_x.repos.ApplicationResponseValueRepository;
import com.oop.leap_ahead_x.repos.InputComponentRepository;
import com.oop.leap_ahead_x.repos.SubformCanvasRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ApplicationResponseValueService {

    private final ApplicationResponseValueRepository applicationResponseValueRepository;
    private final ApplicationRepository applicationRepository;
    private final SubformCanvasRepository subformCanvasRepository;
    private final InputComponentRepository inputComponentRepository;

    public ApplicationResponseValueService(
            final ApplicationResponseValueRepository applicationResponseValueRepository,
            final ApplicationRepository applicationRepository,
            final SubformCanvasRepository subformCanvasRepository,
            final InputComponentRepository inputComponentRepository) {
        this.applicationResponseValueRepository = applicationResponseValueRepository;
        this.applicationRepository = applicationRepository;
        this.subformCanvasRepository = subformCanvasRepository;
        this.inputComponentRepository = inputComponentRepository;
    }

    public List<ApplicationResponseValueDTO> findAll() {
        final List<ApplicationResponseValue> applicationResponseValues = applicationResponseValueRepository.findAll(Sort.by("responseId"));
        return applicationResponseValues.stream()
                .map((applicationResponseValue) -> mapToDTO(applicationResponseValue, new ApplicationResponseValueDTO()))
                .toList();
    }

    public ApplicationResponseValueDTO get(final Integer responseId) {
        return applicationResponseValueRepository.findById(responseId)
                .map(applicationResponseValue -> mapToDTO(applicationResponseValue, new ApplicationResponseValueDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ApplicationResponseValueDTO applicationResponseValueDTO) {
        final ApplicationResponseValue applicationResponseValue = new ApplicationResponseValue();
        mapToEntity(applicationResponseValueDTO, applicationResponseValue);
        return applicationResponseValueRepository.save(applicationResponseValue).getResponseId();
    }

    public void update(final Integer responseId,
            final ApplicationResponseValueDTO applicationResponseValueDTO) {
        final ApplicationResponseValue applicationResponseValue = applicationResponseValueRepository.findById(responseId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(applicationResponseValueDTO, applicationResponseValue);
        applicationResponseValueRepository.save(applicationResponseValue);
    }

    public void delete(final Integer responseId) {
        applicationResponseValueRepository.deleteById(responseId);
    }

    private ApplicationResponseValueDTO mapToDTO(
            final ApplicationResponseValue applicationResponseValue,
            final ApplicationResponseValueDTO applicationResponseValueDTO) {
        applicationResponseValueDTO.setResponseId(applicationResponseValue.getResponseId());
        applicationResponseValueDTO.setValue(applicationResponseValue.getValue());
        applicationResponseValueDTO.setApplicationUuid(applicationResponseValue.getApplicationUuid() == null ? null : applicationResponseValue.getApplicationUuid().getApplicationUuid());
        applicationResponseValueDTO.setCanvasUuid(applicationResponseValue.getCanvasUuid() == null ? null : applicationResponseValue.getCanvasUuid().getCanvasUuid());
        applicationResponseValueDTO.setComponentUuid(applicationResponseValue.getComponentUuid() == null ? null : applicationResponseValue.getComponentUuid().getComponentUuid());
        return applicationResponseValueDTO;
    }

    private ApplicationResponseValue mapToEntity(
            final ApplicationResponseValueDTO applicationResponseValueDTO,
            final ApplicationResponseValue applicationResponseValue) {
        applicationResponseValue.setValue(applicationResponseValueDTO.getValue());
        final Application applicationUuid = applicationResponseValueDTO.getApplicationUuid() == null ? null : applicationRepository.findById(applicationResponseValueDTO.getApplicationUuid())
                .orElseThrow(() -> new NotFoundException("applicationUuid not found"));
        applicationResponseValue.setApplicationUuid(applicationUuid);
        final SubformCanvas canvasUuid = applicationResponseValueDTO.getCanvasUuid() == null ? null : subformCanvasRepository.findById(applicationResponseValueDTO.getCanvasUuid())
                .orElseThrow(() -> new NotFoundException("canvasUuid not found"));
        applicationResponseValue.setCanvasUuid(canvasUuid);
        final InputComponent componentUuid = applicationResponseValueDTO.getComponentUuid() == null ? null : inputComponentRepository.findById(applicationResponseValueDTO.getComponentUuid())
                .orElseThrow(() -> new NotFoundException("componentUuid not found"));
        applicationResponseValue.setComponentUuid(componentUuid);
        return applicationResponseValue;
    }

}
