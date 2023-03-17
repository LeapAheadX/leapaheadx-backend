package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.AssociatedSubform;
import com.oop.leap_ahead_x.domain.FormStep;

import com.oop.leap_ahead_x.domain.SubformCanvas;
import com.oop.leap_ahead_x.dto.AssociatedSubformDTO;

import com.oop.leap_ahead_x.repos.AssociatedSubformRepository;
import com.oop.leap_ahead_x.repos.FormStepRepository;
import com.oop.leap_ahead_x.repos.SubformCanvasRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssociatedSubformService {

    private final AssociatedSubformRepository associatedSubformRepository;
    private final FormStepRepository formStepRepository;
    private final SubformCanvasRepository subformCanvasRepository;

    public AssociatedSubformService(AssociatedSubformRepository associatedSubformRepository, FormStepRepository formStepRepository, SubformCanvasRepository subformCanvasRepository) {
        this.associatedSubformRepository = associatedSubformRepository;
        this.formStepRepository = formStepRepository;
        this.subformCanvasRepository = subformCanvasRepository;
    }

    public List<AssociatedSubformDTO> findAll() {
        final List<AssociatedSubform> associatedSubforms = associatedSubformRepository.findAll();
        return associatedSubforms.stream()
                .map((associatedSubform) -> mapToDTO(associatedSubform, new AssociatedSubformDTO()))
                .toList();
    }

    private AssociatedSubformDTO mapToDTO(final AssociatedSubform associatedSubform, final AssociatedSubformDTO associatedSubformDTO) {

        associatedSubformDTO.setStepUuid(associatedSubform.getStepUuid().getStepUuid());
        associatedSubformDTO.setCanvasUuid(associatedSubform.getCanvasUuid().getCanvasUuid());
        associatedSubformDTO.setPosition(associatedSubform.getPosition());


        return associatedSubformDTO;
    }

    public Integer create(final AssociatedSubformDTO associatedSubformDTO) {
        final AssociatedSubform associatedSubform = new AssociatedSubform();
        mapToEntity(associatedSubformDTO, associatedSubform);
        return associatedSubformRepository.save(associatedSubform).getAssociatedId();
    }

    private AssociatedSubform mapToEntity(final AssociatedSubformDTO associatedSubformDTO,
                                       final AssociatedSubform associatedSubform) {
        FormStep formStep = formStepRepository.findByStepUuid(associatedSubformDTO.getStepUuid());
        associatedSubform.setStepUuid(formStep);
        SubformCanvas subformCanvas = subformCanvasRepository.findByCanvasUuid(associatedSubformDTO.getCanvasUuid());
        associatedSubform.setCanvasUuid(subformCanvas);
        associatedSubform.setPosition(associatedSubformDTO.getPosition());
        return associatedSubform;
    }
}
