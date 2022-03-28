package com.awbd.proiect.services;

import com.awbd.proiect.domain.Info;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.repositories.LocationRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class InfoServiceImpl implements  InfoService{
    private static final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    LocationRepository locationRepository;

    @Autowired
    public InfoServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long locationId, String description, MultipartFile file) {
        try {
            Location location = locationRepository.findById(locationId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0; for (byte b : file.getBytes()){
                byteObjects[i++] = b; }

            Info info = location.getInfo();
            if (info == null) {
                info = new Info();
            }

            info.setImage(byteObjects);
            info.setDescription(description);
            location.setInfo(info);
            info.setLocation(location);
            locationRepository.save(location);
            logger.info("S-a adaugat informatia pentru locatia  cu id-ul " + locationId + " {} ", info);
        }
        catch (IOException e) {
        }
    }

    @Override
    public Info findByLocationId(Long id) {
        Optional<Location> locationOptional =
                locationRepository.findById(id);
        if (!locationOptional.isPresent()) {
            throw new ObjectNotFoundException("Locatia nu a fost gasita!");
        }
        if (locationOptional.get().getInfo() == null) {
            throw new ObjectNotFoundException("Informatia nu a fost gasita!");
        }
        Info info = locationOptional.get().getInfo();
        logger.info("S-a preluat informatia pentru locatia cu id-ul " + id + " {} ", info);
        return info;
    }
}
