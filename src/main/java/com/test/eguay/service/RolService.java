package com.test.eguay.service;

import com.test.eguay.dto.RolDTO;
import com.test.eguay.entity.Rol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    @EJB RolFacade rolFacade;

    public List<RolDTO> findAll() {
        return Rol.toDTO(rolFacade.findAll());
    }
}
