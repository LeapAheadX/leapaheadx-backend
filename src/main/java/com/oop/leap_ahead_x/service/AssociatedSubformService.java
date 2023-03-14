package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.AssociatedSubform;
import com.oop.leap_ahead_x.dto.AssociatedSubformDTO;
import com.oop.leap_ahead_x.repos.AssociatedSubformRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssociatedSubformService {

    private final AssociatedSubformRepository associatedSubformRepository;

    public AssociatedSubformService(AssociatedSubformRepository associatedSubformRepository) {
        this.associatedSubformRepository = associatedSubformRepository;
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
}
