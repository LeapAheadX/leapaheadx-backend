package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.Admin;
import com.oop.leap_ahead_x.domain.User;
import com.oop.leap_ahead_x.dto.AdminDTO;
import com.oop.leap_ahead_x.repos.AdminRepository;
import com.oop.leap_ahead_x.repos.UserRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminService(final AdminRepository adminRepository,
            final UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public List<AdminDTO> findAll() {
        final List<Admin> admins = adminRepository.findAll(Sort.by("adminUuid"));
        return admins.stream()
                .map((admin) -> mapToDTO(admin, new AdminDTO()))
                .toList();
    }

    public AdminDTO get(final UUID adminUuid) {
        return adminRepository.findById(adminUuid)
                .map(admin -> mapToDTO(admin, new AdminDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final AdminDTO adminDTO) {
        final Admin admin = new Admin();
        mapToEntity(adminDTO, admin);
        return adminRepository.save(admin).getAdminUuid();
    }

    public void update(final UUID adminUuid, final AdminDTO adminDTO) {
        final Admin admin = adminRepository.findById(adminUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(adminDTO, admin);
        adminRepository.save(admin);
    }

    public void delete(final UUID adminUuid) {
        adminRepository.deleteById(adminUuid);
    }

    private AdminDTO mapToDTO(final Admin admin, final AdminDTO adminDTO) {
        adminDTO.setAdminUuid(admin.getAdminUuid());
        adminDTO.setDepartment(admin.getDepartment());
        adminDTO.setCountry(admin.getCountry());
        adminDTO.setUId(admin.getUId() == null ? null : admin.getUId().getUId());
        return adminDTO;
    }

    private Admin mapToEntity(final AdminDTO adminDTO, final Admin admin) {
        admin.setDepartment(adminDTO.getDepartment());
        admin.setCountry(adminDTO.getCountry());
        final User uId = adminDTO.getUId() == null ? null : userRepository.findById(adminDTO.getUId())
                .orElseThrow(() -> new NotFoundException("uId not found"));
        admin.setUId(uId);
        return admin;
    }

}
