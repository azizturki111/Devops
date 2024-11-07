package tn.esprit.tpfoyer.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j  // Simple Loggining Façade For Java
public class BlocServiceImpl  implements IBlocService {


    BlocRepository blocRepository;

    @Scheduled(fixedRate = 30000) // millisecondes // cron fixedRate
    //@Scheduled(cron="0/15 * * * * *")
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }


    @Transactional
    public Bloc retrieveBloc(Long blocId) {

        return blocRepository.findById(blocId).orElse(null);
    }


    public Bloc addBloc(Bloc c) {

        return blocRepository.save(c);
    }

    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }



    public List<Bloc> trouverBlocsSansFoyer() {
        return blocRepository.findAllByFoyerIsNull();
    }

    public List<Bloc> trouverBlocsParNomEtCap(String nb, long c) {
        return blocRepository.findAllByNomBlocAndCapaciteBloc(nb,  c);
    }

}
