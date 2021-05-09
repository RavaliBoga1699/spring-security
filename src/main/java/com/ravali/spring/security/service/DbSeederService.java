package com.ravali.spring.security.service;

import com.ravali.spring.security.models.Privileges;
import com.ravali.spring.security.models.Role;
import com.ravali.spring.security.repository.PrivilegesRepository;
import com.ravali.spring.security.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DbSeederService {

    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    PrivilegesRepository privilegesRepository;

    public void addDefaultRoles() {
        List<Privileges> privilegesList = new ArrayList<>();
        Privileges privilegeReadUser = Privileges.builder()
                .id(1l)
                .authority("read_user")
                .createdDate(new Date())
                .description("privilege to view user details")
                .build();

        Privileges privilegeWriteuser = Privileges.builder()
                .id(2l)
                .authority("write_user")
                .createdDate(new Date())
                .description("privilege to write user details")
                .build();
        privilegesList.add(privilegeReadUser);
        privilegesList.add(privilegeWriteuser);
        privilegesList = privilegesRepository.saveAll(privilegesList);

        Role role = Role.builder()
                .id(1l)
                .RoleName("admin")
                .privileges(privilegesList)
                .build();

        rolesRepository.save(role);
    }
}
