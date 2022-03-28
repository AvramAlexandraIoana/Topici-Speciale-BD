package com.awbd.proiect.services;

import com.awbd.proiect.domain.Info;
import org.springframework.web.multipart.MultipartFile;

public interface InfoService {
    void saveImageFile(Long locationId, String description, MultipartFile file);
    Info findByLocationId(Long l);
//    Info save(Info info);
//    Info update(Info info);
}
