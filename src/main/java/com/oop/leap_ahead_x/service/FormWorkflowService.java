package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.*;
import com.oop.leap_ahead_x.dto.FormWorkflowDTO;
import com.oop.leap_ahead_x.dto.FormWorkflowDTO_Post;
import com.oop.leap_ahead_x.repos.*;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class FormWorkflowService {

    private final FormWorkflowRepository formWorkflowRepository;
    private final AdminRepository adminRepository;
    private final SubformCanvasRepository subformCanvasRepository;

    private final InputComponentRepository inputComponentRepository;

    private final OptionsRepository optionsRepository;

    private final FormStepRepository formStepRepository;

    private final AssociatedSubformRepository associatedSubformRepository;

    public FormWorkflowService(final FormWorkflowRepository formWorkflowRepository,
                               final AdminRepository adminRepository, final OptionsRepository optionsRepository, final InputComponentRepository inputComponentRepository,
                               SubformCanvasRepository subformCanvasRepository, FormStepRepository formStepRepository, final AssociatedSubformRepository associatedSubformRepository) {
        this.formWorkflowRepository = formWorkflowRepository;
        this.adminRepository = adminRepository;
        this.subformCanvasRepository = subformCanvasRepository;
        this.inputComponentRepository = inputComponentRepository;
        this.optionsRepository = optionsRepository;
        this.formStepRepository = formStepRepository;
        this.associatedSubformRepository=associatedSubformRepository;
    }

    public List<FormWorkflowDTO> findAll() {
        final List<FormWorkflow> formWorkflows = formWorkflowRepository.findAll(Sort.by("formUuid"));
        return formWorkflows.stream()
                .map((formWorkflow) -> mapToDTO(formWorkflow, new FormWorkflowDTO()))
                .toList();
    }

    public FormWorkflowDTO get(final UUID formUuid) {
        return formWorkflowRepository.findById(formUuid)
                .map(formWorkflow -> mapToDTO(formWorkflow, new FormWorkflowDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final FormWorkflowDTO_Post formWorkflowDTO_post) {
        final FormWorkflow formWorkflow = new FormWorkflow();
        mapToEntity(formWorkflowDTO_post, formWorkflow);
        return formWorkflowRepository.save(formWorkflow).getFormUuid();
    }

    public void update(final UUID formUuid, final FormWorkflowDTO_Post formWorkflowDTO_post) {
        final FormWorkflow formWorkflow = formWorkflowRepository.findById(formUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(formWorkflowDTO_post, formWorkflow);
        formWorkflowRepository.save(formWorkflow);
    }

    public void delete(final UUID formUuid) {
        formWorkflowRepository.deleteById(formUuid);
    }

    private FormWorkflowDTO mapToDTO(final FormWorkflow formWorkflow,
                                     final FormWorkflowDTO formWorkflowDTO) {
        formWorkflowDTO.setFormUuid(formWorkflow.getFormUuid());
        formWorkflowDTO.setName(formWorkflow.getName());
        formWorkflowDTO.setDescription(formWorkflow.getDescription());
        formWorkflowDTO.setCreatedBy(formWorkflow.getCreatedBy() == null ? null : formWorkflow.getCreatedBy().getAdminUuid());
        OffsetDateTime dateTime = formWorkflow.getDateCreated();
        // create a formatter to format the date string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // format the date string
        String formattedDateTime = formatter.format(dateTime);
        formWorkflowDTO.setDateCreated(formattedDateTime);
        return formWorkflowDTO;
    }

    private FormWorkflow mapToEntity(final FormWorkflowDTO_Post formWorkflowDTO_post,
                                     final FormWorkflow formWorkflow) {
        formWorkflow.setName(formWorkflowDTO_post.getName());
        formWorkflow.setDescription(formWorkflowDTO_post.getDescription());
        final Admin createdBy = formWorkflowDTO_post.getCreatedBy() == null ? null : adminRepository.findById(formWorkflowDTO_post.getCreatedBy())
                .orElseThrow(() -> new NotFoundException("createdBy not found"));
        formWorkflow.setCreatedBy(createdBy);
        return formWorkflow;
    }

    public boolean nameExists(final String name) {
        return formWorkflowRepository.existsByNameIgnoreCase(name);
    }

    @Transactional
    public ResponseEntity<String> getEmptyForms() {
        //get full form
        JSONArray outerArray = new JSONArray();
        List<FormWorkflow> forms = formWorkflowRepository.findAll();
        for (FormWorkflow form : forms) {
            JSONObject formObjects = new JSONObject();
            FormStep stepIdApprover = formStepRepository.findByParentFormAndAction(form, "Check and Approve");
            List<AssociatedSubform> subforms = associatedSubformRepository.findCanvasByStep(stepIdApprover);
            formObjects.put("formId", form.getFormUuid());
            formObjects.put("formName", form.getName());
            JSONArray canvaArrays = new JSONArray();
            for (AssociatedSubform subform : subforms) {
                JSONObject canvaObjects = new JSONObject();
                UUID canvasId = subform.getCanvasUuid().getCanvasUuid();
                SubformCanvas canva = subformCanvasRepository.getReferenceById(canvasId);
                String canvasName = canva.getName();
                JSONArray canvaArray = new JSONArray();
                final List<InputComponent> components = inputComponentRepository.findByParentCanvas(canva);
                for (InputComponent component : components) {
                    JSONObject individualComponentObject = new JSONObject();
                    int componentOrderNo = component.getOrderNo();
                    String question = component.getQuestion();
                    String type = component.getType();
                    UUID cId = component.getComponentUuid();
                    individualComponentObject.put("orderNo", componentOrderNo);
                    individualComponentObject.put("question", question);
                    individualComponentObject.put("type", type);
                    individualComponentObject.put("componentId", cId);
                    List<Options> options = optionsRepository.findByParentInputComponent(cId);
                    JSONArray optionArray = new JSONArray();
                    for (Options option : options) {
                        String prompt = option.getOptionPrompt();
                        optionArray.put(prompt);
                    }
                    individualComponentObject.put("optionPrompt", optionArray);
//                    String value = applicationResponseValueRepository.getValue(application, component, canva);
//                    individualComponentObject.put("Value", value);
                    canvaArray.put(individualComponentObject);

                }
                canvaObjects.put("canvaComponent",canvaArray);
                canvaObjects.put("canvasName", canvasName);
                canvaObjects.put("canvasId", canvasId);
                canvaObjects.put("canvasPostion", subform.getPosition());
                canvaArrays.put(canvaObjects);
            }
            formObjects.put("Canva",canvaArrays);
            outerArray.put(formObjects);

        }
        String jsonString = outerArray.toString();
        return ResponseEntity.ok(jsonString);
    }

    @Transactional
    public ResponseEntity<String> getFormById(final UUID fId) {
        //get full form
        JSONArray outerArray = new JSONArray();
        FormWorkflow form = formWorkflowRepository.getReferenceById(fId);
        JSONObject formObjects = new JSONObject();
        FormStep stepIdApprover = formStepRepository.findByParentFormAndAction(form, "Check and Approve");
        List<AssociatedSubform> subforms = associatedSubformRepository.findCanvasByStep(stepIdApprover);
        formObjects.put("formId", form.getFormUuid());
        formObjects.put("formName", form.getName());
        JSONArray canvaArrays = new JSONArray();
        for (AssociatedSubform subform : subforms) {
            JSONObject canvaObjects = new JSONObject();
            UUID canvasId = subform.getCanvasUuid().getCanvasUuid();
            SubformCanvas canva = subformCanvasRepository.getReferenceById(canvasId);
            String canvasName = canva.getName();
            JSONArray canvaArray = new JSONArray();
            final List<InputComponent> components = inputComponentRepository.findByParentCanvas(canva);
            for (InputComponent component : components) {
                JSONObject individualComponentObject = new JSONObject();
                int componentOrderNo = component.getOrderNo();
                String question = component.getQuestion();
                String type = component.getType();
                UUID cId = component.getComponentUuid();
                individualComponentObject.put("orderNo", componentOrderNo);
                individualComponentObject.put("question", question);
                individualComponentObject.put("type", type);
                individualComponentObject.put("componentId", cId);
                List<Options> options = optionsRepository.findByParentInputComponent(cId);
                JSONArray optionArray = new JSONArray();
                for (Options option : options) {
                    String prompt = option.getOptionPrompt();
                    optionArray.put(prompt);
                }
                individualComponentObject.put("optionPrompt", optionArray);
//                    String value = applicationResponseValueRepository.getValue(application, component, canva);
//                    individualComponentObject.put("Value", value);
                canvaArray.put(individualComponentObject);

            }
            canvaObjects.put("canvaComponent",canvaArray);
            canvaObjects.put("canvasName", canvasName);
            canvaObjects.put("canvasId", canvasId);
            canvaObjects.put("canvasPostion", subform.getPosition());
            canvaArrays.put(canvaObjects);
        }
        formObjects.put("canva",canvaArrays);
        outerArray.put(formObjects);
        String jsonString = outerArray.toString();
        return ResponseEntity.ok(jsonString);
    }


}
