package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.InputComponent;
import com.oop.leap_ahead_x.domain.SubformCanvas;
import com.oop.leap_ahead_x.dto.InputComponentDTO;
import com.oop.leap_ahead_x.repos.InputComponentRepository;
import com.oop.leap_ahead_x.repos.SubformCanvasRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InputComponentService {

    private final InputComponentRepository inputComponentRepository;
    private final SubformCanvasRepository subformCanvasRepository;

    public InputComponentService(final InputComponentRepository inputComponentRepository,
            final SubformCanvasRepository subformCanvasRepository) {
        this.inputComponentRepository = inputComponentRepository;
        this.subformCanvasRepository = subformCanvasRepository;
    }

    public List<InputComponentDTO> findAll() {
        final List<InputComponent> inputComponents = inputComponentRepository.findAll(Sort.by("componentUuid"));
        return inputComponents.stream()
                .map((inputComponent) -> mapToDTO(inputComponent, new InputComponentDTO()))
                .toList();
    }

    public InputComponentDTO get(final UUID componentUuid) {
        return inputComponentRepository.findById(componentUuid)
                .map(inputComponent -> mapToDTO(inputComponent, new InputComponentDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final InputComponentDTO inputComponentDTO) {
        final InputComponent inputComponent = new InputComponent();
        mapToEntity(inputComponentDTO, inputComponent);
        return inputComponentRepository.save(inputComponent).getComponentUuid();
    }

    public void update(final UUID componentUuid, final InputComponentDTO inputComponentDTO) {
        final InputComponent inputComponent = inputComponentRepository.findById(componentUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(inputComponentDTO, inputComponent);
        inputComponentRepository.save(inputComponent);
    }

    public void delete(final UUID componentUuid) {
        inputComponentRepository.deleteById(componentUuid);
    }

    private InputComponentDTO mapToDTO(final InputComponent inputComponent,
            final InputComponentDTO inputComponentDTO) {
        inputComponentDTO.setComponentUuid(inputComponent.getComponentUuid());
        inputComponentDTO.setQuestion(inputComponent.getQuestion());
        inputComponentDTO.setType(inputComponent.getType());
        inputComponentDTO.setIsRequired(inputComponent.getIsRequired());
        inputComponentDTO.setOrderNo(inputComponent.getOrderNo());
        inputComponentDTO.setParentCanvas(inputComponent.getParentCanvas() == null ? null : inputComponent.getParentCanvas().getCanvasUuid());
        return inputComponentDTO;
    }

    private InputComponent mapToEntity(final InputComponentDTO inputComponentDTO,
            final InputComponent inputComponent) {
        inputComponent.setQuestion(inputComponentDTO.getQuestion());
        inputComponent.setType(inputComponentDTO.getType());
        inputComponent.setIsRequired(inputComponentDTO.getIsRequired());
        inputComponent.setOrderNo(inputComponentDTO.getOrderNo());
        final SubformCanvas parentCanvas = inputComponentDTO.getParentCanvas() == null ? null : subformCanvasRepository.findById(inputComponentDTO.getParentCanvas())
                .orElseThrow(() -> new NotFoundException("parentCanvas not found"));
        inputComponent.setParentCanvas(parentCanvas);
        return inputComponent;
    }

}
