package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.BatchDTO;
import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.repositories.BatchRepository;
import br.com.meli.wave4.services.iservices.IBatchService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class BatchService implements IBatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private RepresentativeService representativeService;



    public BatchService(BatchRepository batchRepository){
        this.batchRepository = batchRepository;
    }
    @Override
    public Batch findByBatchNumber(Integer batchNumber){
       return batchRepository.findByBatchNumber(batchNumber).orElse(new Batch());
    }

    @Override
    public List<Batch> saveAll(List<Batch> batchList){
        return batchRepository.saveAll(batchList);
    }

    @Override
    public void save(Batch batch){
        batchRepository.save(batch);
    }

    public Batch convertToEntity(BatchDTO batch) {
        return Batch.builder()
                .batchNumber(batch.getBatchNumber())
                .currentQuantity(batch.getCurrentQuantity())
                .initialQuantity(batch.getInitialQuantity())
                .product(productService.findById(batch.getProductId()))
                .currentTemperature(batch.getCurrentTemperature())
                .minimumTemperature(batch.getMinimumTemperature())
                .representative(representativeService.findById(batch.getRepresentativeId()))
                .dueDate(batch.getDueDate())
                .manufacturingDate(batch.getManufacturingDate())
                .manufacturingTime(batch.getManufacturingTime())
               // .inboundOrder(this.inboundOrderService.findyById(batch.getInboundOrderId()))
                .build();
    }

}
