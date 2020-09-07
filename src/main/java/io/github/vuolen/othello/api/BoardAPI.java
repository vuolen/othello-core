/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.api;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public interface BoardAPI {
    
    public int getTile(int x, int y);
    
    public int size();
}
