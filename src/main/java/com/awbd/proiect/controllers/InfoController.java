package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Info;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.services.InfoService;
import com.awbd.proiect.services.LocationService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/info")
public class InfoController {
    private InfoService infoService;
    private LocationService locationService;

    public InfoController(InfoService infoService, LocationService locationService) {
        this.infoService = infoService;
        this.locationService = locationService;
    }

    @PostMapping("/upload/{id}/{description}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void handleImagePost(@PathVariable Long id, @PathVariable String description, @RequestParam("imageFile") MultipartFile file){

        infoService.saveImageFile(id, description, file);
    }


    @GetMapping("location/getImage/{id}")
    public void downloadImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        Location location = locationService.findById(Long.valueOf(id));
        if (location.getInfo() != null) {
            Info info = location.getInfo();

            if (location.getInfo().getImage() != null) {
                byte[] byteArray = new byte[info.getImage().length];
                int i = 0;
                for (Byte wrappedByte : info.getImage()) {
                    byteArray[i++] = wrappedByte;
                }
                response.setContentType("image/jpeg");
                InputStream is = new ByteArrayInputStream(byteArray);
                try {
                    IOUtils.copy(is, response.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Info> getByLocationId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(infoService.findByLocationId(id));
    }

}
