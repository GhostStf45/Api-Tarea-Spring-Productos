package com.retail.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.retail.demo.model.Producto;

@RestController
@RequestMapping(value="api/producto", produces ="application/json")
public class ProductoController {
    private Map<String, Producto> productos;
    // Listar productos
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Producto>> listall() {
        return new ResponseEntity<Map<String, Producto>>(productos, HttpStatus.OK);
    }
    // crear producto
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<String> add (@RequestBody Producto p){
        String id  = UUID.randomUUID().toString();
        if(productos == null){
            productos = new HashMap<String, Producto>();
             p = new Producto();
            p.setName("producto1");
            p.setDescription("descripcion del producto 1");
            p.setCategory("Categoria 1");
            p.setPrice(999.99);
            p.setCreatedAt(new Date());
            productos.put(id, p);
            p.setId(id);
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }else{
            p.setId(id);
            productos.put(id, p);
            p.setCreatedAt(new Date());
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }

    }
    // obtener un producto
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> find(@PathVariable String id) {
        if (productos.containsKey(id)) {
            Producto p = productos.get(id);
            return new ResponseEntity<Producto>(p, HttpStatus.OK);

        } else {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }

    // actualizar un producto
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Producto> update(@RequestBody Producto p){
        String id = p.getId();
        if(!productos.containsKey(id)){
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
        Producto newProduct = new Producto();
        newProduct.setId(id);
        newProduct.setCreatedAt(new Date());
        p  = newProduct;
        productos.put(id, p);
        return new ResponseEntity<Producto>(p, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable String id){
        if (productos.containsKey(id)) {
            productos.remove(id);
            return new ResponseEntity<Producto>(HttpStatus.OK);

        } else {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }



}
