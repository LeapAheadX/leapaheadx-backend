package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.*;
import com.oop.leap_ahead_x.dto.ApplicationDTO;
import com.oop.leap_ahead_x.repos.*;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final VendorRepository vendorRepository;
    private final FormWorkflowRepository formWorkflowRepository;
    private final FormStepRepository formStepRepository;
    private final UserRepository userRepository;
    private final AssociatedSubformRepository associatedSubformRepository;
    private final SubformCanvasRepository subformCanvasRepository;
    private final InputComponentRepository inputComponentRepository;
    private final OptionsRepository optionsRepository;
    private final ApplicationResponseValueRepository applicationResponseValueRepository;
    public ApplicationService(final ApplicationRepository applicationRepository,
            final VendorRepository vendorRepository,
            final FormWorkflowRepository formWorkflowRepository,final UserRepository userRepository,
                              final FormStepRepository formStepRepository, final AssociatedSubformRepository associatedSubformRepository,
                              final  SubformCanvasRepository subformCanvasRepository,final InputComponentRepository inputComponentRepository,
                              final OptionsRepository optionsRepository,final ApplicationResponseValueRepository applicationResponseValueRepository) {
        this.applicationRepository = applicationRepository;
        this.vendorRepository = vendorRepository;
        this.formWorkflowRepository = formWorkflowRepository;
        this.userRepository = userRepository;
        this.formStepRepository = formStepRepository;
        this.associatedSubformRepository=associatedSubformRepository;
        this.subformCanvasRepository = subformCanvasRepository;
        this.inputComponentRepository =inputComponentRepository;
        this.optionsRepository = optionsRepository;
        this.applicationResponseValueRepository = applicationResponseValueRepository;
    }

    public List<ApplicationDTO> findAll() {
        final List<Application> applications = applicationRepository.findAll(Sort.by("applicationUuid"));
        return applications.stream()
                .map((application) -> mapToDTO(application, new ApplicationDTO()))
                .toList();
    }

    public ApplicationDTO get(final UUID applicationUuid) {
        return applicationRepository.findById(applicationUuid)
                .map(application -> mapToDTO(application, new ApplicationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public List<ApplicationDTO> getByStatus(final String status){
        final List<Application> applications = applicationRepository.findByStatus(status);
        return applications.stream()
                .map((application) -> mapToDTO(application, new ApplicationDTO()))
                .toList();
    }

    public UUID create(final ApplicationDTO applicationDTO) {
        final Application application = new Application();
        mapToEntity(applicationDTO, application);
        return applicationRepository.save(application).getApplicationUuid();
    }

    public void update(final UUID applicationUuid, final ApplicationDTO applicationDTO) {
        final Application application = applicationRepository.findById(applicationUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(applicationDTO, application);
        applicationRepository.save(application);
    }

    public void delete(final UUID applicationUuid) {
        applicationRepository.deleteById(applicationUuid);
    }

    @Transactional
    public ResponseEntity<String> getAssignedApplication(final UUID uId,final UUID aId) {
        JSONArray outerArray = new JSONArray();
        String assigneeType = userRepository.getReferenceById(uId).getRole();
        List<Application> applications = applicationRepository.findAll();
        for (Application application : applications) {
            FormWorkflow form = formWorkflowRepository.getReferenceById(application.getFormUuid().getFormUuid());
            int currentStep = application.getCurrentStepNo();
            Vendor createdFor = application.getCreatedFor();
            //find steps using parentForm
            List<FormStep> steps = formStepRepository.findByParentForm(form);
            for (FormStep step : steps) {
                //check if orderNumber == currentstep and the currentUserType is the same
                if (step.getOrderNo().equals(currentStep) && step.getAssigneeType().equals(assigneeType)&& application.getApplicationUuid().equals(aId)) {
                    JSONObject canvaObject = new JSONObject();
                    //if same, return form
                    //Go associated subform and get the subcanvas id that it needs to fill up
                    List<AssociatedSubform> canvas = associatedSubformRepository.findByStepUuid(step);
                    JSONArray canvaFillupNotRestricted = new JSONArray();
                    for (AssociatedSubform canva : canvas) {
                        canvaFillupNotRestricted.put(canva.getCanvasUuid().getCanvasUuid());
                    }
                    //an arrray that shows what subcanvas this user is able to edit
                    canvaObject.put("canvaFillUpNotRestricted", canvaFillupNotRestricted);
                    canvaObject.put("applicationID", application.getApplicationUuid());
                    canvaObject.put("applicationStatus",application.getStatus());
                    canvaObject.put("currentStep",application.getCurrentStepNo());
                    canvaObject.put("FormId", form.getFormUuid());
                    canvaObject.put("FormName", form.getName());
                    canvaObject.put("VendorId", createdFor.getVendorUuid());
                    outerArray.put(canvaObject);
                }
            }
        }
        String jsonString = outerArray.toString();
        return ResponseEntity.ok(jsonString);
    }

    @Transactional
    public ResponseEntity<String> getApplicationWithAllDetails(final UUID aId) {
        //get full form
        JSONArray outerArray = new JSONArray();
        Application application = applicationRepository.getReferenceById(aId);
        FormWorkflow form = formWorkflowRepository.getReferenceById(application.getFormUuid().getFormUuid());
        JSONObject formObjects = new JSONObject();
        FormStep stepIdApprover = formStepRepository.findByParentFormAndAction(form, "Check and Approve");
        List<AssociatedSubform> subforms = associatedSubformRepository.findCanvasByStep(stepIdApprover);
        formObjects.put("applicationId",aId);
        formObjects.put("applicationStatus",application.getStatus());
        formObjects.put("currentStep",application.getCurrentStepNo());
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
                List<Options> options = optionsRepository.findOptionsByComponent(cId);
                JSONArray optionArray = new JSONArray();
                for (Options option : options) {
                    String prompt = option.getOptionPrompt();
                    optionArray.put(prompt);
                }
                individualComponentObject.put("optionPrompt", optionArray);
                String value = applicationResponseValueRepository.getValue(application, component, canva);
                individualComponentObject.put("value", value);
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

    @Transactional
    public ResponseEntity<String> getApplicationByVendor(final UUID vId) {
        Vendor vendor = vendorRepository.getReferenceById(vId);
        List<Application> applications = applicationRepository.findByCreatedFor(vendor);
        JSONArray outerArray = new JSONArray();
        for(Application application:applications){
            JSONObject applicationObject = new JSONObject();
            applicationObject.put("applicationId",application.getApplicationUuid());
            applicationObject.put("status",application.getStatus());
            applicationObject.put("currentStep",application.getCurrentStepNo());
            FormWorkflow form = application.getFormUuid();
            applicationObject.put("FormId",form.getFormUuid());
            applicationObject.put("FormName",form.getName());
            outerArray.put(applicationObject);
        }
        String jsonString = outerArray.toString();
        return ResponseEntity.ok(jsonString);
    }


    private ApplicationDTO mapToDTO(final Application application,
            final ApplicationDTO applicationDTO) {
        applicationDTO.setApplicationUuid(application.getApplicationUuid());
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setComment(application.getComment());
        applicationDTO.setCurrentStepNo(application.getCurrentStepNo());
//        applicationDTO.setCreatedFor(application.getCreatedFor() == null ? null : application.getCreatedFor().getVendorUuid());
        Optional<Vendor> vendor = vendorRepository.findById(application.getCreatedFor().getVendorUuid());
        applicationDTO.setCompany(vendor.get().getCompany());

        Optional<FormWorkflow> formWorkflow = formWorkflowRepository.findById(application.getFormUuid().getFormUuid());
        applicationDTO.setFormName(formWorkflow.get().getName());
//        applicationDTO.setFormUuid(application.getFormUuid() == null ? null : application.getFormUuid().getFormUuid());
        return applicationDTO;
    }

    private Application mapToEntity(final ApplicationDTO applicationDTO,
            final Application application) {
        application.setStatus(applicationDTO.getStatus());
        application.setComment(applicationDTO.getComment());
        application.setCurrentStepNo(applicationDTO.getCurrentStepNo());
//        final Vendor createdFor = applicationDTO.getCreatedFor() == null ? null : vendorRepository.findById(applicationDTO.getCreatedFor())
//                .orElseThrow(() -> new NotFoundException("createdFor not found"));
//        application.setCreatedFor(createdFor);

        final Vendor createdFor = applicationDTO.getCompany() == null ? null : vendorRepository.findByCompanyName(applicationDTO.getCompany());
        application.setCreatedFor(createdFor);
//        final FormWorkflow formUuid = applicationDTO.getFormUuid() == null ? null : formWorkflowRepository.findById(applicationDTO.getFormUuid())
//                .orElseThrow(() -> new NotFoundException("formUuid not found"));
//        application.setFormUuid(formUuid);

        final FormWorkflow formUuid = applicationDTO.getFormName() == null ? null : formWorkflowRepository.findByFormName(applicationDTO.getFormName());
        application.setFormUuid(formUuid);
        return application;
    }

}
