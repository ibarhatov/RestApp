package ru.ibarhatov.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ibarhatov.restapp.domain.RoleCode;
import ru.ibarhatov.restapp.model.Role;
import ru.ibarhatov.restapp.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public Integer createRole(@RequestBody Role role) {
        Integer id = roleService.create(role);
        return id;
    }

    @GetMapping
    public String findRole(@RequestParam(name = "code") RoleCode roleCode) {
        Role result = roleService.findOne(roleCode);
        return result != null ? result.getName() : "Not found.";
    }

    @PutMapping
    public Integer updateRole(@RequestBody Role role) {
        Integer result = roleService.update(role);
        return result;
    }

    @DeleteMapping
    public void deleteRole(@RequestParam(name = "id") Integer id) {
        roleService.delete(id);
    }
}