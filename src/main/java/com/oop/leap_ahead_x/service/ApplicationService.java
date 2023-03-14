package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.Application;
import com.oop.leap_ahead_x.domain.FormWorkflow;
import com.oop.leap_ahead_x.domain.Vendor;
import com.oop.leap_ahead_x.dto.ApplicationDTO;
import com.oop.leap_ahead_x.repos.ApplicationRepository;
import com.oop.leap_ahead_x.repos.FormWorkflowRepository;
import com.oop.leap_ahead_x.repos.VendorRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final VendorRepository vendorRepository;
    private final FormWorkflowRepository formWorkflowRepository;

    public ApplicationService(final ApplicationRepository applicationRepository,
            final VendorRepository vendorRepository,
            final FormWorkflowRepository formWorkflowRepository) {
        this.applicationRepository = applicationRepository;
        this.vendorRepository = vendorRepository;
        this.formWorkflowRepository = formWorkflowRepository;
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
