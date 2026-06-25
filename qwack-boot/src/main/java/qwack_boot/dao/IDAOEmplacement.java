package qwack_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import qwack_boot.model.Emplacement;

public interface IDAOEmplacement extends JpaRepository<Emplacement, Integer> {

}
