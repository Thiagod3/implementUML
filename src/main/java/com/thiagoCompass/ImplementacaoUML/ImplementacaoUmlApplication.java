package com.thiagoCompass.ImplementacaoUML;



import java.util.Arrays;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagoCompass.ImplementacaoUML.domain.Categoria;
import com.thiagoCompass.ImplementacaoUML.domain.Cidade;
import com.thiagoCompass.ImplementacaoUML.domain.Cliente;
import com.thiagoCompass.ImplementacaoUML.domain.Endereco;
import com.thiagoCompass.ImplementacaoUML.domain.Estado;
import com.thiagoCompass.ImplementacaoUML.domain.ItemPedido;
import com.thiagoCompass.ImplementacaoUML.domain.Pagamento;
import com.thiagoCompass.ImplementacaoUML.domain.PagamentoComBoleto;
import com.thiagoCompass.ImplementacaoUML.domain.PagamentoComCartao;
import com.thiagoCompass.ImplementacaoUML.domain.Pedido;
import com.thiagoCompass.ImplementacaoUML.domain.Produto;
import com.thiagoCompass.ImplementacaoUML.domain.enums.EstadoPagamento;
import com.thiagoCompass.ImplementacaoUML.domain.enums.TipoCliente;
import com.thiagoCompass.ImplementacaoUML.repositories.CategoriaRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.CidadeRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.ClienteRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.EnderecoRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.EstadoRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.ItemPedidoRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.PagamentoRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.PedidoRepository;
import com.thiagoCompass.ImplementacaoUML.repositories.ProdutoRepository;

@SpringBootApplication
public class ImplementacaoUmlApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private EstadoRepository estadorepository;
	
	@Autowired
	private CidadeRepository cidaderepository;
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Autowired
	private EnderecoRepository enderecorepository;
	
	@Autowired
	private PedidoRepository pedidorepository;
	
	@Autowired
	private PagamentoRepository pagamentorepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImplementacaoUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//categorias e produtos
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 200.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Computador", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2));
		produtorepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//Estados e cidades
		
		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Niterói", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Santos", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadorepository.saveAll(Arrays.asList(est1, est2));
		cidaderepository.saveAll(Arrays.asList(c1, c2, c3));
		
		//clientes e endereços
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36778912377", TipoCliente.PESSOAFISICA); 
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220634", cli1, c1 );
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2 );
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienterepository.saveAll(Arrays.asList(cli1));
		enderecorepository.saveAll(Arrays.asList(e1, e2));
		
		//pedidos e pagamentos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		pedidorepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentorepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		//item-pedido (produtos que fazem parte de um pedido)
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}


















