package service;

import model.Country;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByCountries(String countries) {
        List<Country> countryList = Arrays.stream(countries.split(","))
                .map(Country::valueOf)
                .collect(Collectors.toList());
        return userRepository.findByCountryInOrderByCountry(countryList);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
