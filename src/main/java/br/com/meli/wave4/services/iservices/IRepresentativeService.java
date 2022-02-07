package br.com.meli.wave4.services.iservices;

//import br.com.meli.wave4.entities.Representative;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.entities.Warehouse;

public interface IRepresentativeService {
//    Boolean compareRepresentative( Integer representativeId, Representative representative);
//
//    public Boolean checkRepresentativeOfWarehouse(Warehouse warehouse, Representative representative);

    Boolean compareRepresentative( Integer representativeId, User representative);

    Boolean checkRepresentativeOfWarehouse(Warehouse warehouse, User representative);
}
