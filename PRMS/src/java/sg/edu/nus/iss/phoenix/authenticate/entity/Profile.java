/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nikhil Metrani
 */
public class Profile {
    private String siteLink;
    private String image;

    public String getSiteLink() {
        return siteLink;
    }

    public void setSiteLink(String siteLink) {
        if (null != siteLink)
            this.siteLink = siteLink;
    }

    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        if (null != image)
            this.image = image;
    }
    
    public Profile() {
        image = "";
        siteLink = "";
    }
    
    public Profile clone() {
        Profile clone = new Profile();
        clone.setImage(new String(image));
        clone.setSiteLink(new String(siteLink));
        return clone;
    }
}
