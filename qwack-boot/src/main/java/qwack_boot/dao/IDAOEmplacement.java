package qwack_boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import qwack_boot.model.Emplacement;
import qwack_boot.model.typeBox;

public interface IDAOEmplacement extends JpaRepository<Emplacement, Integer> {

    public List<Emplacement> findByCompletTrue();
    public List<Emplacement> findByCompletFalse();

    public List<Emplacement> findByBox(typeBox box);
}
