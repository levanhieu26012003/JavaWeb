/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lvh.pojo.Image;
import com.lvh.pojo.Motel;
import com.lvh.repositories.ImageRepository;
import com.lvh.repositories.MotelRepository;
import com.lvh.services.MotelService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author levan
 */
@Service
public class MotelServiceImpl
        implements MotelService {
    
    @Autowired
    private MotelRepository motelRepo;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private ImageRepository imgRepo;
    
    @Override
    public List<Motel> getMotel(Map<String, String> params) {
        return this.motelRepo.getMotel(params);
    }
    
    @Override
    @Transactional
    public void saveOrUpdateMotel(Motel motel) {
        
        this.motelRepo.saveOrUpdateMotel(motel);
        
        System.out.println(motel.getFiles().size());
        
        if (motel.getFiles() != null) {
            for (MultipartFile image : motel.getFiles()) {
                Map uploadResult;
                System.out.println("zzzzzzzzzzoooooooooooo");
                try {
                    uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                    String url = (String) uploadResult.get("url");
                    String publicId = (String) uploadResult.get("public_id");
                    Image img = new Image();
                    img.setMotelId(motel);
                    img.setUrl(url);
                    img.setPublicUrl(publicId);
                    imgRepo.saveImage(img);
                } catch (IOException ex) {
                    Logger.getLogger(MotelServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    @Override
    public void deleteMotel(Long id) {
        this.motelRepo.deleteMotel(id);
    }
    
    @Override
    public Motel getMotelById(Long id) {
        return this.motelRepo.getMotelById(id);
    }
    
    @Override
    public Map<String, Object> motelToJson(Motel motel) {
        return this.motelRepo.motelToJson(motel);
    }
    
    @Override
    public Motel jsonToMotel(Map<String, String> params, List<MultipartFile> files) {
        return this.motelRepo.jsonToMotel(params, files);
    }
    
    @Override
    public List<Motel> getMotelByUsername(String username) {
        return this.getMotelByUsername(username);
    }
    
}
