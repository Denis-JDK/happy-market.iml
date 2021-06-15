package frontend;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import entity.Product;
import repository.ProductRepository;

@Route("main")
public class MainView extends VerticalLayout {
   private final Grid<Product> grid = new Grid<>(Product.class);

   private final ProductRepository productRepository;

    public MainView(ProductRepository productRepository1) {
        this.productRepository = productRepository1;


        initPage();
    }

    private void initPage(){
        initProductGrid();

        add( grid);

    }

    private void initProductGrid () {
        grid.setItems(productRepository.findAll());
        grid.setColumns("name", "count");
        grid.setSizeUndefined();

        grid.addColumn(new ComponentRenderer<>(item->{
            var plusButton = new Button("+", i->{
                item.incrementCount();
                productRepository.save(item);

            var minusButton = new Button("-", i->{
                item.decreaseCount();
                productRepository.save(item);
            });

            return new HorizontalLayout(plusButton,minusButton);
        }));
    }




}
