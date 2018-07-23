package ru.ibarhatov.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ibarhatov.restapp.domain.RoleCode;
import ru.ibarhatov.restapp.model.Role;
import ru.ibarhatov.restapp.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final String createCommand = "/create";
    private final String findCommand = "/find";
    private final String updateCommand = "/update";
    private final String deleteCommand = "/delete";

    @Autowired
    RoleService roleService;

    @PutMapping(createCommand)
    public Integer createRole(@RequestBody Role role) {
        Integer id = roleService.create(role);
        return id;
    }

    @GetMapping(findCommand)
    public String findRole(@RequestParam(name = "code") RoleCode roleCode) {
        Role result = roleService.findOne(roleCode);
        return result != null ? result.getName() : "Not found.";
    }

    @PostMapping(updateCommand)
    public Integer updateRole(@RequestBody Role role) {
        Integer result = roleService.update(role);
        return result;
    }

    @DeleteMapping(deleteCommand)
    public void deleteRole(@RequestParam(name = "id") Integer id) {
        roleService.delete(id);
    }
}