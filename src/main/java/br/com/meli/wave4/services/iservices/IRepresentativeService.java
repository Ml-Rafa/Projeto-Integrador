package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Representative;

public interface IRepresentativeService {
    Boolean compareRepresentative( Integer representativeId, Representative representative);
}
