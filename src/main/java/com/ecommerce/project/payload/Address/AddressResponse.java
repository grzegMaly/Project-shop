package com.ecommerce.project.payload.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private List<AddressDTO> content;
//    private Integer pageNumber;
//    private Integer pageSize;
//    private Long totalElements;
//    private Integer totalPages;
//    private Boolean lastPage;
}
