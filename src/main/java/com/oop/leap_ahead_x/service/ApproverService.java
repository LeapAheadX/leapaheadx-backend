package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.Approver;
import com.oop.leap_ahead_x.domain.User;
import com.oop.leap_ahead_x.dto.ApproverDTO;
import com.oop.leap_ahead_x.repos.ApproverRepository;
import com.oop.leap_ahead_x.repos.UserRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ApproverService {

    private final ApproverRepository approverRepository;
    private final UserRepository userRepository;

    public ApproverService(final ApproverRepository approverRepository,
            final UserRepository userRepository) {
        this.approverRepository = approverRepository;
        this.userRepository = userRepository;
    }

    public List<ApproverDTO> findAll() {
        final List<Approver> approvers = approverRepository.findAll(Sort.by("approverUuid"));
        return approvers.stream()
                .map((approver) -> mapToDTO(approver, new ApproverDTO()))
                .toList();
    }

    public ApproverDTO get(final UUID approverUuid) {
        return approverRepository.findById(approverUuid)
                .map(approver -> mapToDTO(approver, new ApproverDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ApproverDTO approverDTO) {
        final Approver approver = new Approver();
        mapToEntity(approverDTO, approver);
        return approverRepository.save(approver).getApproverUuid();
    }

    public void update(final UUID approverUuid, final ApproverDTO approverDTO) {
        final Approver approver = approverRepository.findById(approverUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(approverDTO, approver);
        approverRepository.save(approver);
    }

    public void delete(final UUID approverUuid) {
        approverRepository.deleteById(approverUuid);
    }

    private ApproverDTO mapToDTO(final Approver approver, final ApproverDTO approverDTO) {
        approverDTO.setApproverUuid(approver.getApproverUuid());
        approverDTO.setApprovalTier(approver.getApprovalTier());
        approverDTO.setUId(approver.getUId() == null ? null : approver.getUId().getUId());
        return approverDTO;
    }

    private Approver mapToEntity(final ApproverDTO approverDTO, final Approver approver) {
        approver.setApprovalTier(approverDTO.getApprovalTier());
        final User uId = approverDTO.getUId() == null ? null : userRepository.findById(approverDTO.getUId())
                .orElseThrow(() -> new NotFoundException("uId not found"));
        approver.setUId(uId);
        return approver;
    }

}
