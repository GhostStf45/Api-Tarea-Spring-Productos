package com.retail.demo.model;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Date createdAt;
}
