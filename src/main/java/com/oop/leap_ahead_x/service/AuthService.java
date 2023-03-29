package com.oop.leap_ahead_x.service;


import com.oop.leap_ahead_x.domain.Admin;
import com.oop.leap_ahead_x.domain.Approver;
import com.oop.leap_ahead_x.domain.User;
import com.oop.leap_ahead_x.domain.Vendor;
import com.oop.leap_ahead_x.repos.AdminRepository;
import com.oop.leap_ahead_x.repos.ApproverRepository;
import com.oop.leap_ahead_x.repos.UserRepository;
import com.oop.leap_ahead_x.repos.VendorRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ApproverRepository approverRepository;
    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor
    public AuthService(UserRepository userRepository, AdminRepository adminRepository, ApproverRepository approverRepository, VendorRepository vendorRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.approverRepository = approverRepository;
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UUID> login(final String email, final String password) {

        // Check if there is an account that is tagged to the email
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Extract the password from the db
            String dBpassword = user.get().getPassword();

            // Check if the password is the same
            if (passwordEncoder.matches(password, dBpassword)) {

                // Construct the mock access token made up of [UUID, ROLE_UUID]
                List<UUID> returnToken = new ArrayList<>();
                UUID userUUID = user.get().getUId();

                // Adds the UUID into the Array
                returnToken.add(userUUID);

                // If it is Admin, add into the array the AdminUUID
                if (user.get().getRole().equals("admin")) {
                    Admin anAdmin = adminRepository.findByuId(user);
                    UUID adminUUID = anAdmin.getAdminUuid();
                    returnToken.add(adminUUID);

                } else if (user.get().getRole().equals("approver")) {
                    // If it is Approver, add into the array the ApproverUUID
                    Approver anApprover = approverRepository.findByuId(user);
                    UUID approverUUID = anApprover.getApproverUuid();
                    returnToken.add(approverUUID);

                } else if (user.get().getRole().equals("vendor")) {
                    // If it is vendor, add into the array the VendorUUID
                    Vendor anVendor = vendorRepository.findByuId(user);
                    UUID vendorUUID = anVendor.getVendorUuid();
                    returnToken.add(vendorUUID);

                }
                return returnToken;
            }
            return null;
        }
        return null;
    }

}
