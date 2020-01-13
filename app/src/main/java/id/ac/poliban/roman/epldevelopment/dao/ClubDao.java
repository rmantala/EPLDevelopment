package id.ac.poliban.roman.epldevelopment.dao;

import java.util.List;

import id.ac.poliban.roman.epldevelopment.domain.Club;

public interface ClubDao {
    void insert(Club c);
    void update(Club c);
    void delete(int id);
    Club getClubById(int id);
    List<Club> getAllClub();
}
