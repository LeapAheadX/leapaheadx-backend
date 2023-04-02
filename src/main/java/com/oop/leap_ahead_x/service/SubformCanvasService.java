package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.Admin;
import com.oop.leap_ahead_x.domain.Application;
import com.oop.leap_ahead_x.domain.SubformCanvas;
import com.oop.leap_ahead_x.dto.SubformCanvasDTO;
import com.oop.leap_ahead_x.repos.AdminRepository;
import com.oop.leap_ahead_x.repos.SubformCanvasRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SubformCanvasService {

    private final SubformCanvasRepository subformCanvasRepository;
    private final AdminRepository adminRepository;

    public SubformCanvasService(final SubformCanvasRepository subformCanvasRepository,
            final AdminRepository adminRepository) {
        this.subformCanvasRepository = subformCanvasRepository;
        this.adminRepository = adminRepository;
    }

    public List<SubformCanvasDTO> findAll() {
        final List<SubformCanvas> subformCanvass = subformCanvasRepository.findAll(Sort.by("canvasUuid"));
        return subformCanvass.stream()
                .map((subformCanvas) -> mapToDTO(subformCanvas, new SubformCanvasDTO()))
                .toList();
    }

    public SubformCanvasDTO get(final UUID canvasUuid) {
        return subformCanvasRepository.findById(canvasUuid)
                .map(subformCanvas -> mapToDTO(subformCanvas, new SubformCanvasDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final SubformCanvasDTO subformCanvasDTO) {
        final SubformCanvas subformCanvas = new SubformCanvas();
        mapToEntity(subformCanvasDTO, subformCanvas);
        return subformCanvasRepository.save(subformCanvas).getCanvasUuid();
    }

    public void update(final UUID canvasUuid, final SubformCanvasDTO subformCanvasDTO) {
        final SubformCanvas subformCanvas = subformCanvasRepository.findById(canvasUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(subformCanvasDTO, subformCanvas);
        subformCanvasRepository.save(subformCanvas);
    }

    public void delete(final UUID canvasUuid) {
        subformCanvasRepository.deleteById(canvasUuid);
    }

    private SubformCanvasDTO mapToDTO(final SubformCanvas subformCanvas,
            final SubformCanvasDTO subformCanvasDTO) {
        subformCanvasDTO.setCanvasUuid(subformCanvas.getCanvasUuid());
        subformCanvasDTO.setName(subformCanvas.getName());
        subformCanvasDTO.setDescription(subformCanvas.getDescription());
        subformCanvasDTO.setCreatedBy(subformCanvas.getCreatedBy() == null ? null : subformCanvas.getCreatedBy().getAdminUuid());
        subformCanvasDTO.setDateCreated(subformCanvas.getDateCreated());
        subformCanvasDTO.setDisabled(subformCanvas.isDisabled());
        return subformCanvasDTO;
    }

    @Transactional
    public void archive(UUID cId){
        SubformCanvas subformCanvas = subformCanvasRepository.getReferenceById(cId);
        subformCanvas.setDisabled(true);
        subformCanvasRepository.save(subformCanvas);
    }

    private SubformCanvas mapToEntity(final SubformCanvasDTO subformCanvasDTO,
            final SubformCanvas subformCanvas) {
        subformCanvas.setName(subformCanvasDTO.getName());
        subformCanvas.setDescription(subformCanvasDTO.getDescription());
        final Admin createdBy = subformCanvasDTO.getCreatedBy() == null ? null : adminRepository.findById(subformCanvasDTO.getCreatedBy())
                .orElseThrow(() -> new NotFoundException("createdBy not found"));
        subformCanvas.setCreatedBy(createdBy);
        subformCanvas.setDateCreated(subformCanvasDTO.getDateCreated());
        subformCanvas.setDisabled(subformCanvasDTO.isDisabled());
        return subformCanvas;
    }

}
