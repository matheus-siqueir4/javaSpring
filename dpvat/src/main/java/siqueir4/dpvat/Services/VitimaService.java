package siqueir4.dpvat.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import siqueir4.dpvat.models.Vitima;

@Service
public interface VitimaService {

    public Vitima createVitima(Vitima vitima);
    
    public Vitima updateVitima(Vitima vitima);

    public List<Vitima> getAllVitima();

    public Vitima findByIdVitima(UUID id);

    public void deleteVitima(UUID id);
}
