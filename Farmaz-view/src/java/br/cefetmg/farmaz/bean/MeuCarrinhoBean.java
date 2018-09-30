package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Produto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Arthur
 */

@ManagedBean(name="MeuCarrinhoBean")
@ViewScoped
public class MeuCarrinhoBean implements Serializable {
     
    private List<Produto> produtos;
 
    @PostConstruct
    public void init() {
        
    }
}
