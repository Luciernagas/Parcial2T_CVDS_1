## Escuela Colombiana de Ingeniería

### PDSW – Procesos de desarrollo de Software
### Parcial Segundo Tercio
### Luisa Valentina De la hoz Rocha

- - -
Como primer paso se clono el respositorio para modificarlo de manera local
![image](https://user-images.githubusercontent.com/104604359/200118567-12c5818b-c345-4c5c-959c-e7ba39069616.png)

Después se hizo el cambio de contraseña en config.properties porque las credenciales dadas en el repositorio no son las correctas
![image](https://user-images.githubusercontent.com/104604359/200118784-1aab118a-8ebf-4716-9637-94844c0bcab4.png)

***1. (20%) A partir de la especificación hecha en los métodos consultarPacientesPorId y consultarMenoresConEnfermedadContagiosa de la fachada de servicios (la parte lógica de la aplicación), implemente sólo una prueba (la que considere más importante para validar las especificaciones y los criterios de aceptación). Siga el esquema usado en ServicesJUnitTest para poblar la base de datos volátil y verificar el comportamiento de las operaciones de la lógica.***

Iniciamos implementando la lógica necesaria para resolver el primer punto inciando con el archivo PacienteMapper.xml el cual es el acceso a los datos de una base de datos relacional, es decir la relacion entre bases de datos y entidad.
![image](https://user-images.githubusercontent.com/104604359/200119005-1dd48c19-1fc0-4584-9ea5-e98c097a73be.png)
![image](https://user-images.githubusercontent.com/104604359/200118976-97ca4eae-6738-4179-8617-2575fbbfe245.png)
![image](https://user-images.githubusercontent.com/104604359/200118994-3b28e276-cac0-470d-bf09-0a33d9244f35.png)

Como segundo paso creamos los métodos para consultar pacientes por id y consultar menores con enfermedad contagiosa en la interfaz pacienteMapper creando la conexion entre parametros de la base de datos y la entidad.

![image](https://user-images.githubusercontent.com/104604359/200119095-f8a32605-eaf0-4e39-97da-e7e4d743bf3f.png)

Posteriormente repetimos el mismo paso de creacion de métodos en la interfaz DaoPaciente.
![image](https://user-images.githubusercontent.com/104604359/200119168-beee0c5f-d866-4598-bedc-a9110bfc8ea0.png)

Sobre escribimos los métodos en las clases MyBatisDAOPaciente y ServiciosPacienteImpl 
![image](https://user-images.githubusercontent.com/104604359/200119325-2da034ff-b609-4993-b068-79a290b7e899.png)
![image](https://user-images.githubusercontent.com/104604359/200119399-b79c60dd-a411-48fe-85ae-9034d69fda2f.png)

Comprobamos que con la implementacion de la lógica de consultarPacientesPorId podemos completar la pruebaCeroTest
![image](https://user-images.githubusercontent.com/104604359/200119455-7a231566-e142-4849-a9f3-3ad922363c97.png)
y como resultado debe ser exitoso el resultado de la prueba
![image](https://user-images.githubusercontent.com/104604359/200119486-3856194f-131d-4511-9a0b-742e8fe55336.png)

Realizamos la implementación de las pruebas necesarias para consultarPacientesPorId y consultarMenoresConEnfermedadContagiosa
![image](https://user-images.githubusercontent.com/104604359/200119572-0734cbcf-00d1-4c81-a052-a5e5462bb767.png)
![image](https://user-images.githubusercontent.com/104604359/200119589-f3772791-7c21-4e98-be57-9232303691e6.png)

El resultado de las pruebas deberia ser exitoso
![image](https://user-images.githubusercontent.com/104604359/200119660-c5f1e809-a105-4c72-a3c2-2075ef5c6ab2.png)
![image](https://user-images.githubusercontent.com/104604359/200119703-b5cd65f7-36fc-4d77-b9f4-bd2640e8977d.png)


***2.(40%) Implemente la historia de usuario #1, agregando todo lo que haga falta en la capa de presentación, lógica y de persistencia. La vista debe implementarse en consultaPaciente.xhtml.***
![image](https://user-images.githubusercontent.com/104604359/200119732-33bc130b-ddfd-44b4-8e92-2b125a520794.png)
![image](https://user-images.githubusercontent.com/104604359/200119735-396dce22-80de-4458-b5aa-e9062ee2b31d.png)


***3.(40%)Implemente la historia de usuario #2, agregando todo lo que haga falta en la capa de presentación, lógica y de persistencia. La vista debe implementarse en consultarMenoresEnfermedadContagiosa.xhtml.***
![image](https://user-images.githubusercontent.com/104604359/200120915-4c45989a-28d9-48c7-b3c4-71c9454b2488.png)
![image](https://user-images.githubusercontent.com/104604359/200120925-8a17f5de-7fb5-4da4-8144-1b5a0edd0ee6.png)





