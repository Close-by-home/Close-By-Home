insert
    into
        condominio
        (cep, cnpj, codigo_condominio, email_sindico, numero, quatidade_de_blocos, sindico, telefone)
    values
    ('58732970','64.986.858/0001-50','09520000','carlos@gmail.com',1,12,'Carlos','11979564553'),
    ('70655803','29.000.670/0001-90','02525000','eduardo@gmail.com',223,10,'Eduardo','11979564553');


insert
    into
        usuario
        (cpf,fk_codigo_condominio,bloco,email,telefone,nome,senha,funcionario,sexo,imagem)
    values
    ('47415092066',1,'b2','felipe@hotmail.com','11969385228','Felipe','senha123',true,'m','imagem'),
    ('70109220048',1,'c5','leandro@hotmail.com','11969385228','Leandro','senha123',false,'m','imagem'),
    ('25637233020',2,'f7','kleiton@hotmail.com','11969385228','Kleiton','senha123',false,'m','imagem');

insert
    into
        funcionario
        (nome_servico,valor_minimo,id_usuario)
    values
    ('Encanador',40.00,1);

insert
    into
        agenda
        (data,nota_servico,status,fk_id_funcionario,fk_id_usuario)
    values
    ('2022-01-13T17:09:42.411',5,'Em andamento',1,2),
    ('2014-01-13T17:09:42.411',5,'Finalizado',1,2);

insert
    into
        notificacao
        (titulo,descricao,fk_agenda,fk_usuario)
    values
    ('Serviço agendado','Olá Leandro, o seu serviço foi agendado para o dia 21/12/2022 com o Felipe, em breve ele irá entrar em contato para prosseguir com o serviço ',1,2),
    ('Serviço cancelado','Olá Leandro, infelizmente o seu serviço com o Felipe para o dia 21/12/2022 foi cancelado',1,2);
