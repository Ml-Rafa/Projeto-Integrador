package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.*;

import java.util.List;
import java.util.Optional;

public interface IInboundOrderService {
    Boolean checkProductSection(Integer sectionCode, Integer productId);

    Integer getTotalProductsInSection(Section section);

    Integer getTotalProductsInSection(List<Batch> batchList);

    Integer getTotalProductsInSection(Integer sectionCode);

    Boolean verifyAvailableArea(Integer quantityRequired, Section section);

    InboundOrder update(InboundOrder inboundOrder);

    void registerBatch(List<Batch> batch, InboundOrder inboundOrder);

    void registerBatch(Batch batch);

    void checkSectionOfWarehouse(Warehouse warehouse, Section section);

    Warehouse getWarehouse(InboundOrder inboundOrder);

    InboundOrder create(InboundOrder inboundOrder);

    Section getSection(InboundOrder inboundOrder);

    User getRepresentative(Batch batch);
//    Representative getRepresentative(Batch batch);

    InboundOrder findById(Integer inboundOrderID);
}
