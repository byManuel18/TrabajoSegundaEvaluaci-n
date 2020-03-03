/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;
import java.time.LocalDateTime;
/**
 *
 * @author Manueh
 */
public interface IClient {
    String getID();  //is UNIQUE in SYSTEM
    String getName();
    void setName(String n);
    LocalDateTime getTime();
    void setTime(LocalDateTime t);
    String getPhone();
    void setPhone(String p);
}
