package tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllChambres() {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null));
        chambres.add(new Chambre(2L, 102L, TypeChambre.DOUBLE, null, null));

        when(chambreRepository.findAll()).thenReturn(chambres);

        List<Chambre> result = chambreService.retrieveAllChambres();
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveChambre() {
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(1L);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void testAddChambre() {
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testModifyChambre() {
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreService.modifyChambre(chambre);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRemoveChambre() {
        Long chambreId = 1L;
        doNothing().when(chambreRepository).deleteById(chambreId);

        chambreService.removeChambre(chambreId);
        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre(1L, 101L, typeChambre, null, null));

        when(chambreRepository.findAllByTypeC(typeChambre)).thenReturn(chambres);

        List<Chambre> result = chambreService.recupererChambresSelonTyp(typeChambre);
        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findAllByTypeC(typeChambre);
    }

    @Test
    void testTrouverchambreSelonEtudiant() {
        long cin = 12345678L;
        Chambre chambre = new Chambre(1L, 101L, TypeChambre.SIMPLE, null, null);

        when(chambreRepository.trouverChselonEt(cin)).thenReturn(chambre);

        Chambre result = chambreService.trouverchambreSelonEtudiant(cin);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }
}
