package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    ChambreRepository chambreRepository;

    public List<Chambre> retrieveAllChambres() {
        log.info("In Methodo retrieveAllChambres : ");
        List<Chambre> listC = chambreRepository.findAll();
        log.info("Out of retrieveAllChambres : ");

        return listC;
    }

    public Chambre retrieveChambre(Long chambreId) {
        Chambre c = chambreRepository.findById(chambreId).get();
        return c;
    }

    public Chambre addChambre(Chambre c) {
        Chambre chambre = chambreRepository.save(c);
        return chambre;
    }

    public Chambre modifyChambre(Chambre c) {
        Chambre chambre = chambreRepository.save(c);
        return c;
    }

    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }


    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc)
    {
        return chambreRepository.findAllByTypeC(tc);
    }



    public Chambre trouverchambreSelonEtudiant(long cin) {
       //

        return chambreRepository.trouverChselonEt(cin);
    }

    public boolean checkIfChambreHasNoValidReservationForYear(Long chambreId, Integer anneeUniversitaire) {
        // Récupération de la chambre à partir de son ID
        Chambre chambre = retrieveChambre(chambreId);

        // Vérification si la chambre a des réservations valides pour l'année universitaire donnée
        return chambre.getReservations().stream()
                .noneMatch(reservation -> {
                    // Extraction de l'année de la date de réservation en utilisant Calendar
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(reservation.getAnneeUniversitaire());
                    int anneeReservation = calendar.get(Calendar.YEAR); // Obtient l'année

                    // Comparaison de l'année de réservation avec l'année universitaire donnée
                    return anneeReservation == anneeUniversitaire && reservation.isEstValide();
                });
    }


}
