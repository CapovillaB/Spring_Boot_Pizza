package com.pizzaria.sis_pedido.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Cliente;
import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.entity.Pedido;
import com.pizzaria.sis_pedido.model.service.ClienteService;
import com.pizzaria.sis_pedido.model.service.ItemService;
import com.pizzaria.sis_pedido.model.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PedidoService pedidoService;

    private Map<Integer, Item> mapaDeItens = new LinkedHashMap<>();
    private int contadorDeItens = 1;

    @GetMapping
    public ModelAndView mostrarMenu() {
        ModelAndView modelAndView = new ModelAndView("menu");

        List<Item> registrosP = itemService.buscarTodasPizzas();
        List<Item> registrosB = itemService.buscarTodasBebidas();

        modelAndView.addObject("registrosP", registrosP);
        modelAndView.addObject("registrosB", registrosB);
        modelAndView.addObject("itensSelecionados", mapaDeItens.values());

        return modelAndView;
    }

    @PostMapping
    public String adicionarAoPedido(@RequestParam String nomeItem, @RequestParam String descItem,
            @RequestParam double priceItem) {
        Item item = new Item();
        item.setIdItem(contadorDeItens++);
        item.setNomeItem(nomeItem);
        item.setDescItem(descItem);
        item.setPriceItem(priceItem);

        mapaDeItens.put(item.getIdItem(), item);

        System.out.println("Item adicionado ao pedido: " + item.getNomeItem() + " - " + item.getDescItem() + " - "
                + item.getPriceItem());
        System.out.println("Mapa de Itens:");
        for (Item i : mapaDeItens.values()) {
            System.out.println(
                    i.getIdItem() + " - " + i.getNomeItem() + " - " + i.getDescItem() + " - " + i.getPriceItem());
        }

        return "redirect:/pedido";
    }

    @PostMapping("/enviarPedido")
    public String enviarPedido() {
        // Com a variável logarUsuario, você tem o nome do cliente logado.
        // Você pode obter o cliente associado a esse nome.

        Cliente cliente = clienteService.buscarClientePorIdUsuario(LogarController.usuario.getIdUsuario());
        if (LogarController.usuario.isLogged()) {

            // Crie um novo pedido
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);

            // Transforme os IDs dos itens em objetos Item
            List<Item> itensSelecionados = new ArrayList<>();
            for (Integer itemId : mapaDeItens.keySet()) {
                Item item = itemService.buscarItemPorId(itemId);
                if (item != null) {
                    itensSelecionados.add(item);
                }
            }

            // Adicione os itens do pedido ao pedido.
            pedido.setItens(itensSelecionados);

            // <<<< VALOR TOTAL DO PEDIDO "BD pedido_valor" >>>>//
            // Calcule o valor total do pedido com base nos itens.
            float valorTotal = 0.0f; // Inicialize o valor total como 0
            for (Item item : itensSelecionados) {
                valorTotal += item.getPriceItem(); // Some o preço de cada item ao valor total
            }
            // Define o valor total no pedido
            pedido.setPedidoValor(valorTotal);

            // <<<< "BD pedido_pag" >>>>//
            String tipoPagamento = "Cartao";
            pedido.setPedidoPagamento(tipoPagamento);

            // <<<< "BD pedido_status" >>>>//
            String pedidoStatus = "teste";
            pedido.setPedidoStatus(pedidoStatus);

            // Salve o pedido no banco de dados.
            pedidoService.salvarPedido(pedido);

            // Limpe o mapa de itens após o pedido ser enviado
            // mapaDeItens.clear();

            // Redirecione para a página de confirmação, ou qualquer outra página desejada.
            return "redirect:/confirmacaoPedido";
        } else {
            return "redirect:/pedido";

        }
    }

}

/*
 * package com.pizzaria.sis_pedido.controller;
 * 
 * import java.util.ArrayList;
 * import java.util.LinkedHashMap;
 * import java.util.List;
 * import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Controller;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RequestParam;
 * import org.springframework.web.servlet.ModelAndView;
 * 
 * import com.pizzaria.sis_pedido.model.entity.Item;
 * import com.pizzaria.sis_pedido.model.service.ItemService;
 * 
 * @Controller
 * 
 * @RequestMapping("/pedido")
 * public class PedidoController {
 * 
 * @Autowired
 * private ItemService itemService;
 * 
 * private Map<Integer, Item> mapaDeItens = new LinkedHashMap<>();
 * private int contadorDeItens = 1;
 * 
 * @GetMapping
 * public ModelAndView mostrarMenu() {
 * ModelAndView modelAndView = new ModelAndView("menu");
 * 
 * List<Item> registrosP = itemService.buscarTodasPizzas();
 * List<Item> registrosB = itemService.buscarTodasBebidas();
 * 
 * modelAndView.addObject("registrosP", registrosP);
 * modelAndView.addObject("registrosB", registrosB);
 * modelAndView.addObject("pedidos", mapaDeItens.values());
 * 
 * return modelAndView;
 * }
 * 
 * @PostMapping
 * public String adicionarAoPedido(@RequestParam String nomeItem, @RequestParam
 * String descItem, @RequestParam double priceItem) {
 * Item item = new Item();
 * item.setIdItem(contadorDeItens++);
 * item.setNomeItem(nomeItem);
 * item.setDescItem(descItem);
 * item.setPriceItem(priceItem);
 * 
 * mapaDeItens.put(item.getIdItem(), item);
 * 
 * System.out.println("Item adicionado ao pedido: " + item.getNomeItem() + " - "
 * + item.getDescItem() + " - " + item.getPriceItem());
 * System.out.println("Mapa
 */