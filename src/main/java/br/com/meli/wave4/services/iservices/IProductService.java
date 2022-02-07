package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.DTO.BatchSimpleResponseDTO;
import br.com.meli.wave4.DTO.ListProductWithAllBatchDTO;
import br.com.meli.wave4.entities.*;

import java.util.List;

public interface IProductService {
    Product findById(Integer productId);

    List<Product> getAll();

    List<Product> findAllByCategory(TypeRefrigeration type);

    Boolean verifyStock(Integer productId, Integer quantity, Integer sectionCode);

    boolean verifyIfDueDateLessThan3Weeks(Product product);

    public ListProductWithAllBatchDTO filterProductInWarehouse(Warehouse warehouse, Product product,
                                                                      Character charOrdered);

    public List<Batch> getBatchListInSpecificWarehouse(Warehouse warehouse, Product product);

    public List<BatchSimpleResponseDTO> getBatchSimpleResponseDTOS(List<Batch> batchList);

    public List<BatchSimpleResponseDTO> orderByBatchNumber(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList);

    public List<BatchSimpleResponseDTO> orderByCurrentQuantity(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList);

    public List<BatchSimpleResponseDTO> orderByDueDate(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList);

    public Product save(Product product);

    public Product update(Product product);

    public List<WarehouseProductInfo> countProductInWarehouse(Integer productId);

    public List<ProductNearExpireDate> getProductsNearOfExpiraionDate(Integer days, String category, String order);

    public List<ProductNearExpireDate> getProductsNearOfExpiraionDate(Integer days, Integer sectionCode);

}