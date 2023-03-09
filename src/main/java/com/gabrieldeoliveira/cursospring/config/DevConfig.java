package com.gabrieldeoliveira.cursospring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabrieldeoliveira.cursospring.domain.Address;
import com.gabrieldeoliveira.cursospring.domain.BankPaymentSlip;
import com.gabrieldeoliveira.cursospring.domain.CardPayment;
import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.domain.City;
import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.domain.Order;
import com.gabrieldeoliveira.cursospring.domain.OrderItem;
import com.gabrieldeoliveira.cursospring.domain.Payment;
import com.gabrieldeoliveira.cursospring.domain.Product;
import com.gabrieldeoliveira.cursospring.domain.State;
import com.gabrieldeoliveira.cursospring.domain.enums.ClientType;
import com.gabrieldeoliveira.cursospring.domain.enums.PaymentStatus;
import com.gabrieldeoliveira.cursospring.repositories.AddressRepository;
import com.gabrieldeoliveira.cursospring.repositories.CategoryRepository;
import com.gabrieldeoliveira.cursospring.repositories.CityRepository;
import com.gabrieldeoliveira.cursospring.repositories.ClientRepository;
import com.gabrieldeoliveira.cursospring.repositories.OrderItemRepository;
import com.gabrieldeoliveira.cursospring.repositories.OrderRepository;
import com.gabrieldeoliveira.cursospring.repositories.PaymentRepository;
import com.gabrieldeoliveira.cursospring.repositories.ProductRepository;
import com.gabrieldeoliveira.cursospring.repositories.StateRepository;

@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired 
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Bean
    public void initDb() {
        Category c1 = new Category(null, "Informática");
        Category c2 = new Category(null, "Escritório");
        Category c3 = new Category(null, "Cama, mesa e banho");
        Category c4 = new Category(null, "Eletrônicos");
        Category c5 = new Category(null, "Jardinagem");
        Category c6 = new Category(null, "Decoração");
        Category c7 = new Category(null, "Perfumaria");
        
        Product p1 = new Product(null, "Computador", 3000.0);
        Product p2 = new Product(null, "Impressora", 800.0);
        Product p3 = new Product(null, "Mouse", 80.0);
        Product p4 = new Product(null, "Mesa de escritório", 300.0);
        Product p5 = new Product(null, "Toalha", 50.0);
        Product p6 = new Product(null, "Colcha", 200.0);
        Product p7 = new Product(null, "TV true color", 1200.0);
        Product p8 = new Product(null, "Roçadeira", 800.0);
        Product p9 = new Product(null, "Abajour", 100.0);
        Product p10 = new Product(null, "Pendente", 180.0);
        Product p11 = new Product(null, "Shampoo", 90.0);

        
        c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        c2.getProducts().addAll(Arrays.asList(p2, p4));
        c3.getProducts().addAll(Arrays.asList(p5, p6));
        c4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        c5.getProducts().addAll(Arrays.asList(p8));
        c6.getProducts().addAll(Arrays.asList(p9, p10));
        c7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(c1, c4));
        p2.getCategories().addAll(Arrays.asList(c1, c2, c4));
        p3.getCategories().addAll(Arrays.asList(c1, c4));
        p4.getCategories().addAll(Arrays.asList(c2));
        p5.getCategories().addAll(Arrays.asList(c3));
        p6.getCategories().addAll(Arrays.asList(c3));
        p7.getCategories().addAll(Arrays.asList(c4));
        p8.getCategories().addAll(Arrays.asList(c5));
        p9.getCategories().addAll(Arrays.asList(c6));
        p10.getCategories().addAll(Arrays.asList(c6));
        p11.getCategories().addAll(Arrays.asList(c7));
        
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        State st1 = new State(null, "Minas Gerais");
        State st2 = new State(null, "São Paulo");

        City ct1 = new City(null, "Uberlândia", st1);
        City ct2 = new City(null, "São Paulo", st2);
        City ct3 = new City(null, "Campinas", st2);

        st1.getCities().addAll(Arrays.asList(ct1));
        st2.getCities().addAll(Arrays.asList(ct2, ct3));

        stateRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(ct1, ct2, ct3));

        Client cli1 = new Client(
            null, 
            "Maria Silva", 
            "maria@email.com", 
            "50849231000", 
            ClientType.FISIC_PERSON);

        cli1.getPhones().addAll(Arrays.asList("38917283413", "11912348793"));

        Address addr1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", ct1, cli1);
        Address addr2 = new Address(null, "Avenia Matos", "105", "Sala 800", "Centro", "38777012", ct2, cli1);

        cli1.getAddresses().addAll(Arrays.asList(addr1, addr2));

        clientRepository.saveAll(Arrays.asList(cli1));
        addressRepository.saveAll(Arrays.asList(addr1, addr2));

        Order o1 = new Order(null, Instant.parse("2017-09-30T10:32:00.00Z"), cli1, addr1);
        Order o2 = new Order(null, Instant.parse("2017-10-10T19:35:00.00Z"), cli1, addr2);

        Payment pmt1 = new CardPayment(null, PaymentStatus.SETTLED, o1, 6);
        o1.setPayment(pmt1);

        Payment pmt2 = new BankPaymentSlip(null, PaymentStatus.PENDING, o2, Instant.parse("2017-10-20T00:00:00.00Z"), null);
        o2.setPayment(pmt2);

        cli1.getOrders().addAll(Arrays.asList(o1, o2));

        orderRepository.saveAll(Arrays.asList(o1, o2));
        paymentRepository.saveAll(Arrays.asList(pmt1, pmt2));

        OrderItem oi1 = new OrderItem(o1, p1, 0.0, 1, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 0.0, 2, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p2, 100.0, 1, p2.getPrice());

        o1.getItems().addAll(Arrays.asList(oi1, oi2));
        o2.getItems().addAll(Arrays.asList(oi3));

        p1.getItems().addAll(Arrays.asList(oi1));
        p2.getItems().addAll(Arrays.asList(oi3));
        p3.getItems().addAll(Arrays.asList(oi2));

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
    }
}
