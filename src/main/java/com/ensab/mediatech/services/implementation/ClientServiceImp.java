
package com.ensab.mediatech.services.implementation;
import com.ensab.mediatech.dto.ClientDto;
import com.ensab.mediatech.entities.Client;
import com.ensab.mediatech.exceptions.EntityNotFoundException;
import com.ensab.mediatech.repositories.ClientRepository;
import com.ensab.mediatech.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import org.apache.log4j.Logger;
@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepo;
    private final ModelMapper modelMapper;


    private static final Logger log = Logger.getLogger(ClientService.class);

    @Override
    public ClientDto save(ClientDto clientDto) {
        System.out.println(clientDto);
        Client client = modelMapper.map(clientDto,Client.class);
        Client saved = clientRepo.save(client);
        return  modelMapper.map(saved ,ClientDto.class);
    }

    @Override
    public Collection<ClientDto> list(int limit) {
        log.info("get all customers");
        return clientRepo.findAll(PageRequest.of(0,limit))
                .stream().map(el->modelMapper.map(el,ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto update(ClientDto clientDto) throws EntityNotFoundException {
        Optional<Client> client = clientRepo.findById(clientDto.getId());
        if(client.isPresent()){
            Client client1 = modelMapper.map(clientDto,Client.class);
            Client updated = clientRepo.save(client1);
            return modelMapper.map(updated,ClientDto.class);
        }else{
            throw new EntityNotFoundException("Client Not Found !!");
        }
    }

    @Override
    public Boolean delete(Long id) {
        clientRepo.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public ClientDto findById(Long id) throws EntityNotFoundException {
        Client client = clientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("client with id "+id+" not found"));
        return modelMapper.map(client ,ClientDto.class);
    }

    @Override
    public ClientDto findByNom(String nom) throws EntityNotFoundException {
        Client client = clientRepo.findByNom(nom);
        if (client != null){
            return modelMapper.map(client ,ClientDto.class);
        }else {
            throw   new EntityNotFoundException("client with name "+"'"+nom+"'"+" not found !!");
        }
    }

}
