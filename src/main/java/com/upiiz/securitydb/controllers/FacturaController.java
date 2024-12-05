package com.upiiz.securitydb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {
    //GET
    @GetMapping
    public String listarFacturas() {
        return "Listado de facturas";
    }

    //POST
    @PostMapping
    public String crearFactura() {
        return "Creado de facturas";
    }

    //PUT
    @PutMapping
    public String actualizarFactura() {
        return "Actualizado de una factura";
    }

    //DELETE
    @DeleteMapping
    public String eliminarFactura(){
        return "Eliminado de una factura";
    }
    
    
}
