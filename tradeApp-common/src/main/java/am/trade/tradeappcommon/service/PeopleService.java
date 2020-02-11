package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.People;

import java.util.List;

public interface PeopleService {

    boolean isEmailExists(String email);

    void registerPeople(People people);

    List<People> findAll();

    People findPeopleById(int id);

    People getPeopleByPhone(String phone);

    People findPeopleByEmailOrPhone(String search);

}
