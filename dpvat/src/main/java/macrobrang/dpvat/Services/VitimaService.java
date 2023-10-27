package macrobrang.dpvat.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import macrobrang.dpvat.Models.Vitima;

@Service
public interface VitimaService {

    public Vitima createVitima(Vitima vitima);
    
    public Vitima updateVitima(Vitima vitima);

    public List<Vitima> getAllVitima();

    public Vitima findByIdVitima(UUID id);

    public void deleteVitima(UUID id);
}
