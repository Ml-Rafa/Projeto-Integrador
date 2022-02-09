package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.BatchDTO;
import br.com.meli.wave4.DTO.BatchSimpleResponseDTO;
import br.com.meli.wave4.DTO.ListProductWithAllBatchDTO;
import br.com.meli.wave4.DTO.ProductDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.DueDateLessThan3WeeksException;
import br.com.meli.wave4.exceptions.InsufficientStockException;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.services.iservices.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    BatchService batchService;

    @Autowired
    UserService userService;

    @Autowired
    SellerService sellerService;

    @Autowired
    SectionService sectionService;

    @Override
    public Product findById(Integer productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new NotFoundException("Produto não localizado");
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
    public Boolean verifyStock(Integer productId, Integer quantity, Integer batchCode) {
        Product product = this.productRepository.findById(productId).orElse(null);
        assert product != null;
        Batch batch = product.getBatchList()
                .stream().filter(b -> b.getBatchNumber().equals(batchCode))
                .findFirst().orElse(null);
        assert batch != null;
        if (batch.getCurrentQuantity() < quantity)
            throw new InsufficientStockException();
        return true;
    }

    @Override
    public boolean verifyIfDueDateLessThan3Weeks(Product product) {
        if (product.getDateValid().isAfter(LocalDate.now().plusDays(20)))
            return false;
        throw new DueDateLessThan3WeeksException();

    }

    @Override
    public ListProductWithAllBatchDTO filterProductInWarehouse(Warehouse warehouse, Product product,
                                                               Character charOrdered) {

        List<Batch> batchList = getBatchListInSpecificWarehouse(warehouse, product);

        List<BatchSimpleResponseDTO> batchSimpleResponseDTOList = getBatchSimpleResponseDTOS(batchList);

        List<BatchSimpleResponseDTO> sortedList;
        switch (charOrdered) {
            case 'L':
            case 'l':
                sortedList = orderByBatchNumber(batchSimpleResponseDTOList);
                break;
            case 'C':
            case 'c':
                sortedList = orderByCurrentQuantity(batchSimpleResponseDTOList);
                break;
            case 'F':
            case 'f':
                sortedList = orderByDueDate(batchSimpleResponseDTOList);
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
    public Product save(Product product) {
        return productRepository.save(product);
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
        List<Warehouse> warehouseList = this.warehouseService.findAll();
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

    @Override
    public List<ProductNearExpireDate> getProductsNearOfExpiraionDate(Integer days, String order) {

        List<Warehouse> warehouseList = this.warehouseService.findAll();

        List<ProductNearExpireDate> productNearExpireDateList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        warehouseList.forEach(w -> w.getSectionSet().forEach(s -> {
            generateListProductsNearOfExpirationDate(days, productNearExpireDateList, today, s);
        }));

        if (!productNearExpireDateList.isEmpty()) {
            if (order != null && !order.isEmpty()) {
                List<ProductNearExpireDate> filteredList = productNearExpireDateList.stream()
                        .filter(productNearExpireDate -> productNearExpireDate.getTypeRefrigerated().equals(TypeRefrigeration.valueOf(order.toUpperCase()).getCode()))
                        .collect(Collectors.toList());
                productNearExpireDateList.clear();
                productNearExpireDateList.addAll(filteredList);
            }
            productNearExpireDateList.sort(Comparator.comparing(ProductNearExpireDate::getDueDate));
            return productNearExpireDateList;
        } else {
            throw new NotFoundException("Não foram encntrados produtos neste período!");
        }
    }

    @Override
    public List<ProductNearExpireDate> getProductsNearOfExpiraionDate(Integer days, Integer sectionCode) {
        Section section = sectionService.findBySectionCode(sectionCode);
        List<ProductNearExpireDate> productNearExpireDateList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        generateListProductsNearOfExpirationDate(days, productNearExpireDateList, today, section);

        if (!productNearExpireDateList.isEmpty()) {
            productNearExpireDateList.sort(Comparator.comparing(ProductNearExpireDate::getDueDate));
            return productNearExpireDateList;
        } else {
            throw new NotFoundException("Não foram encntrados produtos neste período!");
        }
    }

    private void generateListProductsNearOfExpirationDate(Integer days, List<ProductNearExpireDate> productNearExpireDateList, LocalDate today, Section s) {
        s.getBatchList().forEach(batch -> {

            if (DAYS.between(today, batch.getDueDate()) <= days) {
                LocalDate dueDate = batch.getDueDate();
                productNearExpireDateList.add(
                        new ProductNearExpireDate(
                                batch.getBatchNumber(),
                                batch.getProduct().getId(),
                                batch.getProduct().getSectionTypeRefrigerated().getCode(),
                                batch.getCurrentQuantity(),
                                dueDate,
                                s.getWarehouse().getId(),
                                s.getSectionCode()
                        )
                );
            }
        });
    }

    public Product convertToEntity(ProductDTO product) {

        List<Batch> batchList = product.getBatchList().stream().map(batchService::convertToEntity).collect(Collectors.toList());
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .sectionTypeRefrigerated(product.getSectionTypeRefrigerated())
                .dateValid(product.getDateValid())
                .price(product.getPrice())
                .batchList(batchList)
                .seller(sellerService.convertToEntity(product.getSeller()))
                .build();
    }

    public ProductDTO convertToDTO(Product product) {

        List<BatchDTO> batchDTOList = product.getBatchList().stream().map(BatchDTO::convertToDTO).collect(Collectors.toList());
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sectionTypeRefrigerated(product.getSectionTypeRefrigerated())
                .dateValid(product.getDateValid())
                .price(product.getPrice())
                .batchList(batchDTOList)
                .seller(sellerService.convertToDTO(product.getSeller()))
                .build();
    }


    @Override
    public List<Batch> findProductsOnSaleByWarehouse(Integer warehouseId){
        Warehouse warehouse = warehouseService.findById(warehouseId);
        List<Batch> batches = new ArrayList<>();

        warehouse.getSectionSet().forEach(section -> {
            section.getBatchList().forEach(b -> {
                if (b.getCurrentQuantity() > 0
                        && b.getDueDate().isBefore(LocalDate.now().plusDays(35))
                        && b.getDueDate().isAfter(LocalDate.now().plusDays(20))
                ){
                    batches.add(b);
                }
            });
        });

        batches.forEach(batch -> {
            Double discountPercentage = Double.valueOf(batch.getDiscountOfDueDate() / 100);
            Double valueOfDiscount = batch.getProduct().getPrice().doubleValue() - (batch.getProduct().getPrice().doubleValue() * discountPercentage);
            batch.getProduct().setPrice(BigDecimal.valueOf(valueOfDiscount));
        });

        return batches;
    }
}
