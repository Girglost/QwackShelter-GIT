package quack.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import quack.model.Emplacement;

public interface IDAOEmplacement extends JpaRepository<Emplacement,Integer> {


}
