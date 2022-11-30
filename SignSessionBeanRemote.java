/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proj.login;

import javax.ejb.Remote;

/**
 *
 * @author 91904
 */
@Remote
public interface SignSessionBeanRemote {

    public String validateUser(String username, String email, String password);

    
    
}
