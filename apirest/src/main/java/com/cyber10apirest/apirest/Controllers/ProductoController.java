package com.cyber10apirest.apirest.Controllers;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber10apirest.apirest.Ropositories.ProductoRepository;

import com.cyber10apirest.apirest.Entities.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/producto")

public class ProductoController {
    // ↓ repositorio que tiene q traer de la bdd
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @PostMapping
    Producto createProducts(@RequestBody Producto Requestproducto) {
        return productoRepository.save(Requestproducto);
    }

    @GetMapping("/{id}")
    public Producto GetProductoId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se pudo encontrar el id " + id));

    }

    /*
     * @PutMapping
     * public Producto actualizarProducto(@PathVariable Long id, Producto
     * dealleProducto){
     * 
     * }
     */
    @PutMapping("/{id}")
    public Producto actualizaProducto(@PathVariable Long id, @RequestBody Producto detalleProducto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se encontro el producto con el Id: " + id));
        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());
        return productoRepository.save(producto);

    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el Id: " + id));
        productoRepository.delete(producto); // Elimina el producto de la base de datos
        return "El producto con el id: " + id + " fue eliminado";
    }

}
