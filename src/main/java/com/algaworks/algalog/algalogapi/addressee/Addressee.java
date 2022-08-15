package com.algaworks.algalog.algalogapi.addressee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Addressee {

    @Column(name = "destinatario_nome")
    private String name;

    @Column(name = "destinatario_endereco")
    private String address;

    @Column(name = "destinatario_numero")
    private String number;

    @Column(name = "destinatario_complemento")
    private String complement;

    @Column(name = "destinatario_bairro")
    private String district;
}
