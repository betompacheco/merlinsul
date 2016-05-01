//update proprietario set nomeproprietario='Luiz Armando Linhares Garcez' where codigoproprietario='153'

//select c.nomecondominio, a.numeroapartamento, p.codigoproprietario,p.nomeproprietario 
//from apartamento a, proprietario p, condominio c 
//where a.codigoproprietario = p.codigoproprietario and a.codigocondominio = c.codigocondominio 
//and numeroapartamento='2501' and c.codigocondominio='3'