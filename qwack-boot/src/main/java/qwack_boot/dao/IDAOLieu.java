package qwack_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import qwack_boot.model.Adresse;
import qwack_boot.model.Lieu;

public interface IDAOLieu extends JpaRepository<Lieu, Integer> {

	Lieu findByAdresse(Adresse adresse);

}
