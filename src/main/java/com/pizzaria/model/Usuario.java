package main.java.com.pizzaria.model;


@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;


    private String nome_usuario;


    private String pswd_usuario;
    
}
