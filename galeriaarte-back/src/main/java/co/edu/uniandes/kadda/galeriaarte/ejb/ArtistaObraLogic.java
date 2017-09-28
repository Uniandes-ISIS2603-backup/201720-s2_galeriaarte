/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;

import co.edu.uniandes.kadda.galeriaarte.entities.ArtistaEntity;
import co.edu.uniandes.kadda.galeriaarte.entities.ObraEntity;
import co.edu.uniandes.kadda.galeriaarte.persistence.ArtistaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.carrillor
 */
//@Stateless
public class ArtistaObraLogic
{
    /*/
    @Inject

    private ArtistaPersistence persistence;
    
    /*/
    public ArtistaEntity findArtista(Long id)
    {
        /*/
        ArtistaEntity artistas = persistence.find(id);
/*/
       return null;
    }
    
    
     public List<ObraEntity> listObras(Long authorId)
      {
        return findArtista(authorId).getObras();
      }
    
}
