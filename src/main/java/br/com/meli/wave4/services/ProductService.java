package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.BatchDTO;
import br.com.meli.wave4.DTO.BatchSimpleResponseDTO;
import br.com.meli.wave4.DTO.ListProductWithAllBatchDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.DueDateLessThan3WeeksException;
import br.com.meli.wave4.exceptions.InsufficientStockException;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.services.iservices.IProductService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WarehouseService warehouseService;

    @Override
    public Product findById(Integer productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null)
            throw  new NotFoundException("Produto não localizado");
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(TypeRefrigeration type) {
        return productRepository.findAllBySectionTypeRefrigerated(type);

    }

    @Override
    public Boolean verifyStock(Integer productId, Integer quantity, Integer sectionCode) {
        Product product = this.productRepository.findById(productId).orElse(null);
        assert product != null;
        Batch batch = product.getBatchList()
                .stream().filter(b -> b.getSection().getSectionCode().equals(sectionCode))
                .findFirst().orElse(null);
        assert batch != null;
        if(batch.getCurrentQuantity() < quantity)
           throw new InsufficientStockException();
       return true;
    }

    @Override
    public boolean verifyIfDueDateLessThan3Weeks(Product product) {
        if(product.getDateValid().isBefore(LocalDate.now().plusDays(20)))
            return false;
        throw new DueDateLessThan3WeeksException();
    }

    @Override
   public ListProductWithAllBatchDTO filterProductInWarehouse(Warehouse warehouse, Product product,
                                                                     Character charOrdered){

       List<Batch> batchList = getBatchListInSpecificWarehouse(warehouse, product);

       List<BatchSimpleResponseDTO> batchSimpleResponseDTOList = getBatchSimpleResponseDTOS(batchList);

       List<BatchSimpleResponseDTO> sortedList;
        switch (charOrdered){
            case 'L': case 'l':sortedList = orderByBatchNumber(batchSimpleResponseDTOList);
                break;
            case 'C': case 'c': sortedList = orderByCurrentQuantity(batchSimpleResponseDTOList);
                break;
            case 'F': case 'f': sortedList = orderByDueDate(batchSimpleResponseDTOList);
                break;
            default:
                sortedList = batchSimpleResponseDTOList;
        }

        return ListProductWithAllBatchDTO.builder()
                .sectionCode(batchList.get(0).getSection().getSectionCode())
                .warehouseCode(batchList.get(0).getSection().getWarehouse().getId())
                .productId(product.getId())
                .batchStock(sortedList).build();
    }
    @Override
    public List<Batch> getBatchListInSpecificWarehouse(Warehouse warehouse, Product product) {
        return product.getBatchList().stream()
                .filter(batch -> batch.getSection().getWarehouse().equals(warehouse))
                .collect(Collectors.toList());
    }
    @Override
    public List<BatchSimpleResponseDTO> getBatchSimpleResponseDTOS(List<Batch> batchList) {
        return batchList.stream()
                .map(batch -> BatchSimpleResponseDTO.builder()
                        .batchNumber(batch.getBatchNumber())
                        .currentQuantity(batch.getCurrentQuantity())
                        .dueDate(batch.getDueDate())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<BatchSimpleResponseDTO> orderByBatchNumber(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList) {

        batchSimpleResponseDTOList.sort(Comparator.comparing(BatchSimpleResponseDTO::getBatchNumber));

        return batchSimpleResponseDTOList;
    }
    @Override
    public List<BatchSimpleResponseDTO> orderByCurrentQuantity(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList) {
        batchSimpleResponseDTOList.sort(Comparator.comparing(BatchSimpleResponseDTO::getCurrentQuantity));
        return batchSimpleResponseDTOList;
    }
    @Override
    public List<BatchSimpleResponseDTO> orderByDueDate(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList) {
        batchSimpleResponseDTOList.sort(Comparator.comparing(BatchSimpleResponseDTO::getDueDate));
        return batchSimpleResponseDTOList;
    }
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product productUpdated = productRepository.findById(product.getId()).orElse(null);
        assert productUpdated != null;
        productUpdated.setPrice(product.getPrice());
        productUpdated.setDateValid(product.getDateValid());
        productUpdated.setSeller(product.getSeller());
        productUpdated.setName(product.getName());
        productUpdated.setSectionTypeRefrigerated(product.getSectionTypeRefrigerated());

        return productRepository.saveAndFlush(productUpdated);
    }

    @Override
    public List<WarehouseProductInfo> countProductInWarehouse(Integer productId) {
        List<Warehouse> warehouseList = warehouseService.findAll();

        List<WarehouseProductInfo> warehouseProductInfoList = new ArrayList<>();

        warehouseList.stream().forEach(warehouse -> {
            Integer quantityProduct = warehouse.getSectionSet().stream().mapToInt(section -> {
                return section.getBatchList().stream().mapToInt(batch -> {
                    if (batch.getProduct().getId().equals(productId)) {
                        return batch.getCurrentQuantity();
                    }
                    return 0;
                }).sum();
            }).sum();

            if (quantityProduct > 0) {
                warehouseProductInfoList.add(new WarehouseProductInfo(productId, warehouse, quantityProduct));
            }
        });

        if (warehouseProductInfoList.size() > 0) {
            return warehouseProductInfoList;
        } else {
            throw new NotFoundException("O produto não foi encontrado em nenhum armazem!");
        }
    }

}
