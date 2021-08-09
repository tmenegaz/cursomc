package com.proj.cursomc;

import com.proj.cursomc.domain.*;
import com.proj.cursomc.domain.enums.EstadoPagamento;
import com.proj.cursomc.domain.enums.TipoCliente;
import com.proj.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 3289.00);
        Product p2 = new Product(null, "Impressora", 858.00);
        Product p3 = new Product(null, "Mouse", 85.00);

        cat1.getProductes().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProductes().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Rio Grande do Sul");
        Estado est2 = new Estado(null, "Bahia");
        Estado est3 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Passo Fundo", est1);
        Cidade c2 = new Cidade(null, "Carazinho", est1);
        Cidade c3 = new Cidade(null, "Erechim", est1);

        Cidade c4 = new Cidade(null, "Salvador", est2);
        Cidade c5 = new Cidade(null, "Ilheus", est2);

        Cidade c6 = new Cidade(null, "São Paulo", est3);

        est1.getCidade().addAll(Arrays.asList(c1, c2, c3));
        est2.getCidade().addAll(Arrays.asList(c4, c5));
        est3.getCidade().addAll(Arrays.asList(c6));

        estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6 ));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "99999999999", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("88888888", "99999999"));

        Endereco e1 = new Endereco(null, "Rua Morom", "3006", "ap203", "Boqueirão","99010035", cli1, c1);
        Endereco e2 = new Endereco(null, "Rua Barro Vermelho", "345", "201", "Rio Vermelho","99040090", cli1, c4);

        cli1.getEndereco().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1= new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, e1);
        Pedido ped2= new Pedido(null, sdf.parse("10/10/2020 23:32"), cli1, e2);

        Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pg1);

        Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2020 00:00"), null);
        ped2.setPagamento(pg2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pg1, pg2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }
}
