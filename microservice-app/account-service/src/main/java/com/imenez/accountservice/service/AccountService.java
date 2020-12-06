package com.imenez.accountservice.service;

import com.imenez.accountservice.entity.Account;
import com.imenez.accountservice.entity.es.AccountModel;
import com.imenez.accountservice.repo.AccountRepository;
import com.imenez.accountservice.repo.es.AccountElasticRepository;
import com.imenez.client.contract.AccountDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountElasticRepository accountElasticRepository;
    private final ModelMapper modelMapper;

   /* public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }*/


   @Transactional
    public AccountDto get(String id) {
        Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        modelMapper.map(account, AccountDto.class);
        return modelMapper.map(account, AccountDto.class);
    }

    @Transactional
    public AccountDto save(AccountDto accountDto){

        Account account = modelMapper.map(accountDto, Account.class);
        account = accountRepository.save(account);
        accountDto.setId(account.getId());

        AccountModel accountModel = modelMapper.map(accountDto, AccountModel.class);
        accountElasticRepository.save(accountModel);

        return accountDto;

    }

    @Transactional
    public AccountDto update(String id, AccountDto accountDto){

        Assert.isNull(id, "Id cannot be null");
        Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        account.setUsername(accountDto.getUsername());
        account.setSurname(accountDto.getSurname());
        account.setName(accountDto.getName());
        account.setEmail(accountDto.getEmail());
        account.setBirthDate(accountDto.getBirthDate());


        /*Optional<Account> account = accountRepository.findById(id);
        Account accountToUpdate = account.map(item ->{
                item.setBirthDate(accountDto.getBirthDate());
                item.setEmail(accountDto.getEmail());
                item.setName(accountDto.getName());
                item.setSurname(accountDto.getSurname());
                item.setUsername(accountDto.getUsername());
                return item;
        }).orElseThrow(IllegalArgumentException::new);*/

        return modelMapper.map(accountRepository.save(account), AccountDto.class);

    }

    @Transactional
    public void delete(String id){
        Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        accountRepository.delete(account);

    }

    public List<AccountDto> findAll(){
        List<Account> accountList = accountRepository.findAll();

        return (List<AccountDto>) modelMapper.map(accountList, AccountDto.class);
    }

    /*public Account pagenation(){

    }*/
}
