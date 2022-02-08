package br.com.meli.wave4.services;


import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.RepresentativeNotCorrespondentException;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.IRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeService implements IRepresentativeService {

    @Autowired
    UserRepository userRepository;

    public User findById(Integer representativeId){

        return userRepository.findById(representativeId).orElse(null);
    }

    @Override
    public Boolean compareRepresentative( Integer representativeId, User representative){
        if (representativeId.equals(representative.getId())){
            return true;
        } else{
            throw new RepresentativeNotCorrespondentException();
        }
    }

    @Override
    public Boolean checkRepresentativeOfWarehouse(Warehouse warehouse, User representative){

        if (representative.getWarehouse().getId().equals(warehouse.getId())){
            return true;
        }
            throw new RepresentativeNotCorrespondentException();
    }
}
