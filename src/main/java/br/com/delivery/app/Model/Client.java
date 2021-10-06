package br.com.delivery.app.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_client;
	
	@NotBlank(message="Por favor entre com o nome")
	private String name;

	@NotBlank(message="Por favor entre com o telefone")
	private String phone;

	@NotBlank(message="Por favor entre com o endereço")
	private String address;
}
