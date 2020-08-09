package filter;

public class UsuarioFilter extends BasicoFilter{
    
    private Boolean acesso;
    private Integer tipo;

    public Boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(Boolean acesso) {
        this.acesso = acesso;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}