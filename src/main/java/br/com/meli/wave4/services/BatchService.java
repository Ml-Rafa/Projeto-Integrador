package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.BatchDTO;
import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.exceptions.BatchNotContainsProductException;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.repositories.BatchRepository;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.IBatchService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class BatchService implements IBatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductService productService;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private InboundOrderService inboundOrderService;

    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    @Override
    public Batch findByBatchNumber(Integer batchNumber) {
//        return batchRepository.findByBatchNumber(batchNumber);
        Batch batch = batchRepository.findByBatchNumber(batchNumber);
        if(batch == null)
            throw new NotFoundException("Não foi localizado nenhum lote com o número informado.");
        return batch;
    }

    @Override
    public List<Batch> saveAll(List<Batch> batchList) {
        return batchRepository.saveAll(batchList);
    }

    @Override
    public Batch save(Batch batch) {
        return batchRepository.save(batch);
    }


    public Batch convertToEntity(BatchDTO batch) {
        return Batch.builder()
                .batchNumber(batch.getBatchNumber())
                .currentQuantity(batch.getCurrentQuantity())
                .initialQuantity(batch.getInitialQuantity())
                .product(productRepository.findById(batch.getProductId()).stream().findFirst().orElse(null))
                .currentTemperature(batch.getCurrentTemperature())
                .minimumTemperature(batch.getMinimumTemperature())
//                .representative(userRepository.findById(batch.getRepresentativeId()).stream().findFirst().orElse(null))
                .dueDate(batch.getDueDate())
                .manufacturingDate(batch.getManufacturingDate())
                .manufacturingTime(batch.getManufacturingTime())
//                 .inboundOrder(this.inboundOrderService.findyById(batch.getInboundOrderId()))
                .build();
    }

    @Override
    public void update(Batch batch) {
        Batch batchUpdated = batchRepository.findById(batch.getBatchNumber()).orElse(new Batch());
        batchUpdated.setInboundOrder(batch.getInboundOrder());
        batchUpdated.setBatchNumber(batch.getBatchNumber());
        batchUpdated.setSection(batch.getSection());
        batchUpdated.setRepresentative(batch.getRepresentative());
        batchUpdated.setCurrentQuantity(batch.getCurrentQuantity());
        batchUpdated.setCurrentTemperature(batch.getCurrentTemperature());
        batchUpdated.setDueDate(batch.getDueDate());
        batchUpdated.setInitialQuantity(batch.getInitialQuantity());
        batchUpdated.setManufacturingDate(batch.getManufacturingDate());
        batchUpdated.setManufacturingTime(batch.getManufacturingTime());
        batchUpdated.setMinimumTemperature(batch.getMinimumTemperature());
        batchUpdated.setProduct(batch.getProduct());
        batchRepository.saveAndFlush(batchUpdated);
    }

    @Override
    public boolean verifyBatchContainsProduct(Batch batch, Product product) {
        Batch batch1 = product.getBatchList().stream()
                .filter(b -> b.getBatchNumber().equals(batch.getBatchNumber()))
                .findFirst().orElse(null);
        if(batch1 == null)
            throw new BatchNotContainsProductException();
        return true;
    }

    @Override
    public void updateStock(Integer productId, Integer quantity, Integer sectionCode) {
        Product product = this.productService.findById(productId);
        Batch batch = product.getBatchList()
                .stream().filter(b -> b.getSection().getSectionCode().equals(sectionCode))
                .findFirst().orElse(null);
        assert batch != null;
        batch.setCurrentQuantity(batch.getCurrentQuantity() - quantity);
    }

}
