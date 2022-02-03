package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.BatchDTO;
import br.com.meli.wave4.DTO.BatchSimpleResponseDTO;
import br.com.meli.wave4.DTO.ListProductWithAllBatchDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.services.iservices.IProductService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return productRepository.findById(productId).orElse(new Product());
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

        Batch batch = product.getBatchList()
                .stream().filter(b -> b.getSection().getSectionCode().equals(sectionCode))
                .findFirst().orElse(null);

        return batch.getCurrentQuantity() >= quantity;

    }

    public boolean verifyIfDueDateLessThan3Weeks(Product product) {
        return product.getDateValid().isBefore(LocalDate.now().minusDays(20));
    }

//    public List<BatchSimpleResponseDTO> sortByCurrentQuantity(List<BatchSimpleResponseDTO> batchList){
//        batchList.sort((a, b) -> b.getCurrentQuantity().compareTo(a.getCurrentQuantity()));
//        return batchList;
//    }
//   public ListProductWithAllBatchDTO filterProductInWarehouseOrdered(Warehouse warehouse, Product product,
//                                                                     Character charOrdered){
//
//        List<Batch> batchList = product.getBatchList().stream()
//                .filter(batch -> batch.getSection().getWarehouse().equals(warehouse))
//                .collect(Collectors.toList());
//
//        List<BatchSimpleResponseDTO> batchSimpleResponseDTOList = batchList.stream()
//                .map(batch -> BatchSimpleResponseDTO.builder()
//                        .batchNumber(batch.getBatchNumber())
//                        .currentQuantity(batch.getCurrentQuantity())
//                        .dueDate(batch.getDueDate())
//                        .build()
//                ).collect(Collectors.toList());
//
//        List<BatchSimpleResponseDTO> sortedList;
//        switch (charOrdered){
//            case 'C':
//            case 'c': sortedList = sortByCurrentQuantity(batchSimpleResponseDTOList);
//                    break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + charOrdered);
//        }
//
//        return ListProductWithAllBatchDTO.builder()
//                .sectionCode(batchList.get(0).getSection().getSectionCode())
//                .warehouseCode(batchList.get(0).getSection().getWarehouse().getId())
//                .productId(product.getId())
//                .batchStock(sortedList).build();
//    }

    public ListProductWithAllBatchDTO filterProductInWarehouse(Warehouse warehouse, Product product) {

        List<Batch> batchList = product.getBatchList().stream()
                .filter(batch -> batch.getSection().getWarehouse().equals(warehouse))
                .collect(Collectors.toList());

        List<BatchSimpleResponseDTO> batchSimpleResponseDTOList = batchList.stream()
                .map(batch -> BatchSimpleResponseDTO.builder()
                        .batchNumber(batch.getBatchNumber())
                        .currentQuantity(batch.getCurrentQuantity())
                        .dueDate(batch.getDueDate())
                        .build()
                ).collect(Collectors.toList());

        return ListProductWithAllBatchDTO.builder()
                .sectionCode(batchList.get(0).getSection().getSectionCode())
                .warehouseCode(batchList.get(0).getSection().getWarehouse().getId())
                .productId(product.getId())
                .batchStock(batchSimpleResponseDTOList).build();
    }

    public List<BatchSimpleResponseDTO> orderByBatchNumber(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList) {

        batchSimpleResponseDTOList.sort(Comparator.comparing(BatchSimpleResponseDTO::getBatchNumber));

        return batchSimpleResponseDTOList;
    }

    public List<BatchSimpleResponseDTO> orderByCurrentQuantity(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList) {
        batchSimpleResponseDTOList.sort(Comparator.comparing(BatchSimpleResponseDTO::getCurrentQuantity));
        return batchSimpleResponseDTOList;
    }

    public List<BatchSimpleResponseDTO> orderByDueDate(List<BatchSimpleResponseDTO> batchSimpleResponseDTOList) {
        batchSimpleResponseDTOList.sort(Comparator.comparing(BatchSimpleResponseDTO::getDueDate));
        return batchSimpleResponseDTOList;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product update(Product product) {
        Product productUpdated = productRepository.findById(product.getId()).orElse(null);
        productUpdated.setPrice(product.getPrice());
        productUpdated.setDateValid(product.getDateValid());
        productUpdated.setSeller(product.getSeller());
        productUpdated.setName(product.getName());
        productUpdated.setSectionTypeRefrigerated(product.getSectionTypeRefrigerated());

        return productRepository.saveAndFlush(productUpdated);

    }

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
