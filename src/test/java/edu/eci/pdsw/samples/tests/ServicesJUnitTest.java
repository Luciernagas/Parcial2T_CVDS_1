/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.TipoIdentificacion;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSuscripciones;
import edu.eci.pdsw.samples.services.ServiciosPacientesFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author hcadavid
 */
public class ServicesJUnitTest {

    public ServicesJUnitTest() throws SQLException {
    }

    @Before
    public void setUp() throws SQLException{

    }

    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=MYSQL", "anonymous", "anonymous");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from CONSULTAS");
        stmt.execute("delete from PACIENTES");
        conn.commit();
        conn.close();
    }

    /**
     * Obtiene una conexion a la base de datos de prueba
     * @return
     * @throws SQLException 
     */
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=MYSQL", "anonymous", "anonymous");
    }
    
    @Test
    public void pruebaCeroTest() throws SQLException, ExcepcionServiciosSuscripciones, PersistenceException {
        //Insertar datos en la base de datos de pruebas, de acuerdo con la clase
        //de equivalencia correspondiente
        Connection conn=getConnection();
        Statement stmt=conn.createStatement();

        stmt.execute("INSERT INTO `PACIENTES` (`id`, `tipo_id`, `nombre`, `fecha_nacimiento`) VALUES (9876,'TI','Carmenzo','1995-07-10')");
        stmt.execute("INSERT INTO `CONSULTAS` (`idCONSULTAS`, `fecha_y_hora`, `resumen`, `PACIENTES_id`, `PACIENTES_tipo_id`) VALUES (1262218,'2001-01-01 00:00:00','Gracias',9876,'TI')");

        conn.commit();
        conn.close();
	
        //Realizar la operacion de la logica y la prueba

        
        List<Paciente> pacientes = ServiciosPacientesFactory.getInstance().getTestingForumServices().consultarPacientes();

        
        for (Paciente paciente : pacientes){
            System.out.println(paciente);
        }
        //assert ...
        Paciente paciente = ServiciosPacientesFactory.getInstance().getTestingForumServices().consultarPacientesPorId(9876, TipoIdentificacion.TI);
        Assert.assertEquals(paciente.getNombre(), "Carmenzo");
    }

    @Test
    public void Dado_Id_y_TipoIdentificacion_Cuando_PacienteExiste_Entonces_RetornaPaciente() throws SQLException {
        //Arrange
        Connection conn=getConnection();
        Statement stmt=conn.createStatement();

        stmt.execute("INSERT INTO `PACIENTES` (`id`, `tipo_id`, `nombre`, `fecha_nacimiento`) VALUES (1080,'CC','Aura','1968-07-20')");
        stmt.execute("INSERT INTO `CONSULTAS` (`idCONSULTAS`, `fecha_y_hora`, `resumen`, `PACIENTES_id`, `PACIENTES_tipo_id`) VALUES (1262218,'2002-05-13 00:00:00','Muy buen servicio',1080,'CC')");

        conn.commit();
        conn.close();

        try {
            //Act
            Paciente paciente = ServiciosPacientesFactory.getInstance().getTestingForumServices().consultarPacientesPorId(1080, TipoIdentificacion.CC);
            //Assert
            Assert.assertEquals(paciente.getId(), 1080);
        } catch (ExcepcionServiciosSuscripciones | PersistenceException e) {
            throw new SQLException("No se encuentra el paciente");
        }
    }

    @Test
    public void Dado_UnPaciente_Cuando_PacienteEsMenorEdadYEstaEnfermo_Entonces_RetornarListaPacientes() throws SQLException {
        //Arrange
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        stmt.execute("INSERT INTO `PACIENTES` (`id`, `tipo_id`, `nombre`, `fecha_nacimiento`) VALUES (1080,'TI','Aura','1968-07-20')");
        stmt.execute("INSERT INTO `CONSULTAS` (`idCONSULTAS`, `fecha_y_hora`, `resumen`, `PACIENTES_id`, `PACIENTES_tipo_id`) VALUES (1262219,'1962-01-01 00:00:00','hepatitis',1080,'TI')");

        stmt.execute("INSERT INTO `PACIENTES` (`id`, `tipo_id`, `nombre`, `fecha_nacimiento`) VALUES (9875,'TI','Edgar','1968-03-18')");
        stmt.execute("INSERT INTO `CONSULTAS` (`idCONSULTAS`, `fecha_y_hora`, `resumen`, `PACIENTES_id`, `PACIENTES_tipo_id`) VALUES (1262217,'1973-05-13 00:00:00','varicela',9875,'TI')");

        conn.commit();
        conn.close();
        try {
            //Act
            List<Paciente> pacientes = ServiciosPacientesFactory.getInstance().getTestingForumServices().consultarMenoresConEnfermedadContagiosa();
            String resumen = pacientes.get(0).getConsultas().get(0).getResumen();
            //Assert
            Assert.assertEquals(resumen, "varicela");
        } catch (ExcepcionServiciosSuscripciones | PersistenceException e) {
            throw new SQLException(e);
        }

    }
}
