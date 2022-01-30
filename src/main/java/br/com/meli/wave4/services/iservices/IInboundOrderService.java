package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.entities.Section;

import java.util.List;

public interface IInboundOrderService {
    Boolean checkProductSection(Integer sectionCode, Integer productId);

    Integer getTotalProductsInSection(Section section);

    Integer getTotalProductsInSection(List<Batch> batchList);

    Integer getTotalProductsInSection(Integer sectionCode);

    Boolean verifyAvailableArea(Integer quantityRequired, Section section);

    InboundOrder saveInboundOrder(InboundOrder inboundOrder);

    InboundOrder updateById(InboundOrder inboundOrder);

    void registerBatch(List<Batch> batch);

    void registerBatch(Batch batch);

    InboundOrder create(InboundOrder inboundOrder);
}
