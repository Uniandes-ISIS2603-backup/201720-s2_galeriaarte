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
public class HojaVidaLogic {

    @Inject
    private HojaVidaPersistence persistence;

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public HojaVidaEntity createHojaVida(HojaVidaEntity entity) throws BusinessLogicException {
        // Verifica la regla de negocio que dice que no puede haber dos Estudiantees con el mismo nombre

        // Invoca la persistencia para crear la Estudiante
        persistence.create(entity);
        return entity;
    }
    
    
    
    public HojaVidaEntity getHojaVida(Long id) {
        HojaVidaEntity hojaVida = persistence.find(id);
        if (hojaVida == null) {
          
        }
          return hojaVida;
    }
    

    /**
     *
     * Obtener todas las Estudiantees existentes en la base de datos.
     *
     * @return una lista de Estudiantees.
     */
    public List<HojaVidaEntity> getHojasVida() {

        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        return persistence.findAll();

    }

    public HojaVidaEntity findHoja(Long id) {
        List<HojaVidaEntity> hojas = persistence.findAll();
        for (int i = 0; i < hojas.size(); i++) {
            HojaVidaEntity actual = hojas.get(i);
            if (actual.getId().equals(id)) {
                return actual;
            }
        }
        return null;
    }

    public void delete(Long id) {
        persistence.delete(id);
    }

    public HojaVidaEntity update(HojaVidaEntity entity) {

        return persistence.update(entity);

    }
    
    
   
}
