package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.AssociatedSubform;
import com.oop.leap_ahead_x.domain.FormStep;
import com.oop.leap_ahead_x.domain.FormWorkflow;
import com.oop.leap_ahead_x.domain.SubformCanvas;

import com.oop.leap_ahead_x.dto.FormStepDTO;

import com.oop.leap_ahead_x.repos.AssociatedSubformRepository;
import com.oop.leap_ahead_x.repos.FormStepRepository;
import com.oop.leap_ahead_x.repos.FormWorkflowRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class FormStepService {

    private final FormStepRepository formStepRepository;
    private final FormWorkflowRepository formWorkflowRepository;

    private final AssociatedSubformRepository associatedSubformRepository;

    public FormStepService(final FormStepRepository formStepRepository,
                           final FormWorkflowRepository formWorkflowRepository, AssociatedSubformRepository associatedSubformRepository) {
        this.formStepRepository = formStepRepository;
        this.formWorkflowRepository = formWorkflowRepository;
        this.associatedSubformRepository = associatedSubformRepository;
    }

    public List<FormStepDTO> findAll() {
        final List<FormStep> formSteps = formStepRepository.findAll(Sort.by("stepUuid"));
        return formSteps.stream()
                .map((formStep) -> mapToDTO(formStep, new FormStepDTO()))
                .toList();
    }

    public FormStepDTO get(final UUID stepUuid) {
        return formStepRepository.findById(stepUuid)
                .map(formStep -> mapToDTO(formStep, new FormStepDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final FormStepDTO formStepDTO) {
        final FormStep formStep = new FormStep();
        mapToEntity(formStepDTO, formStep);
        return formStepRepository.save(formStep).getStepUuid();
    }

    public void update(final UUID stepUuid, final FormStepDTO formStepDTO) {
        final FormStep formStep = formStepRepository.findById(stepUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(formStepDTO, formStep);
        formStepRepository.save(formStep);
    }

    public void delete(final UUID stepUuid) {
        formStepRepository.deleteById(stepUuid);
    }

    private FormStepDTO mapToDTO(final FormStep formStep, final FormStepDTO formStepDTO) {
        UUID stepUuid=formStep.getStepUuid();
        formStepDTO.setStepUuid(stepUuid);
        formStepDTO.setAssigneeType(formStep.getAssigneeType());
        formStepDTO.setOrderNo(formStep.getOrderNo());
        formStepDTO.setAction(formStep.getAction());
        formStepDTO.setParentForm(formStep.getParentForm() == null ? null : formStep.getParentForm().getFormUuid());


        List <SubformCanvas> afs = associatedSubformRepository.findByStepUuid(formStep)
                .stream()
                .map(AssociatedSubform::getCanvasUuid)
                .collect(Collectors.toList());
        List <UUID> uuid = afs
                .stream()
                .map(SubformCanvas::getCanvasUuid)
                .collect(Collectors.toList());
        formStepDTO.setAssociatedSubformSubformCanvass(uuid);

        return formStepDTO;
    }

    private FormStep mapToEntity(final FormStepDTO formStepDTO, final FormStep formStep) {
        formStep.setAssigneeType(formStepDTO.getAssigneeType());
        formStep.setOrderNo(formStepDTO.getOrderNo());
        formStep.setAction(formStepDTO.getAction());
        final FormWorkflow parentForm = formStepDTO.getParentForm() == null ? null : formWorkflowRepository.findById(formStepDTO.getParentForm())
                .orElseThrow(() -> new NotFoundException("parentForm not found"));
        formStep.setParentForm(parentForm);
        return formStep;
    }

    @Transactional
    public ResponseEntity<String> getAllSubstepDetails(){
        JSONArray outerArray = new JSONArray();
        List<FormStep> formSteps = formStepRepository.findAll();

        for (FormStep formStep: formSteps){
            JSONObject formObjects = new JSONObject();
            formObjects.put("stepUuid", formStep.getStepUuid());
            formObjects.put("assigneeType", formStep.getAssigneeType());
            formObjects.put("action", formStep.getAction());
            formObjects.put("orderNo", formStep.getOrderNo());
            List<AssociatedSubform> associatedSubforms = associatedSubformRepository.findByStepUuid(formStep);

            JSONArray associatedSubformsArray = new JSONArray();
            for (AssociatedSubform associatedSubform :associatedSubforms){

                JSONObject associatedSubformsObjects = new JSONObject();
                associatedSubformsObjects.put("name",associatedSubform.getCanvasUuid().getName());
                associatedSubformsObjects.put("position",associatedSubform.getPosition());
                associatedSubformsArray.put(associatedSubformsObjects);
            }

            formObjects.put("associatedSubform", associatedSubformsArray);
            outerArray.put(formObjects);
        }


        String jsonString = outerArray.toString();


        return ResponseEntity.ok(jsonString);
    }

    @Transactional
    public ResponseEntity<String> getSubstepDetails(final UUID step){
        JSONArray outerArray = new JSONArray();
        FormStep formStep = formStepRepository.getReferenceById(step);
        JSONObject formObjects = new JSONObject();
        formObjects.put("stepUuid", formStep.getStepUuid());
        formObjects.put("assigneeType", formStep.getAssigneeType());
        formObjects.put("action", formStep.getAction());
        formObjects.put("orderNo", formStep.getOrderNo());
        List<AssociatedSubform> associatedSubforms = associatedSubformRepository.findByStepUuid(formStep);


        JSONArray associatedSubformsArray = new JSONArray();

        for (AssociatedSubform associatedSubform :associatedSubforms){
            JSONObject associatedSubformsObjects = new JSONObject();
            associatedSubformsObjects.put("name",associatedSubform.getCanvasUuid().getName());
            associatedSubformsObjects.put("position",associatedSubform.getPosition());
            associatedSubformsArray.put(associatedSubformsObjects);
        }

        formObjects.put("associatedSubform", associatedSubformsArray);
        outerArray.put(formObjects);
        String jsonString = outerArray.toString();


        return ResponseEntity.ok(jsonString);
    }

    public ResponseEntity<String> getAllstepDetailsByParentform(final UUID parentform_Uuid){
        JSONArray outerArray = new JSONArray();
        FormWorkflow form = formWorkflowRepository.getReferenceById(parentform_Uuid);
        List<FormStep> formSteps = formStepRepository.findByParentForm(form);
        JSONObject formObjects = new JSONObject();
        formObjects.put("workflowUuid", parentform_Uuid);
        JSONArray formStepArray = new JSONArray();
        for (FormStep formStep: formSteps) {
            JSONObject formStepObjects = new JSONObject();
            formStepObjects.put("stepUuid", formStep.getStepUuid());
            formStepObjects.put("assigneeType", formStep.getAssigneeType());
            formStepObjects.put("action", formStep.getAction());
            formStepObjects.put("orderNo", formStep.getOrderNo());
            List<AssociatedSubform> associatedSubforms = associatedSubformRepository.findByStepUuid(formStep);
            JSONArray associatedSubformsArray = new JSONArray();
            for (AssociatedSubform associatedSubform : associatedSubforms) {
                JSONObject associatedSubformsObjects = new JSONObject();
                associatedSubformsObjects.put("associatedSubformsId", associatedSubform.getAssociatedId());
                associatedSubformsObjects.put("name", associatedSubform.getCanvasUuid().getCanvasUuid());
                associatedSubformsObjects.put("position", associatedSubform.getPosition());
                associatedSubformsArray.put(associatedSubformsObjects);
            }
            formStepObjects.put("associatedSubform", associatedSubformsArray);
            formStepArray.put(formStepObjects);
        }
        formObjects.put("formSteps", formStepArray);
        outerArray.put(formObjects);
        String jsonString = outerArray.toString();
        return ResponseEntity.ok(jsonString);
    }


}
