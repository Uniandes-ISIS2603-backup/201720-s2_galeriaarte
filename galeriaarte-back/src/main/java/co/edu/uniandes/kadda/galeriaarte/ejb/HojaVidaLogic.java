/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.kadda.galeriaarte.ejb;


import co.edu.uniandes.kadda.galeriaarte.entities.HojaVidaEntity;
import co.edu.uniandes.kadda.galeriaarte.exceptions.BusinessLogicException;
import co.edu.uniandes.kadda.galeriaarte.persistence.HojaVidaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.carrillor
 */
@Stateless
public class HojaVidaLogic
{
 @Inject
    private HojaVidaPersistence persistence; 
 
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public HojaVidaEntity createHojaVida(HojaVidaEntity entity) throws BusinessLogicException 
    {
        // Verifica la regla de negocio que dice que no puede haber dos Estudiantees con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una Hoja de vida con el id \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear la Estudiante
        persistence.create(entity);
        return entity;
    }

    /**
     * 
     * Obtener todas las Estudiantees existentes en la base de datos.
     *
     * @return una lista de Estudiantees.
     */
    public List<HojaVidaEntity> getHojasVida() {
        
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<HojaVidaEntity> hojas = persistence.findAll();
        
        return hojas;
    }
    
    public HojaVidaEntity findHoja(Long id)
    {
        List<HojaVidaEntity> hojas = persistence.findAll();
        for(int i=0;i<hojas.size();i++)
        {
            HojaVidaEntity actual =hojas.get(i);
            if (actual.getId().equals(id))
            {
              return actual;  
            }
        }
        return null;
    }
    
    public void delete(Long id)
    {
       persistence.delete(id);
    }
    
    
    public void update(HojaVidaEntity entity)
    {
        
        HojaVidaEntity x = findHoja(entity.getId());
        if (x!=null)
        {
            persistence.update(x);
        }
        
    }
 
    
}
