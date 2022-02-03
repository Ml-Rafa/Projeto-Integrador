package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Representative;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.RepresentativeNotCorrespondentException;
import br.com.meli.wave4.repositories.RepresentativeRepository;
import br.com.meli.wave4.services.iservices.IRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeService implements IRepresentativeService {

    @Autowired
    RepresentativeRepository representativeRepository;

    public Representative findById(Integer representativeId){
        return representativeRepository.findById(representativeId).orElse(new Representative());
    }

    @Override
    public Boolean compareRepresentative( Integer representativeId, Representative representative){
        System.out.println("========================== = "+ representativeId +"  ,  " + representative.getId());
        if (representativeId.equals(representative.getId())){
            return true;
        } else{
            throw new RepresentativeNotCorrespondentException();
        }
    }

    @Override
    public Boolean checkRepresentativeOfWarehouse(Warehouse warehouse, Representative representative){
        if (representative.getWarehouse().getId().equals(warehouse.getId())){
            return true;
        } else {
            throw new RepresentativeNotCorrespondentException();
        }
    }
}
