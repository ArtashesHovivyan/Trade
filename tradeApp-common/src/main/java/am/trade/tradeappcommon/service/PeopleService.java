package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.People;

import java.util.List;

public interface PeopleService {

    boolean isEmailExists(String email);

    void registerPeople(People people);

    List<People> findAll();

    People findPeopleById(int id);

    People getByEmailOrPhone(String email, String Phone);

    People getPeopleByPhone(String phone);

}
